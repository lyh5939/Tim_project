package com.study.outclass.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public  String index(){
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
