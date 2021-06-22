package marchsoft.modules.fruit.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import marchsoft.modules.fruit.dto.FruitProductCriteria;
import marchsoft.modules.fruit.dto.FruitProductDTO;
import marchsoft.modules.fruit.entity.FruitProduct;
import marchsoft.modules.fruit.mapper.FruitProductMapper;
import marchsoft.modules.fruit.service.IFruitProductService;
import marchsoft.base.BasicServiceImpl;
import org.springframework.stereotype.Service;

/**
* <p>
*  服务实现类
* </p>
*
* @author liuqichun
* @since 2021-06-21
*/
@Service
@RequiredArgsConstructor
public class FruitProductServiceImpl extends BasicServiceImpl<FruitProductMapper, FruitProduct> implements IFruitProductService {

    private final FruitProductMapper fruitProductMapper;

    @Override
    public IPage<FruitProductDTO> search(FruitProductCriteria criteria, IPage<FruitProduct> page) {
        return fruitProductMapper.search(criteria.getKey(),criteria.getQualityId(),criteria.getCarId(),criteria.getPhaseId(),page);
    }
}

