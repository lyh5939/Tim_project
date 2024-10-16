package com.study.outclass.service;

import com.study.outclass.Entity.ClassInfo;
import com.study.outclass.repository.ClassInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClassInfoService {

    private final ClassInfoRepository classInfoRepository;

    // 기본강의 데이터 입력
    public ClassInfo saveClassInfo(ClassInfo classInfo){
        // 강의정보 DB에 저장
        ClassInfo classInfo1 = classInfoRepository.save(classInfo);
        return classInfo1;
    }

    // 강사번호로  강사 정보 가져오기
    public ClassInfo findbytNo(Long userNo){

        return classInfoRepository.findByUserNo(userNo) ;
    }


}
