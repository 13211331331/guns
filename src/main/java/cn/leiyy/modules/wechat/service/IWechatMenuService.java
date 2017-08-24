package cn.leiyy.modules.wechat.service;

import cn.leiyy.common.persistence.model.WechatMenu;
import cn.leiyy.modules.wechat.vo.WechatMenuVo;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author HuangHanlin
 * @since 2017-08-23
 */
public interface IWechatMenuService extends IService<WechatMenu> {

    List<WechatMenuVo> getMenusByPublicSignalId(Integer id);

}
