package com.stylefeng.guns.common.wechat.message.resp;


/**
 * @author HuangHL
 * @date 2017/2/27
 * @see
 */
public class VoiceMessage extends BaseMessage
{
    // 语音
    private Voice Voice;

    public Voice getVoice() {
        return Voice;
    }

    public void setVoice(Voice voice) {
        Voice = voice;
    }
}
