package com.study.outclass.dto;


import com.study.outclass.Entity.UserImg;
import lombok.*;
import org.modelmapper.ModelMapper;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserImgDto {

    private Long  id;           // 사진 번호

    private Long   userNo;      // 사용자 번호

    private String imgName;     // 이미지 파일명(중복x)

    private String oriImgName;  // 원본이미지 파일명

//    private static ModelMapper modelMapper = new ModelMapper();
//
//    public static UserImgDto entityToDto(UserImg userImg){
//        return modelMapper.map(userImg, UserImgDto.class);
//    }
}
