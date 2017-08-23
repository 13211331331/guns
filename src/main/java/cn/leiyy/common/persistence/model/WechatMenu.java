package cn.leiyy.common.persistence.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author HuangHanlin
 * @since 2017-08-23
 */
@TableName("wechat_menu")
public class WechatMenu extends Model<WechatMenu> {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单层级
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 菜单类型
     */
	private String type;
    /**
     * 菜单名称
     */
	private String name;
    /**
     * 跳转url
     */
	private String url;
    /**
     * 菜单指令
     */
	private String key;
    /**
     * 上级菜单
     */
	private Integer pid;
    /**
     * 菜单层级
     */
	private Integer level;
    /**
     * 公众号id
     */
	@TableField("public_signal_id")
	private Integer publicSignalId;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getPublicSignalId() {
		return publicSignalId;
	}

	public void setPublicSignalId(Integer publicSignalId) {
		this.publicSignalId = publicSignalId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "WechatMenu{" +
			"id=" + id +
			", type=" + type +
			", name=" + name +
			", url=" + url +
			", key=" + key +
			", pid=" + pid +
			", level=" + level +
			", publicSignalId=" + publicSignalId +
			"}";
	}
}
