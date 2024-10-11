package com.study.outclass.dto;

import java.time.LocalDateTime;

import com.study.outclass.Entity.Users;
import com.study.outclass.constant.Role;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long          userNo;           // 회원번호

    @NotEmpty(message = "이메일을 입력하세요")
    @Email(message = "이메일 형기에 맞게 입력하세요")
    private String        userEmail;        // 회원 메일

    @NotBlank(message = "이름을 입력하세요")
    private String        userName;         // 회원 이름

    @NotEmpty(message = "비번을 입력하세요")
    @Length(min=6 , message = "비번은 6글자이상 입력하세요")
    private String        userPw;           // 패스워드

    @NotEmpty(message = "닉네임을 입력하세요")
    private String        userNickname;             // 별명

    private int           userBlackList;            // 정지상태...
    private LocalDateTime userJoinDate;             // 가입일자
    private LocalDateTime userLastAccess;           // 마지막 접속일시
    private String        userGrade;                // 회원 등급

    private int           userOpen;                 // 정시 또는 탈퇴시 0
    private LocalDateTime userBlackDate;            // 정지 날짜
    private int           userBlackReason;          // 정지 이유
    private LocalDateTime userWithdraw;             // 탈퇴 날짜
    private Role role;

//    private static ModelMapper modelMapper = new ModelMapper();
//    public Users createUser(){
//        return modelMapper.map(this, Users.class);
//    }
//    public static UserFormDto entityToDto(Users user){
//        return modelMapper.map(user, UserFormDto.class);
//    }
}
