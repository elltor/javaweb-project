package marchsoft.modules.fruit.service.impl;

import lombok.RequiredArgsConstructor;
import marchsoft.modules.fruit.entity.Unit;
import marchsoft.modules.fruit.mapper.UnitMapper;
import marchsoft.modules.fruit.service.IUnitService;
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
public class UnitServiceImpl extends BasicServiceImpl<UnitMapper, Unit> implements IUnitService {

    private final UnitMapper unitMapper;

}

