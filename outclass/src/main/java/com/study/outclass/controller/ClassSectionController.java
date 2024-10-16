package com.study.outclass.controller;


import com.study.outclass.Entity.ClassInfo;
import com.study.outclass.Entity.ClassSection;
import com.study.outclass.Entity.Users;
import com.study.outclass.dto.ClassSectionDto;
import com.study.outclass.service.ClassInfoService;
import com.study.outclass.service.ClassSectionService;
import com.study.outclass.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ClassSectionController {

    private final ClassSectionService classSectionService;
    private final UserService userService;
    private final ClassInfoService classInfoService;

    @GetMapping("/classSection")
    public String classSection(  Model model){

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

        // 회원번호로 강의번호 가져오기
        ClassInfo classNo = classInfoService.findbytNo(userNo);

        Long tNo = classNo.getClassInfoNo();  //강의번호
        log.info("====> " + tNo);

        List<ClassSection> list = classSectionService.findbyClassNo(tNo);

        log.info("===>"+ list.toString());

        model.addAttribute("dto" , list);
        model.addAttribute("classNo", tNo);
        return "classes/classCurri";

    }

    @PostMapping("/classSectionAdd")
    public String classSectionAdd(ClassSectionDto classSectionDto, Model model){


        classSectionDto.setClassSectionOpen("OPEN");
        log.info("====> classSectionDto : "+classSectionDto.toString());
         // 섹션 저장하기
            ClassSection classSection = ClassSection.createClassSection(classSectionDto);
            classSectionService.saveClassSection(classSection);

         // 섹션 리스트 가져오기
         List<ClassSection> list = classSectionService.findbyClassNo(classSectionDto.getClassNo());

         log.info("===>"+ list.toString());


            model.addAttribute("dto" , list);


        return "classes/classCurri";
    }

}
