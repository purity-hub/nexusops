package com.nexusops.app.controller;

import com.nexusops.common.web.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 应用控制器
 */
@RestController
@RequestMapping("/app")
public class AppController {

    @GetMapping("/list")
    public Result<String> list() {
        return Result.success("App list endpoint");
    }
}
