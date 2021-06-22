package marchsoft.modules.fruit.dto;

import lombok.Data;
import marchsoft.modules.fruit.entity.FruitProduct;

/**
 * @author: liuqichun
 * @date: 2021/6/22 21:27
 */
@Data
public class FruitProductModifyBatchDTO {
    private Long[] fruitIds;
    private FruitProduct fruitProduct;
}
