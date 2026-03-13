package com.nexusops.alert.controller;

import com.nexusops.common.web.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 告警控制器
 */
@RestController
@RequestMapping("/alert")
public class AlertController {

    @GetMapping("/rules")
    public Result<String> rules() {
        return Result.success("Alert rules endpoint");
    }
}
