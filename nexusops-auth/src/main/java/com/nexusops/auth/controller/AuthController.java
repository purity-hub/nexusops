package com.nexusops.auth.controller;

import com.nexusops.auth.service.AuthService;
import com.nexusops.common.web.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 认证控制器
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public Result<Map<String, String>> login(@RequestBody Map<String, String> loginRequest) {
        String username = loginRequest.get("username");
        String password = loginRequest.get("password");

        String token = authService.login(username, password);

        return Result.success(Map.of("token", token));
    }

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public Result<Void> register(@RequestBody Map<String, String> registerRequest) {
        String username = registerRequest.get("username");
        String password = registerRequest.get("password");
        String email = registerRequest.get("email");

        authService.register(username, password, email);

        return Result.success(null);
    }
}
