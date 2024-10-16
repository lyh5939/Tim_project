package com.study.outclass.repository;

import com.study.outclass.Entity.TutorSignUp;
import com.study.outclass.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TutorRepository  extends JpaRepository<TutorSignUp, Long> {


    //회원번호로 레코드 찾아오기
    Optional<TutorSignUp> findByUserNo(Long userNo);
    Optional<TutorSignUp> findBytNo(Long tNo);
}
