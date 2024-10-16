package com.study.outclass.service;

import com.study.outclass.Entity.ClassSection;
import com.study.outclass.repository.ClassSectiionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClassSectionService {

    private final ClassSectiionRepository classSectiionRepository;

    // 기본강의 데이터 입력
    public ClassSection saveClassSection(ClassSection classSection){
        // 강의정보 DB에 저장
        return  classSectiionRepository.save(classSection);
    }

    // 강의 번호 가져오기
    public List<ClassSection> findbyClassNo(Long classNo){

        return classSectiionRepository.findByClassNo(classNo) ;
    }

}
