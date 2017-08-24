package cn.leiyy.modules.wechat.dao;

import cn.leiyy.common.node.ZTreeNode;

import java.util.List;

/**
 * Created by hanlin.huang on 2017/8/24.
 */
public interface WechatMenuDao {
    List<ZTreeNode> tree();
}
