package cn.leiyy.common.wechat.utils;

/**
 * Created by hanlin.huang on 2017/8/27.
 */
public enum MenuType {

    CLICK("事件触发","click", 1),
    VIEW("URL跳转","view", 2),
    MEMU("带下级菜单","menu", 3),
    SCANCODE_WAITMSG("扫码带提示","scancode_waitmsg", 4),
    SCANCODE_PUSH("扫码推事件","scancode_push", 5),
    PIC_SYSPHOTO("系统拍照发图","pic_sysphoto", 6),
    PIC_PHOTO_OR_ALBUM("拍照或者相册发图","pic_photo_or_album", 7),
    PIC_WEIXIN("微信相册发图","pic_weixin", 8),
    LOCATION_SELECT("发送位置","location_select", 9),
    MINIPROGRAM("小程序跳转","miniprogram", 10);


    private String typeName;
    private String typeKey;
    private int index;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeKey() {
        return typeKey;
    }

    public void setTypeKey(String typeKey) {
        this.typeKey = typeKey;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    // 构造方法
    private MenuType(String typeName, String typeKey,int index) {
        this.typeName = typeName;
        this.typeKey = typeKey;
        this.index = index;

    }


}
