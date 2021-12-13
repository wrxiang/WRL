package com.wrxiang.wrl.controller.insurance.wonders;

import com.wrxiang.wrl.service.insurance.wonders.ShanghaiInsuranceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 上海医保业务控制层
 * @Author wrxiang
 * @Date 2021/12/12 12:17
 */
@RestController
@RequestMapping("/insurance/wonders")
@Api(tags = "上海万达信息医保接口")
public class ShanghaiInsuranceController {

    @Autowired
    private ShanghaiInsuranceService shanghaiInsuranceService;

    /**
     * 发送消息
     * @param message
     * @return
     */
    @PostMapping(value = { "/sendMessage" }, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    @ApiOperation(value="医保接口调用", notes = "上海市医保动态库是32位,需要使用32位jdk，并将动态库文件放到jdk\\bin目录")
    public String sendMessage(@ApiParam(name="message", value = "消息入参") @RequestBody final String message){

        return shanghaiInsuranceService.sendMessage(message);
    }


}
