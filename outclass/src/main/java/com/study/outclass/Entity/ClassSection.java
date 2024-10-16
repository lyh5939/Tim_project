package com.study.outclass.Entity;

import com.study.outclass.dto.ClassInfoDto;
import com.study.outclass.dto.ClassSectionDto;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ClassSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classSectionNo;    //섹션번호

    @Column(nullable = false)
    private Long classNo; // 강의번호

    @Column(nullable = false)
    private String classSectionTitle;     // 강의제목

    @Column(nullable = false)
    private String classSectionOpen;     // 노출표시


    public static  ClassSection createClassSection( ClassSectionDto classSectionDto) {

        return ClassSection.builder()
                .classNo(classSectionDto.getClassNo())
                .classSectionTitle(classSectionDto.getClassSectionTitle())
                .classSectionOpen(classSectionDto.getClassSectionOpen())
                .build();
    }

}
