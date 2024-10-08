package com.study.outclass.repository;

import com.study.outclass.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {

    //이메일로 레코드 찾아오기
    Optional<Users> findByuserEmail(String userEmail);
    // 닉네임 레코드 찾아오기
    Optional<Users> findByuserNickname(String userNickname);
}
