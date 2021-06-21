package marchsoft.modules.fruit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

import marchsoft.modules.fruit.service.IFruitProductService;
import marchsoft.modules.fruit.entity.FruitProduct;


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
@RequestMapping("/api/fruitProduct")
public class FruitProductController {
    private final IFruitProductService fruitProductService;

}
