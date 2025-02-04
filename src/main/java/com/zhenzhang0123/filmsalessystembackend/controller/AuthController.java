package com.zhenzhang0123.filmsalessystembackend.controller;

import com.zhenzhang0123.filmsalessystembackend.dto.AuthRequest;
import com.zhenzhang0123.filmsalessystembackend.dto.AuthResponse;
import com.zhenzhang0123.filmsalessystembackend.dto.RegisterRequest;
import com.zhenzhang0123.filmsalessystembackend.dto.Response;
import com.zhenzhang0123.filmsalessystembackend.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("//auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Response<AuthResponse>> register(@Valid @RequestBody RegisterRequest request) {
        AuthResponse response = authService.register(request);
        return ResponseEntity.ok(Response.success(response));
    }

    @PostMapping("/login")
    public ResponseEntity<Response<AuthResponse>> login(@Valid @RequestBody AuthRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(Response.success(response));
    }
}
