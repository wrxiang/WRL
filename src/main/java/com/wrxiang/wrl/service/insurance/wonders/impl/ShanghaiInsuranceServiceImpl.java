package com.wrxiang.wrl.service.insurance.wonders.impl;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.wrxiang.wrl.constant.GlobalConstant;
import com.wrxiang.wrl.service.insurance.wonders.InvokeShanghaiInsuranceDll;
import com.wrxiang.wrl.service.insurance.wonders.ShanghaiInsuranceService;
import com.wrxiang.wrl.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 上海医保业务层实现类
 * @Author wrxiang
 * @Date 2021/12/12 12:14
 */
@Service
@Slf4j
@Lazy
public class ShanghaiInsuranceServiceImpl implements ShanghaiInsuranceService {

    private static InvokeShanghaiInsuranceDll invokeShanghaiInsuranceDll;

    /**
     * 初始化医保调用类
     */
    public void initInvokeShanghaiInsuranceDll(){

        String jnaEncode = System.getProperty(GlobalConstant.JNA_ENCODING_PROPERTY);
        if(StringUtils.isBlank(jnaEncode)){
            System.setProperty(GlobalConstant.JNA_ENCODING_PROPERTY, GlobalConstant.WINDOWS_ENCODING);
        }

        //实例化JNA接口
        if(invokeShanghaiInsuranceDll == null){
            invokeShanghaiInsuranceDll = Native.load("SendRcv4", InvokeShanghaiInsuranceDll.class);
        }
    }


    /**
     * 调用医保动态库并返回数据
     *
     * @param message
     * @return
     */
    @Override
    public String sendMessage(String message) {

        Pointer pointer0 = new Memory((message.length() + 1) * 2);
        Pointer pointer1 = new Memory(0x102400);

        pointer0.setString(0, message);

        String result;
        try{
            this.initInvokeShanghaiInsuranceDll();

            invokeShanghaiInsuranceDll.SendRcv4("12345678", pointer0, pointer1);
            result = pointer1.getString(0, GlobalConstant.WINDOWS_ENCODING);
        }catch (Throwable e){
            log.error("上海市医保接口调用异常, 异常信息：", e);

            //由于上海市五期医保接口返回的是JSON字符串，发生异常时构造自定义错误码的JSON字符串返回，保证调用方的统一
            result = buildFailMessage(e.getMessage());
        }finally {
            this.freePointer(pointer0);
            this.freePointer(pointer1);
        }
        return result;
    }

    /**
     * 构造自定义错误码的消息返回值
     * @param exceptionMessage
     * @return
     */
    private String buildFailMessage(String exceptionMessage){

        Map<String,String> resultMap = new HashMap<>(16);
        resultMap.put("xxfhm", "500");
        resultMap.put("fhxx", String.format("上海市医保动态库调用异常, 异常原因: %s", exceptionMessage));

        String result = JsonUtil.writeValueAsString(resultMap);
        return result;
    }


    /**
     * 释放指针
     * @param pointer
     */
    private void freePointer(Pointer pointer){
        long peer = Pointer.nativeValue(pointer);
        Native.free(peer);
        Pointer.nativeValue(pointer, 0);
    }
}
