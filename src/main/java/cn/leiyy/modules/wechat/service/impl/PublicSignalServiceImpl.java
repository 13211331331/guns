package cn.leiyy.modules.wechat.service.impl;

import cn.leiyy.common.persistence.model.OperationLog;
import cn.leiyy.common.persistence.model.PublicSignal;
import cn.leiyy.common.persistence.dao.PublicSignalMapper;
import cn.leiyy.modules.wechat.dao.PublicSignalDao;
import cn.leiyy.modules.wechat.service.IPublicSignalService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Autowired
    PublicSignalDao publicSignalDao;


    @Override
    public List<PublicSignal> findByCondition(Page<PublicSignal> page, String name) {
        return publicSignalDao.getOperation(page,name, page.getOrderByField(), page.isAsc());
    }
}
