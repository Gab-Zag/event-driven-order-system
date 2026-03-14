package com.gab.event_driven_order_system.user.controller;

import com.gab.event_driven_order_system.user.dto.getme.GetMeDTO;
import com.gab.event_driven_order_system.user.dto.getme.GetMeReturnDTO;
import com.gab.event_driven_order_system.user.dto.login.LoginResponseDTO;
import com.gab.event_driven_order_system.user.dto.login.UserLoginDTO;
import com.gab.event_driven_order_system.user.dto.register.UserRegisterDTO;
import com.gab.event_driven_order_system.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid UserRegisterDTO data){

        return ResponseEntity.ok(userService.registerUser(data));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUser (@RequestBody @Valid UserLoginDTO data){
        return ResponseEntity.ok(userService.loginUser(data));
    }

    @GetMapping("/me")
    public ResponseEntity<GetMeReturnDTO> getMe(@RequestBody @Valid GetMeDTO data){
        return ResponseEntity.ok(userService.getMe(data));
    }
}
