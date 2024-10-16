package com.study.outclass.Entity;


import com.study.outclass.dto.ClassInfoDto;
import com.study.outclass.dto.TutorSignUpDto;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ClassInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classInfoNo;    //강의번호

    @Column(nullable = false)
    private Long userNo; // 회원번호

    @Column(nullable = false)
    private String classInfoTitle;     // 강의제목

    @Column(nullable = false)
    private String classInfoSummery;     // 강의 요약

    @Lob
    @Column(nullable = false)
    private String classInfoStudy;      // 이런걸 배워요

    @Lob
    @Column(nullable = false)
    private String classInfoBeforeKnowledge;      // 사전지식

    @Column(nullable = false)
    private String classInfoCategory;        // 강의 분류  여러게 입력가능

    @Column(nullable = false)
    private String classInfoLevel;       // 강의수준


    public static  ClassInfo createClassInfo(ClassInfoDto classInfoDto) {

        return ClassInfo.builder()
                .userNo(classInfoDto.getUserNo())
                .classInfoTitle(classInfoDto.getClassInfoTitle())
                .classInfoStudy(classInfoDto.getClassInfoStudy())
                .classInfoBeforeKnowledge(classInfoDto.getClassInfoBeforeKnowledge())
                .classInfoCategory(classInfoDto.getClassInfoCategory())
                .classInfoSummery(classInfoDto.getClassInfoSummery())
                .classInfoLevel(classInfoDto.getClassInfoLevel())
                .build();
    }
}
