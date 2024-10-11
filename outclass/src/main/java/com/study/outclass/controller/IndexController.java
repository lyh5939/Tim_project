package com.study.outclass.controller;


import com.study.outclass.config.AuditConfig;
import com.study.outclass.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class IndexController {

    private final AuditConfig auditConfig;

    @GetMapping("/")
    public  String index(Model model){

        //로그인시  사용자의 닉네임과 , 사진,  정보를 넘겨준다.
        String userEmail = "";
        userEmail = String.valueOf(auditConfig.auditorAware().getCurrentAuditor());
        log.info("====> session : "+userEmail);
        model.addAttribute("userEmail", userEmail);
        return "index";
    }

    @GetMapping("/tutor")
    public  String tutor(){
        return "tutor/tutorMain";
    }

    @GetMapping("/tutorNewAdd")
    public  String tutorNewAdd(){
        return "tutor/tutorNewAdd";
    }

    @PostMapping("/tutorInfo")
    public  String tutorInfo(){
        return "tutor/tutorCurri";
    }

    @PostMapping("/studyAdd")
    public  String studyAdd(){
        return "tutor/tutorDetail";
    }
    @PostMapping("/studyImage")
    public  String studyImage(){
        return "tutor/tutorCoverImage";
    }
    @PostMapping("/studyQuestion")
    public  String studyQuestion(){
        return "tutor/tutorQuestion";
    }
}
