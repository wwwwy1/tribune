package cn.wwwwy.tribune.service.impl;

import cn.wwwwy.tribune.entity.Tags;
import cn.wwwwy.tribune.mapper.TagsMapper;
import cn.wwwwy.tribune.service.ITagsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wwwwy
 * @since 2020-02-09
 */
@Service
public class TagsServiceImpl extends ServiceImpl<TagsMapper, Tags> implements ITagsService {

}
