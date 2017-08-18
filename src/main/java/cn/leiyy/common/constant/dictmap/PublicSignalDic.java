package cn.leiyy.common.constant.dictmap;

import cn.leiyy.common.constant.dictmap.base.AbstractDictMap;

/**
 * 字典map
 *
 * @author fengshuonan
 * @date 2017-05-06 15:43
 */
public class PublicSignalDic extends AbstractDictMap {



    @Override
    public void init() {
        put("name","公众号");
        put("appid","appid");
        put("appsecret","appsecret");
        put("url","url");
        put("token","token");
    }

    @Override
    protected void initBeWrapped() {

    }
}
