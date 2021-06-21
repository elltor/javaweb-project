package marchsoft.modules.fruit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

import marchsoft.modules.fruit.service.IImagesService;
import marchsoft.modules.fruit.entity.Images;


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
@RequestMapping("/api/images")
public class ImagesController {
    private final IImagesService imagesService;

}
