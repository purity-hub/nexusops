package com.nexusops.monitor.controller;

import com.nexusops.common.web.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 监控控制器
 */
@RestController
@RequestMapping("/monitor")
public class MonitorController {

    @GetMapping("/status")
    public Result<String> status() {
        return Result.success("Monitor status endpoint");
    }
}
