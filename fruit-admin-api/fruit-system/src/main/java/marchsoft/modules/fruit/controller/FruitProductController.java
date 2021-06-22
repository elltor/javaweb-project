package marchsoft.modules.fruit.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import marchsoft.annotation.AnonymousAccess;
import marchsoft.base.PageVO;
import marchsoft.exception.BadRequestException;
import marchsoft.modules.fruit.dto.FruitProductCriteria;
import marchsoft.modules.fruit.dto.FruitProductDeleteDTO;
import marchsoft.modules.fruit.dto.FruitProductModifyBatchDTO;
import marchsoft.response.Result;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

import marchsoft.modules.fruit.service.IFruitProductService;
import marchsoft.modules.fruit.entity.FruitProduct;

import java.util.Arrays;


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
@Api(tags = "FruitProduct模块")
@RequestMapping("/api/fruitProduct")
public class FruitProductController {

    private final IFruitProductService fruitProductService;

    @ApiOperation(value = "搜索接口", notes = " \n author: liuqichun")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "criteria", value = "标准查询字段", paramType = "query",
                    dataType = "CompetitionScheduleQueryCriteria"),
            @ApiImplicitParam(name = "pageVO", value = "分页对象", paramType = "query", dataType = "PageVO")
    })
    @GetMapping
    @AnonymousAccess
    public Result<Object> competitionScheduleSearch(FruitProductCriteria criteria,
                                                    PageVO pageVO) {
        if (criteria == null) {
            throw new BadRequestException("【查询条件不为null, 至少需要传competitionId】");
        }
        return Result.success(fruitProductService.search(criteria, pageVO.buildPage()));
    }

    @ApiOperation(value = "单个删除", notes = " \n author: liuqichun ")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", paramType = "path",
                    dataType = "Long"),
    })
    @DeleteMapping("/{id}")
    @AnonymousAccess
    public Result<Object> delete(@PathVariable Long id){
        fruitProductService.removeById(id);
        return Result.success();
    }


    @ApiOperation(value = "批量删除", notes = " \n author: liuqichun")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deleteIds", value = "删除id列表", paramType = "body",
                    dataType = "FruitProductDeleteDTO"),

    })
    @AnonymousAccess
    @PostMapping("/delete-batch")
    public Result<Object> deleteBatch(FruitProductDeleteDTO deleteIds){
        Long[] fruitProductIds = deleteIds.getFruitProductIds();
        if(fruitProductIds!=null){
            fruitProductService.removeByIds(Arrays.asList(fruitProductIds));
        }else{
            return Result.error("fruit product id 列表为空");
        }
        return Result.success();
    }


    @ApiOperation(value = "修改单个对象", notes = " \n author: liuqichun")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruit", value = "产品对象", paramType = "body",
                    dataType = "FruitProduct"),

    })
    @PutMapping()
    public Result<Object> modify(FruitProduct fruit){
        fruitProductService.updateById(fruit);
        return Result.success();
    }

    @ApiOperation(value = "批量更新", notes = " \n author: liuqichun")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitProductModifyBatchDTO", value = "批量更新DTO", paramType = "body",
                    dataType = "FruitProductModifyBatchDTO"),

    })
    @PutMapping("/update-batch")
    public Result<Object> modifyBatch(FruitProductModifyBatchDTO fruitProductModifyBatchDTO){
        if(fruitProductModifyBatchDTO.getFruitIds()==null || fruitProductModifyBatchDTO.getFruitProduct()==null){
            return Result.error("参数错误");
        }
        LambdaUpdateWrapper<FruitProduct> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.in(FruitProduct::getId, fruitProductModifyBatchDTO.getFruitIds());
        fruitProductService.update(fruitProductModifyBatchDTO.getFruitProduct(),updateWrapper);
        return Result.success();
    }

    @ApiOperation(value = "添加水果产品", notes = " \n author: liuqichun")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruit", value = "批量更新DTO", paramType = "body",
                    dataType = "FruitProduct"),

    })
    @PostMapping
    public Result<Object> add(FruitProduct fruit){
        fruitProductService.save(fruit);
        return Result.success();
    }
}
