package com.study.outclass.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_img_id")
    private Long  id;           // 사진 번호
    
    private Long   userNo;      // 사용자 번호

    private String imgName;     // 이미지 파일명(중복x)

    private String oriImgName;  // 원본이미지 파일명
    
    private String imgUrl;      // 이미지 경로


    public  void updateUserImg(String imgName,String oriImgName,
                               String imgUrl){
        this.imgName = imgName;
        this.imgUrl = imgUrl;
        this.oriImgName = oriImgName;
    }

}
