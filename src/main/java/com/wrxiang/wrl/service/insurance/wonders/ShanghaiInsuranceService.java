package com.wrxiang.wrl.service.insurance.wonders;

/**
 * @Description 上海医保业务层接口
 * @Author wrxiang
 * @Date 2021/12/12 12:12
 */
public interface ShanghaiInsuranceService {

    /**
     * 调用医保动态库并返回数据
     * @param message
     * @return
     */
    String sendMessage(String message);

}
