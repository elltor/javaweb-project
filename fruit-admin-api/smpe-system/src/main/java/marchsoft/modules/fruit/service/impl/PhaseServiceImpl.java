package marchsoft.modules.fruit.service.impl;

import lombok.RequiredArgsConstructor;
import marchsoft.modules.fruit.entity.Phase;
import marchsoft.modules.fruit.mapper.PhaseMapper;
import marchsoft.modules.fruit.service.IPhaseService;
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
public class PhaseServiceImpl extends BasicServiceImpl<PhaseMapper, Phase> implements IPhaseService {

    private final PhaseMapper phaseMapper;

}

