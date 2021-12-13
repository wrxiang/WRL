package com.wrxiang.wrl.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Description 语音播报入参类
 * @Author wrxiang
 * @Date 2021/12/12 14:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@ApiModel(value = "语音播报入参")
public class SpeakVoiceMessage {

    /**
     * 音量，取值范围为0到100。数值越大，音量越大
     */
    @ApiModelProperty("音量，取值范围为0到100。数值越大，音量越大")
    private String volume;

    /**
     * 语音朗读速度，取值范围为-10到+10。数值越大，速度越快
     */
    @ApiModelProperty("语音朗读速度，取值范围为-10到+10。数值越大，速度越快")
    private String rate;

    /**
     * 语音信息
     */
    @ApiModelProperty(value="语音信息", required = true)
    private String message;

    /**
     * 生成语音文件地址
     */
    @ApiModelProperty("生成语音文件地址")
    private String voiceAddress;



}
