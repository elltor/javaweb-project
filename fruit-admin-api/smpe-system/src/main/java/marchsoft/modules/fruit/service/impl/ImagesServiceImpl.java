package marchsoft.modules.fruit.service.impl;

import lombok.RequiredArgsConstructor;
import marchsoft.modules.fruit.entity.Images;
import marchsoft.modules.fruit.mapper.ImagesMapper;
import marchsoft.modules.fruit.service.IImagesService;
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
public class ImagesServiceImpl extends BasicServiceImpl<ImagesMapper, Images> implements IImagesService {

    private final ImagesMapper imagesMapper;

}

