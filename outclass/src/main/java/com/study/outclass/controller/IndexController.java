package com.study.outclass.controller;


import com.study.outclass.config.AuditConfig;
import com.study.outclass.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class IndexController {

    @GetMapping("/")
    public String index(Model model) {

        //로그인시  사용자의 닉네임과 , 사진,  정보를 넘겨준다.
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        String userEmail = "";
        if (authentication != null) {
            userEmail = authentication.getName();  // Session정보중  username에 해당값을 가져옴
        }

        log.info("====> session : " + userEmail);
        model.addAttribute("userEmail", userEmail);
        return "index";

    }
}
