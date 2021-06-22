package marchsoft.modules.fruit.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import marchsoft.annotation.AnonymousAccess;
import marchsoft.base.PageVO;
import marchsoft.exception.BadRequestException;
import marchsoft.modules.fruit.dto.FruitProductCriteria;
import marchsoft.response.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

import marchsoft.modules.fruit.service.IFruitProductImageService;
import marchsoft.modules.fruit.entity.FruitProductImage;

import javax.validation.constraints.NotNull;


/**
* <p>
*  前端控制器
* </p>
* @author liuqichun
* @since 2021-06-21
*/
@RequiredArgsConstructor
@RestController
@Slf4j
@Api(tags = "模块")
@RequestMapping("/api/fruitProductImage")
public class FruitProductImageController {
    private final IFruitProductImageService fruitProductImageService;




}
