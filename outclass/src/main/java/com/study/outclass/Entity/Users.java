package com.study.outclass.Entity;


import java.time.LocalDateTime;

import com.study.outclass.constant.Role;
import com.study.outclass.dto.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.Builder.Default;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name = "user_no")
    private Long    userNo;        // 회원번호

    @Column(unique = true, nullable = false)
    private String  userEmail;     // 회원 이메일

    @Column(nullable = false)
    private String  userName;      // 회원 이름

    @Column(nullable = false)
    private String  userPw;

    @Column(unique = true , nullable = false)
    private String  userNickname;       // 별명

    @Default
    @Column(nullable = false)
    private int userBlackList=0;  // 정지상태...

    @Default
    @Column(nullable = false)
    @CreatedDate
    private  LocalDateTime userJoinDate=LocalDateTime.now();  //가입일자

    @LastModifiedDate
    @Column(nullable = true)
    private  LocalDateTime userLastAccess;  // 마지막 접속일시

    @Default
    @Column(nullable = false)
    private  String  userGrade="일반";      // 회원 등급

    @Column(nullable = true)
    private  String  userProfileImage;    // 사진

    @Default
    @Column(nullable = false)
    private  int     userOpen = 1;         // 정시 또는 탈퇴시 0

    @Column(nullable = true)
    private  LocalDateTime userBlackDate;   // 정지 날짜
    @Column(nullable = true)

    private  int userBlackReason; // 정지 이유

    @Column(nullable = true)
    private  LocalDateTime userWithdraw;     // 탈퇴 날짜

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Users createUser(UserDto userDto,
                                  PasswordEncoder passwordEncoder){

        return Users.builder()
                .role(Role.USER)
                .userEmail(userDto.getUserEmail())
                //비번 암호화
                .userPw( passwordEncoder.encode(userDto.getUserPw()))
                .userName(userDto.getUserName())
                .userNickname(userDto.getUserNickname())
                .userGrade("일반")
                .userBlackList(0)
                .userBlackReason(0)
                .userJoinDate( LocalDateTime.now())
                .build();
    }

}
