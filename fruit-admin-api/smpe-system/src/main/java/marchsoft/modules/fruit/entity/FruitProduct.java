package marchsoft.modules.fruit.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import marchsoft.base.BasicModel;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author liuqichun
 * @since 2021-06-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="FruitProduct对象", description="")
public class FruitProduct extends BasicModel<FruitProduct> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private String sourcePlace;

    private LocalDateTime pickTime;

    private String phaseId;

    private Long qualityId;

    private String aimPace;

    private Long carId;

    private LocalDateTime transportTime;

    private Integer count;

    private Long unitId;

    private LocalDateTime saleTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
