package com.study.outclass.repository;

import com.study.outclass.Entity.UserImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserImgRepository extends JpaRepository<UserImg , Long > {

}
