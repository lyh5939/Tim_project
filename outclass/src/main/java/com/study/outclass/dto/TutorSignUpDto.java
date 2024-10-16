package com.study.outclass.dto;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TutorSignUpDto {


    private Long tNo;    //강사번호

    private Long userNo; // 회원번호

    private String tCareer;     // 경력 / 저서입력

    private String tIntroduce;      // 소개글


    private String tYoutube;        //유트브 주소

    private String tInstargram;       // 인스타그램 주소

    private int tOpen;   // 강사 상태 ( 0: 허가 , 1: 불허)

}
