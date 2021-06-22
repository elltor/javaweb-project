package marchsoft.modules.fruit.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import marchsoft.modules.fruit.dto.FruitProductCriteria;
import marchsoft.modules.fruit.dto.FruitProductDTO;
import marchsoft.modules.fruit.entity.FruitProduct;
import marchsoft.base.IBasicService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liuqichun
 * @since 2021-06-21
 */
public interface IFruitProductService extends IBasicService<FruitProduct> {

    IPage<FruitProductDTO> search(FruitProductCriteria criteria,IPage<FruitProduct> page);

}
