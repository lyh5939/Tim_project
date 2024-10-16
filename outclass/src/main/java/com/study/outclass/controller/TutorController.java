package com.study.outclass.controller;


import com.study.outclass.Entity.TutorSignUp;
import com.study.outclass.Entity.Users;
import com.study.outclass.dto.TutorSignUpDto;
import com.study.outclass.dto.UserDto;
import com.study.outclass.service.TutorService;
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

import java.util.Optional;

@Controller
@Slf4j
public class TutorController {
    @Autowired
    private  TutorService tutorService;
    @Autowired
    private  UserService userService;

    //FindByIndexNameSessionRepository<? extends Session> sessions;

    @GetMapping("/tutorNewAdd")
    public String tutorNewAdd(Model model) {

        // 로그인된 email를 통해 사용자 번호 추출

        //사용자번호로  강사번호 추출

        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        String userEmail = "";
        if (authentication != null) {
            userEmail = authentication.getName();  // Session정보중  username에 해당값을 가져옴
        }

        Optional<Users> user = userService.findByemail(userEmail);
        Long tNo = user.get().getUserNo();

        Optional<TutorSignUp> tutorSignUp = tutorService.findbytNo(tNo);
        TutorSignUpDto dto = new TutorSignUpDto();
        dto.setTNo(tutorSignUp.get().getTNo());
        log.info("===> tNo : " + tutorSignUp.get().getTNo());
        dto.setUserNo(tutorSignUp.get().getUserNo());

        model.addAttribute("dto" , dto);
        return "tutor/tutorNewAdd";

    }

    @GetMapping("/tutorSignUpForm")
    public String tutorSignUpForm(Model model) {
        // 로그인된 사용자 정보 가져오기


        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        String userEmail = "";
        if (authentication != null) {
            userEmail = authentication.getName();  // Session정보중  username에 해당값을 가져옴
        }

        log.info("===> userEmail : " + userEmail);

        Optional<Users> user = userService.findByemail(userEmail);

        UserDto userDto = new UserDto();
        userDto.setUserNo(user.get().getUserNo());
        userDto.setUserEmail(user.get().getUserEmail());
        userDto.setUserNickname(user.get().getUserNickname());
        userDto.setUserName(user.get().getUserName());


        model.addAttribute("userDto", userDto);
        return "tutor/tutorSignUpForm";
    }

    @PostMapping("/tutorSignUpForm")
    public String tutorSignUpForm(TutorSignUpDto tutorSignUpDto, Model model) {

        log.info("===> "+ tutorSignUpDto.toString());
        // 폼에서 받아온 데이터를 DB에 저장한다.
        // 상사 가입실행
        try {
            TutorSignUp tutorSignUp = TutorSignUp.createTutorSignUp(tutorSignUpDto);
            TutorSignUp t = tutorService.saveTutor(tutorSignUp);
            // 회원가입 성공시 메인페이로 이동...
            model.addAttribute("tutorDto", t);
            return "tutor/tutorNewAdd";
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "tutor/tutorSignUpForm";
        }

    }
    

}
