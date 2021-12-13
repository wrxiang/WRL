package com.wrxiang.wrl.service.insurance.wonders;

import com.sun.jna.Library;
import com.sun.jna.Pointer;

/**
 * @Description 上海市医保动态库调用
 * @Author wrxiang
 * @Date 2021/12/12 12:10
 */
public interface InvokeShanghaiInsuranceDll extends Library {

    /**
     * 医保动态库所提供的方法
     * @param startParams 固定参数
     * @param input 入参
     * @param pointer 出参
     * @return 返回值
     */
    String SendRcv4(String startParams, String input, Pointer pointer);

    /**
     * 医保动态库所提供的方法
     * @param startParams 固定参数
     * @param input 入参
     * @param pointer 出参
     * @return 返回值
     */
    Pointer SendRcv4(String startParams, Pointer input, Pointer pointer);

    /**
     * 医保动态库所提供的方法
     * @param startParams 固定参数
     * @param input 入参
     * @param output 出参
     * @return 返回值
     */
    String SendRcv4(String startParams, String input, byte[] output);

    /**
     * 医保动态库所提供的方法
     * @param startParams 固定参数
     * @param input 入参
     * @param output 出参
     * @return 返回值
     */
    String SendRcv4(String startParams, byte[] input, byte[] output);




}
