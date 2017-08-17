package cn.leiyy.modules.wechat.service.impl;

import cn.leiyy.common.persistence.model.PublicSignal;
import cn.leiyy.common.persistence.dao.PublicSignalMapper;
import cn.leiyy.modules.wechat.service.IPublicSignalService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author HuangHanlin
 * @since 2017-08-12
 */
@Service
public class PublicSignalServiceImpl extends ServiceImpl<PublicSignalMapper, PublicSignal> implements IPublicSignalService {

    @Override
    public Object add(Map<String, Object> map) {
        return null;
    }
}