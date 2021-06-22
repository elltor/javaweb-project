package marchsoft.modules.fruit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 *
 * @author: liuqichun
 * @date: 2021/6/22 17:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FruitProductDTO {

    private Long id;

    private String name;

    private String sourcePlace;

    private LocalDateTime pickTime;

    private String phaseName;

    private String qualityName;

    private String aimPace;

    private String carName;

    private LocalDateTime transportTime;

    private Integer count;

    private String unitName;

    private LocalDateTime saleTime;

}
