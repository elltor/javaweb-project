package marchsoft.modules.fruit.service.impl;

import lombok.RequiredArgsConstructor;
import marchsoft.modules.fruit.entity.Car;
import marchsoft.modules.fruit.mapper.CarMapper;
import marchsoft.modules.fruit.service.ICarService;
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
public class CarServiceImpl extends BasicServiceImpl<CarMapper, Car> implements ICarService {

    private final CarMapper carMapper;

}

