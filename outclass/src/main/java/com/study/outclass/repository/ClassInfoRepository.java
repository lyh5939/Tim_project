package com.study.outclass.repository;

import com.study.outclass.Entity.ClassInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassInfoRepository extends JpaRepository<ClassInfo, Long> {

    //회원번호로 레코드 찾아오기
    ClassInfo findByUserNo(Long userNo);


}
