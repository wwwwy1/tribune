package cn.wwwwy.tribune.service.impl;


import cn.wwwwy.tribune.entity.SensitiveWords;
import cn.wwwwy.tribune.mapper.SensitiveWordsMapper;
import cn.wwwwy.tribune.service.ISensitiveWordsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wwwwy
 * @since 2020-02-25
 */
@Service
public class SensitiveWordsServiceImpl extends ServiceImpl<SensitiveWordsMapper, SensitiveWords> implements ISensitiveWordsService {

}
