package com.study.outclass.dto;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassInfoDto {


    private Long classInfoNo;    //강의번호


    private Long userNo; // 회원번호

    private String classInfoTitle;     // 강의제목


    private String classInfoSummery;     // 강의 요약


    private String classInfoStudy;      // 이런걸 배워요


    private String classInfoBeforeKnowledge;      // 사전지식


    private String classInfoCategory;        // 강의 분류  여러게 입력가능

    private String classInfoLevel;  // 강의수준

}
