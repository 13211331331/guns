package cn.leiyy.modules.wechat.service.impl;

import cn.leiyy.common.persistence.model.WechatMenu;
import cn.leiyy.common.persistence.dao.WechatMenuMapper;
import cn.leiyy.modules.wechat.Const;
import cn.leiyy.modules.wechat.service.IWechatMenuService;
import cn.leiyy.modules.wechat.vo.WechatMenuVo;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<WechatMenuVo> getMenusByPublicSignalId(Integer id) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("public_signal_id",id);
        List<WechatMenu> list = selectByMap(map);
        List<WechatMenuVo> result = new ArrayList<WechatMenuVo>();
        for(WechatMenu wechatMenu:list){
            WechatMenuVo vo = new WechatMenuVo();
            BeanUtils.copyProperties(wechatMenu,vo);
            if(wechatMenu.getType().equals(Const.MENU_TYPE_CLICK)){
                vo.setTypeName(Const.MENU_TYPE_CLICK_NAME);
            } else  if(wechatMenu.getType().equals(Const.MENU_TYPE_VIEW)){
                vo.setTypeName(Const.MENU_TYPE_VIEW_NAME);
            }  else  if(wechatMenu.getType().equals(Const.MENU_TYPE_MENU)){
                vo.setTypeName(Const.MENU_TYPE_MENU_NAME);
            }
            if(wechatMenu.getPid() != null){
                WechatMenu w = super.selectById(wechatMenu.getPid());
                vo.setPidName(w.getName());
            }
            result.add(vo);
        }
        return result;
    }





}
