package com.study.outclass.controller;


import com.study.outclass.Entity.Users;
import com.study.outclass.config.AuditConfig;
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
import java.util.Optional;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private  UserService userService;
    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private  AuditConfig auditConfig;

    @GetMapping("/login")
    public  String login(){
       return "user/userLoginForm";
    }

    @GetMapping(value = "/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        return "user/userLoginForm";
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

    @GetMapping("/tutorSignUp")
    public  String tutorSignUp(Model model){
        // 로그인된 사용자 정보 가져오기

        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        String userEmail = "";
        if(authentication != null){
            userEmail = authentication.getName();  // Session정보중  username에 해당값을 가져옴
        }

        log.info("===> userEmail : " + userEmail );

        Optional<Users> user = userService.findByemail(userEmail);

        UserDto userDto = new UserDto();
        userDto.setUserNo(user.get().getUserNo());
        userDto.setUserEmail(user.get().getUserEmail());


        model.addAttribute("userDto", userDto);
        return "user/tutorSignUp";
    }


}
