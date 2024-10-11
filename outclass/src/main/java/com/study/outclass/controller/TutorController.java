package com.study.outclass.controller;


import com.study.outclass.Entity.Users;
import com.study.outclass.dto.UserDto;
import com.study.outclass.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.web.servlet.server.Session;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class TutorController {

    private final UserService userService;
    //FindByIndexNameSessionRepository<? extends Session> sessions;

    @GetMapping("/tutorSignUpForm")
    public  String tutorSignUpForm(Model model){
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
        userDto.setUserNickname(user.get().getUserNickname());
        userDto.setUserName(user.get().getUserName());


        model.addAttribute("userDto", userDto);
        return "tutor/tutorSignUpForm";
    }

}
