package marchsoft.modules.fruit.service.impl;

import lombok.RequiredArgsConstructor;
import marchsoft.modules.fruit.entity.Quality;
import marchsoft.modules.fruit.mapper.QualityMapper;
import marchsoft.modules.fruit.service.IQualityService;
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
public class QualityServiceImpl extends BasicServiceImpl<QualityMapper, Quality> implements IQualityService {

    private final QualityMapper qualityMapper;

}

