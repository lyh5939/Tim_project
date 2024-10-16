package com.study.outclass.Entity;

import com.study.outclass.dto.TutorSignUpDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Optional;


@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity                  
public class TutorSignUp  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tNo;    //강사번호

    @Column(nullable = false)
    private Long userNo; // 회원번호

    @Lob
    @Column(nullable = false)
    private String tCareer;     // 경력 / 저서입력
    
    @Lob
    @Column(nullable = false)
    private String tIntroduce;      // 소개글

    @Column(nullable = false)
    private String tYoutube;        //유트브 주소

    @Column(unique = true, nullable = false)
    private String tInstargram;       // 인스타그램 주소

    @Column(nullable = false)
    private int tOpen;   // 강사 상태 ( 0: 허가 , 1: 불허)


    public static TutorSignUp createTutorSignUp(TutorSignUpDto tutorSignUpDto) {

        return TutorSignUp.builder()
                .userNo(tutorSignUpDto.getUserNo())
                .tCareer(tutorSignUpDto.getTCareer())
                .tIntroduce(tutorSignUpDto.getTIntroduce())
                .tYoutube(tutorSignUpDto.getTYoutube())
                .tInstargram(tutorSignUpDto.getTInstargram())
                .build();
    }

}