package marchsoft.modules.fruit.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author: liuqichun
 * @date: 2021/6/22 20:40
 */
@Data
@NoArgsConstructor
public class FruitProductDeleteDTO {
    private Long[] fruitProductIds;
}
