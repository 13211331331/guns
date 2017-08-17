package cn.leiyy.modules.wechat.service;

import cn.leiyy.common.persistence.model.PublicSignal;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author HuangHanlin
 * @since 2017-08-12
 */
public interface IPublicSignalService extends IService<PublicSignal> {

    Object add(Map<String, Object> map);
}
