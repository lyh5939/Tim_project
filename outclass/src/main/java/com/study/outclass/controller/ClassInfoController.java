package com.study.outclass.controller;

import com.study.outclass.Entity.ClassInfo;
import com.study.outclass.Entity.TutorSignUp;
import com.study.outclass.Entity.Users;
import com.study.outclass.dto.ClassInfoDto;
import com.study.outclass.service.ClassInfoService;
import com.study.outclass.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ClassInfoController {

    private final ClassInfoService classInfoService;
    private final UserService userService;

    @GetMapping("/classInfo")
    public String classInfo(Model model){
        //로그인된 사용자 버호 가져오기
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        String userEmail = "";
        if (authentication != null) {
            userEmail = authentication.getName();  // Session정보중  username에 해당값을 가져옴
        }

        Optional<Users> user = userService.findByemail(userEmail);
        Long userNo = user.get().getUserNo();
        // 회원번호로 강의 가져오기
        ClassInfo classInfo  =  classInfoService.findbytNo(userNo);
        ClassInfoDto dto = new ClassInfoDto();
        if (!classInfo.equals(null)){
            dto.setUserNo(classInfo.getUserNo());
            dto.setClassInfoBeforeKnowledge(classInfo.getClassInfoBeforeKnowledge());
            dto.setClassInfoLevel(classInfo.getClassInfoLevel());
            dto.setClassInfoNo(classInfo.getClassInfoNo());
            dto.setClassInfoStudy(classInfo.getClassInfoStudy());
            dto.setClassInfoCategory(classInfo.getClassInfoCategory());
            dto.setClassInfoSummery(classInfo.getClassInfoSummery());
            dto.setClassInfoTitle(classInfo.getClassInfoTitle());
        }
        model.addAttribute("dto" , dto);

        return "classes/classNewAdd";
    }
    @PostMapping("/classInfoNewAdd")
    public String classInfoPost(ClassInfoDto classInfoDto, Model model){

        log.info("===> "+classInfoDto.toString());

        //로그인된 사용자 버호 가져오기
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        String userEmail = "";
        if (authentication != null) {
            userEmail = authentication.getName();  // Session정보중  username에 해당값을 가져옴
        }
        Optional<Users> user = userService.findByemail(userEmail);
        Long userNo = user.get().getUserNo();

        classInfoDto.setUserNo(userNo);

        try {
            ClassInfo classInfo = ClassInfo.createClassInfo(classInfoDto);
            ClassInfo dto = classInfoService.saveClassInfo(classInfo);
            // 회원가입 성공시 메인페이로 이동...
            model.addAttribute("dto", dto);
            return "classes/classCurri";
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "classes/classNewAdd";
        }



    }

    @GetMapping("/classCurri")
    public String classCurri(){

        return "classes/classCurri";
    }

}
