package cn.leiyy.modules.wechat.service.impl;

import cn.leiyy.common.exception.BizExceptionEnum;
import cn.leiyy.common.exception.BussinessException;
import cn.leiyy.common.node.ZTreeNode;
import cn.leiyy.common.persistence.model.PublicSignal;
import cn.leiyy.common.persistence.model.WechatMenu;
import cn.leiyy.common.persistence.dao.WechatMenuMapper;
import cn.leiyy.common.wechat.message.menu.*;
import cn.leiyy.common.wechat.message.pojo.AccessToken;
import cn.leiyy.common.wechat.utils.WeixinUtil;
import cn.leiyy.modules.wechat.Const;
import cn.leiyy.modules.wechat.dao.PublicSignalDao;
import cn.leiyy.modules.wechat.dao.WechatMenuDao;
import cn.leiyy.modules.wechat.service.IPublicSignalService;
import cn.leiyy.modules.wechat.service.IWechatMenuService;
import cn.leiyy.modules.wechat.vo.WechatMenuVo;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Autowired
    WechatMenuDao wechatMenuDao;

    @Autowired
    IPublicSignalService publicSignalService;

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

    @Override
    public List<ZTreeNode> getTree() {
        return wechatMenuDao.tree();
    }

    @Override
    public void synch(Integer publicSignalId) {
        PublicSignal publicSignal = publicSignalService.selectById(publicSignalId);
        List<WechatMenuVo> vos = getMenusByPublicSignalId(publicSignalId);
        // 调用接口获取access_token
        AccessToken at = WeixinUtil.getAccessToken(publicSignal.getAppid(), publicSignal.getAppsecret());
        if (null != at) {
            // 调用接口创建菜单
            MenuList menuLists = getMenu(vos);
            String result = WeixinUtil.createMenuList(menuLists, at.getToken());
            // 判断菜单创建结果
            if ("0".equals(result))
            {
                return;
            }
            else
                throw new RuntimeException("菜单创建失败，错误码：" + result);
        }else {
            throw new BussinessException(BizExceptionEnum.ERROR_WECHAT_NO_TOKEN);
        }
    }

    private MenuList getMenu(List<WechatMenuVo> vos) {
        MenuList menu = new MenuList();
        List<Button> buttons = new ArrayList<Button>();

        for(WechatMenuVo wechatMenuVo:vos){
            if(wechatMenuVo.getType().equals(Const.MENU_TYPE_MENU)){
                ComplexButtonList a = new ComplexButtonList();
                a.setName(wechatMenuVo.getName());
                List<Button> sub = new ArrayList<Button>();
                int i = 0;
                for(WechatMenuVo wechatMenuVo1:vos){
                    if(wechatMenuVo1.getPid() != null && wechatMenuVo1.getPid().intValue() == wechatMenuVo.getId().intValue()){
                        i++;
                        if(wechatMenuVo1.getType().equals(Const.MENU_TYPE_VIEW)){
                            ViewButton b = new ViewButton();
                            b.setName(wechatMenuVo1.getName());
                            b.setUrl(wechatMenuVo1.getUrl());
                            sub.add(b);
                        }
                        if(wechatMenuVo1.getType().equals(Const.MENU_TYPE_CLICK)){
                            CommonButton b = new CommonButton();
                            b.setName(wechatMenuVo1.getName());
                            b.setType(Const.MENU_TYPE_CLICK);
                            b.setKey(wechatMenuVo1.getKey());
                            sub.add(b);
                        }

                    }
                }
                if(i == 0){
                    throw new RuntimeException("菜单：" +wechatMenuVo.getName() + " 是带子菜单类型，没有相应子菜单，创建菜单失败");
                }else {
                    a.setSub_button(sub);
                    buttons.add(a);
                 }
            }else{
                if(wechatMenuVo.getPid() == null){
                    if(wechatMenuVo.getType().equals(Const.MENU_TYPE_VIEW)){
                        ViewButton b = new ViewButton();
                        b.setName(wechatMenuVo.getName());
                        b.setUrl(wechatMenuVo.getUrl());
                        buttons.add(b);
                    }
                    if(wechatMenuVo.getType().equals(Const.MENU_TYPE_CLICK)){
                        CommonButton b = new CommonButton();
                        b.setName(wechatMenuVo.getName());
                        b.setType(Const.MENU_TYPE_CLICK);
                        b.setKey(wechatMenuVo.getKey());
                        buttons.add(b);
                    }
                }
            }
        }


        menu.setButton(buttons);
        return menu;
    }


}
