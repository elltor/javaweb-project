package marchsoft.modules.fruit.service.impl;

import lombok.RequiredArgsConstructor;
import marchsoft.modules.fruit.entity.FruitProductImage;
import marchsoft.modules.fruit.mapper.FruitProductImageMapper;
import marchsoft.modules.fruit.service.IFruitProductImageService;
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
public class FruitProductImageServiceImpl extends BasicServiceImpl<FruitProductImageMapper, FruitProductImage> implements IFruitProductImageService {

    private final FruitProductImageMapper fruitProductImageMapper;

}

