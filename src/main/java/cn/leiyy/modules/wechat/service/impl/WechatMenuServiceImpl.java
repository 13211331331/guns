package cn.leiyy.modules.wechat.service.impl;

import cn.leiyy.common.persistence.model.WechatMenu;
import cn.leiyy.common.persistence.dao.WechatMenuMapper;
import cn.leiyy.modules.wechat.service.IWechatMenuService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author HuangHanlin
 * @since 2017-08-23
 */
@Service
public class WechatMenuServiceImpl extends ServiceImpl<WechatMenuMapper, WechatMenu> implements IWechatMenuService {

    @Override
    public List<WechatMenu> getMenusByPublicSignalId(Integer id) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("public_signal_id",id);
        return super.selectByMap(map);
    }
}
