package marchsoft.modules.fruit.service.impl;

import lombok.RequiredArgsConstructor;
import marchsoft.modules.fruit.entity.FruitProduct;
import marchsoft.modules.fruit.mapper.FruitProductMapper;
import marchsoft.modules.fruit.service.IFruitProductService;
import marchsoft.base.BasicServiceImpl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}

