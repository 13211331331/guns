package cn.leiyy.modules.wechat.service;

import cn.leiyy.common.persistence.model.OperationLog;
import cn.leiyy.common.persistence.model.PublicSignal;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
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

    List<PublicSignal> findByCondition(Page<PublicSignal> page,String condition);

}
