package marchsoft.modules.fruit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Description:
 *
 * @author: liuqichun
 * @date: 2021/6/22 16:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FruitProductCriteria {
    /** 品质编号 */
    private Long qualityId;

    /** 车辆编号 **/
    private Long carId;

    /** 阶段编号 **/
    private Long phaseId;

    /** name 和 source_place 的关键字 */
    private String Key;
}
