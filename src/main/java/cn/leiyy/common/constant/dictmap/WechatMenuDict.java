package cn.leiyy.common.constant.dictmap;

import cn.leiyy.common.constant.dictmap.base.AbstractDictMap;

/**
 * Created by hanlin.huang on 2017/8/23.
 */
public class WechatMenuDict extends AbstractDictMap {

    @Override
    public void init() {
        put("type","菜单类型");
        put("name","菜单名称");
        put("url","跳转url");
        put("key","菜单指令");
        put("pid","上级菜单");
        put("publicSignalId","公众号id");
    }

    @Override
    protected void initBeWrapped() {

    }
}
