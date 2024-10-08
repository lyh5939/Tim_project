package com.study.outclass.controller;


import com.study.outclass.Entity.Users;
import com.study.outclass.dto.UserDto;
import com.study.outclass.repository.UserRepository;
import com.study.outclass.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public  String login(){
       return "user/userLoginForm";
    }

    @GetMapping(value = "/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        return "/user/userLoginForm";
    }

    @GetMapping("/logout")
    public String performLogout(HttpServletRequest request, HttpServletResponse response) {
        // .. perform logout
        log.info("===============> logout");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/";
    }


    @GetMapping("/signUp")
    public  String singUp(Model model){
        model.addAttribute("userDto", new UserDto());
        return "user/signUp";
    }

    @PostMapping("/signUpAction")
    public  String signUpAction(@Valid UserDto userDto,
                                BindingResult bindingResult,
                                Model model){
        // 넘겨받은 데이터의 이메일과 닉네임을 보내서 중복여부 판단해 오기
        if(bindingResult.hasErrors()){
            return "user/signUp";
        }
        // 회원가입실행
        try{
            Users users = Users.createUser(userDto, passwordEncoder);
            Users u = userService.saveUser(users);
            // 회원가입 성공시 메인페이로 이동...
            model.addAttribute("userDto", new UserDto());
            return "redirect:/";
        }catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "user/signUp";
        }

    }
}
