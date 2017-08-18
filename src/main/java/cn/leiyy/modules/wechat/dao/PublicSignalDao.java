package cn.leiyy.modules.wechat.dao;
import cn.leiyy.common.persistence.model.PublicSignal;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by hanlin.huang on 2017/8/18.
 */
public interface PublicSignalDao {

    List<PublicSignal> getOperation(@Param("page") Page<PublicSignal> page, @Param("name") String name, @Param("orderByField") String orderByField, @Param("isAsc") boolean isAsc);

}
