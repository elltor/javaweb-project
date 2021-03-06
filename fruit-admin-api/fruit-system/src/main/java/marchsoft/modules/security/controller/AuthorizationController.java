package marchsoft.modules.security.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.wf.captcha.base.Captcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import marchsoft.annotation.rest.AnonymousDeleteMapping;
import marchsoft.annotation.rest.AnonymousGetMapping;
import marchsoft.annotation.rest.AnonymousPostMapping;
import marchsoft.config.bean.RsaProperties;
import marchsoft.enums.ResultEnum;
import marchsoft.exception.BadRequestException;
import marchsoft.modules.security.config.bean.LoginCodeEnum;
import marchsoft.modules.security.config.bean.LoginProperties;
import marchsoft.modules.security.config.bean.SecurityProperties;
import marchsoft.modules.security.entity.dto.AuthUserDto;
import marchsoft.modules.security.entity.dto.JwtUserDto;
import marchsoft.modules.security.security.TokenProvider;
import marchsoft.modules.security.service.OnlineUserService;
import marchsoft.modules.system.service.IUserService;
import marchsoft.response.Result;
import marchsoft.utils.RedisUtils;
import marchsoft.utils.RsaUtils;
import marchsoft.utils.SecurityUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Wangmingcan
 * @date 2020-08-23
 * ???????????????token????????????????????????
 */
@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Api(tags = "???????????????????????????")
public class AuthorizationController {

    private final SecurityProperties properties;
    private final RedisUtils redisUtils;
    private final OnlineUserService onlineUserService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final LoginProperties loginProperties;
    private final IUserService userService;

    @ApiOperation("????????????")
    @AnonymousPostMapping(value = "/login")
    public Result<Map<String, Object>> login(@Validated @RequestBody AuthUserDto authUser,
                                             HttpServletRequest request) throws Exception {
        // ????????????
        String password = RsaUtils.decryptByPrivateKey(RsaProperties.privateKey, authUser.getPassword());
        // ???????????????
        String code = (String) redisUtils.get(authUser.getUuid());
        // ???????????????
        redisUtils.del(authUser.getUuid());
        if (StrUtil.isBlank(code)) {
            throw new BadRequestException("??????????????????????????????");
        }
        if (StrUtil.isBlank(authUser.getCode()) || !authUser.getCode().equalsIgnoreCase(code)) {
            throw new BadRequestException("???????????????");
        }
        // modify @RenShiWei 2020/11/23 description:??????token???username->id???
        Long userId = userService.findUserIdByName(authUser.getUsername());
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userId, password);

        Authentication authentication = null;
        try {
            authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        } catch (BadCredentialsException e) {
            //?????????????????????
            throw new BadRequestException(ResultEnum.LOGIN_FAIL);
        } catch (InternalAuthenticationServiceException e) {
            throw new BadRequestException(ResultEnum.COUNT_NOT_ENABLE);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // ????????????
        final JwtUserDto jwtUserDto = (JwtUserDto) authentication.getPrincipal();
        String token = tokenProvider.createToken(authentication, jwtUserDto.getUser().getId());
        // ??????????????????
        onlineUserService.save(jwtUserDto, token, request);
        // ?????? token ??? ????????????
        Map<String, Object> authInfo = new HashMap<String, Object>(2) {{
            put("token", properties.getTokenStartWith() + token);
            put("user", jwtUserDto);
        }};
        if (loginProperties.isSingleLogin()) {
            //???????????????????????????token
            onlineUserService.checkLoginOnUser(authUser.getUsername(), token);
        }
        return Result.success(authInfo);
    }

    @ApiOperation("??????????????????")
    @GetMapping(value = "/info")
    public Result<UserDetails> getUserInfo() {
        UserDetails currentUser = SecurityUtils.getCurrentUser();
        if (ObjectUtil.isEmpty(currentUser)) {
            throw new BadRequestException(ResultEnum.LOGIN_USER_INFO_NOT_FOUND);
        }
        return Result.success(currentUser);
    }

    @ApiOperation("???????????????")
    @AnonymousGetMapping(value = "/code")
    public Result<Map<String, Object>> getCode() {
        // ?????????????????????
        Captcha captcha = loginProperties.getCaptcha();
        String uuid = properties.getCodeKey() + IdUtil.simpleUUID();
        //????????????????????? arithmetic???????????? >= 2 ??????captcha.text()??????????????????????????????
        String captchaValue = captcha.text();
        if (captcha.getCharType() - 1 == LoginCodeEnum.arithmetic.ordinal() & captchaValue.contains(".")) {
            captchaValue = captchaValue.split("\\.")[0];
        }
        // ??????
        redisUtils.set(uuid, captchaValue, loginProperties.getLoginCode().getExpiration(), TimeUnit.MINUTES);
        log.info(StrUtil.format("?????????????????????????????????????????????{}", captchaValue));
        // ???????????????
        Map<String, Object> imgResult = new HashMap<String, Object>(2) {{
            put("img", captcha.toBase64());
            put("uuid", uuid);
        }};
        return Result.success(imgResult);
    }

    @ApiOperation("????????????")
    @AnonymousDeleteMapping(value = "/logout")
    public Result<Void> logout(HttpServletRequest request) {
        onlineUserService.logout(tokenProvider.getToken(request));
        return Result.success();
    }

    @ApiOperation("????????????????????????token??????????????????????????????")
    @AnonymousPostMapping(value = "/testLogin")
    public Result<Map<String, Object>> testLogin(@Validated @RequestBody AuthUserDto authUser,
                                                 HttpServletRequest request) throws Exception {
        String password = authUser.getPassword();
        // modify @RenShiWei 2020/11/23 description:??????token???username->id???
        Long userId = userService.findUserIdByName(authUser.getUsername());
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userId, password);
        Authentication authentication = null;
        try {
            authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        } catch (BadCredentialsException e) {
            throw new BadRequestException(ResultEnum.LOGIN_FAIL);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // ????????????
        final JwtUserDto jwtUserDto = (JwtUserDto) authentication.getPrincipal();
        String token = tokenProvider.createToken(authentication, jwtUserDto.getUser().getId());
        // ??????????????????
        onlineUserService.save(jwtUserDto, token, request);
        // ?????? token ??? ????????????
        Map<String, Object> authInfo = new HashMap<String, Object>(2) {{
            put("token", properties.getTokenStartWith() + token);
            put("user", jwtUserDto);
        }};
        if (loginProperties.isSingleLogin()) {
            //???????????????????????????token
            onlineUserService.checkLoginOnUser(authUser.getUsername(), token);
        }
        return Result.success(authInfo);
    }

}
