package cn.leiyy.modules.wechat.vo;

import cn.leiyy.common.persistence.model.WechatMenu;

/**
 * Created by hanlin.huang on 2017/8/23.
 */
public class WechatMenuVo extends WechatMenu {

    private String pidName;
    private String typeName;

    public String getPidName() {
        return pidName;
    }

    public void setPidName(String pidName) {
        this.pidName = pidName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
