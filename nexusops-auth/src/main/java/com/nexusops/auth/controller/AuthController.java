package com.nexusops.auth.controller;

import com.nexusops.common.web.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 认证控制器
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/login")
    public Result<String> login() {
        return Result.success("Login endpoint");
    }
}
