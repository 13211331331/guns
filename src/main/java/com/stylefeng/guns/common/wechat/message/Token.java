package com.stylefeng.guns.common.wechat.message;
/**
 * 类名: Token </br>
 * 描述: 凭证 </br>
 * 开发人员： souvc </br>
 * 创建时间：  2015-9-30 </br>
 * 发布版本：V1.0  </br>
 */
public class Token
{
    // 接口访问凭证
    private String accessToken;
    // 凭证有效期，单位：秒
    private String expiresIn;

    public String getAccessToken()
    {
        return accessToken;
    }

    public void setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;
    }

    public String getExpiresIn()
    {
        return expiresIn;
    }
    public void setExpiresIn(String expiresIn)
    {
        this.expiresIn = expiresIn;
    }
}