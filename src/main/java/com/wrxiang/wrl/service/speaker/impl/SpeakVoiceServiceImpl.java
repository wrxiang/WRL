package com.wrxiang.wrl.service.speaker.impl;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.wrxiang.wrl.constant.SpeakVoiceConstant;
import com.wrxiang.wrl.domain.dto.SpeakVoiceMessage;
import com.wrxiang.wrl.service.speaker.SpeakVoiceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @Description 语音播报实现类
 * @Author wrxiang
 * @Date 2021/12/12 13:55
 */
@Service
public class SpeakVoiceServiceImpl implements SpeakVoiceService {


    /**
     * 语音生成及播报
     * @param speakVoiceMessage
     */
    @Override
    public void speak(SpeakVoiceMessage speakVoiceMessage) {

        setDefaultValueOfSpeakVoiceMessage(speakVoiceMessage);

        ActiveXComponent ax = new ActiveXComponent("Sapi.SpVoice");
        final Dispatch spVoice = ax.getObject();
        ax.setProperty("Volume", new Variant(speakVoiceMessage.getVolume()));
        ax.setProperty("Rate", new Variant(speakVoiceMessage.getRate()));
        Dispatch.call(spVoice, "Speak", new Object[] { new Variant((Object)speakVoiceMessage.getMessage()) });
        ax = new ActiveXComponent("Sapi.SpFileStream");
        final Dispatch spFileStream = ax.getObject();
        ax = new ActiveXComponent("Sapi.SpAudioFormat");
        final Dispatch spAudioFormat = ax.getObject();
        Dispatch.put(spAudioFormat, "Type", (Object)new Variant(22));
        Dispatch.putRef(spFileStream, "Format", (Object)spAudioFormat);
        Dispatch.call(spFileStream, "Open", new Object[] { new Variant((Object)speakVoiceMessage.getVoiceAddress()), new Variant(3), new Variant(true) });
        Dispatch.putRef(spVoice, "AudioOutputStream", (Object)spFileStream);
        Dispatch.put(spVoice, "Volume", (Object)new Variant(speakVoiceMessage.getVolume()));
        Dispatch.put(spVoice, "Rate", (Object)new Variant(speakVoiceMessage.getRate()));
        Dispatch.call(spVoice, "Speak", new Object[] { new Variant((Object)speakVoiceMessage.getMessage()) });
        Dispatch.call(spFileStream, "Close");
        Dispatch.putRef(spVoice, "AudioOutputStream", (Object)null);
        spAudioFormat.safeRelease();
        spFileStream.safeRelease();
        spVoice.safeRelease();
        ax.safeRelease();
    }

    /**
     * 设置语音播报默认值
     * @param speakVoiceMessage
     */
    private void setDefaultValueOfSpeakVoiceMessage(SpeakVoiceMessage speakVoiceMessage){

        if(StringUtils.isBlank(speakVoiceMessage.getVolume())){
            speakVoiceMessage.setVolume(SpeakVoiceConstant.SPEAK_VOICE_DEFAULT_VOLUME);
        }

        if(StringUtils.isBlank(speakVoiceMessage.getRate())){
            speakVoiceMessage.setRate(SpeakVoiceConstant.SPEAK_VOICE_DEFAULT_RATE);
        }

        if(StringUtils.isBlank(speakVoiceMessage.getVoiceAddress())){
            speakVoiceMessage.setVoiceAddress(SpeakVoiceConstant.SPEAK_VOICE_DEFAULT_WAV_ADDRESS);
        }
    }



}
