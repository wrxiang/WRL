package com.wrxiang.wrl.controller.speaker;

import com.wrxiang.wrl.domain.dto.SpeakVoiceMessage;
import com.wrxiang.wrl.service.speaker.SpeakVoiceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 语音播报控制层
 * @Author wrxiang
 * @Date 2021/12/12 14:01
 */
@RestController
@RequestMapping("/Sapi")
@Api(tags = "Sapi语音播报")
public class SpeakVoiceController {


    @Autowired
    private SpeakVoiceService speakVoiceService;

    /**
     * 语音播报
     * @param speakVoiceMessage
     */
    @PostMapping("/speakVoice")
    @ApiOperation("播放语音")
    public void speak(@RequestBody  SpeakVoiceMessage speakVoiceMessage){
        speakVoiceService.speak(speakVoiceMessage);
    }

}
