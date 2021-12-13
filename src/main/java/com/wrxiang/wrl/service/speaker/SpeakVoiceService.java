package com.wrxiang.wrl.service.speaker;

import com.wrxiang.wrl.domain.dto.SpeakVoiceMessage;

/**
 * @Description 语音播放接口
 * @Author wrxiang
 * @Date 2021/12/12 13:54
 */
public interface SpeakVoiceService {

    /**
     * 语音生成及播报
     * @param speakVoiceMessage
     */
    void speak(SpeakVoiceMessage speakVoiceMessage);

}
