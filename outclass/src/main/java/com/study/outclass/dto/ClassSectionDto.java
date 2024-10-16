package com.study.outclass.dto;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassSectionDto {

    private Long classSectionNo;    //섹션번호


    private Long classNo; // 강의번호

    private String classSectionTitle;     // 강의제목


    private String classSectionOpen;     // 노출표시

}
