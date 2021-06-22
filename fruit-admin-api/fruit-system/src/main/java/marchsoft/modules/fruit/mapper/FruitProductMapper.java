package marchsoft.modules.fruit.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import marchsoft.modules.fruit.dto.FruitProductDTO;
import marchsoft.modules.fruit.entity.FruitProduct;
import marchsoft.base.BasicMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
* <p>
*  Mapper 接口
* </p>
*
* @author liuqichun
* @since 2021-06-21
*/
@Component
public interface FruitProductMapper extends BasicMapper<FruitProduct> {

    /**
     * @author: liuqichun
     * @date: 2021/6/22 20:14
     */
    @Select("<script> fp.*, c.`name` AS car_name, q.`name` AS quality_name, p.`name` AS phase_name\n" +
            "FROM fruit_product fp INNER JOIN quality q fp.quality_id = q.id \n" +
            "INNER JOIN car c ON fp.car_id = c.id \n" +
            "INNER JOIN phase p ON fp.phase_id = p.id\n" +
            "WHERE 1=1 \n" +
            "<if test='nameAndSourcePlaceKey!=null' >\n" +
            "   AND (fp.name like '${nameAndSourcePlaceKey}%' OR fp.source_place like '${nameAndSourcePlaceKey}%') \n" +
            "</if> \n" +
            "<if test='carId!=null'> \n" +
            "   AND fp.car_id = #{carId} \n" +
            "</if> \n" +
           "<if test='phaseId!=null'> \n" +
            "   AND fp.phase_id = #{phaseId} \n" +
            "</if></script>")
    IPage<FruitProductDTO> search(String nameAndSourcePlaceKey, Long qualityId, Long carId, Long phaseId, IPage<FruitProduct> page);
}
