package com.study.outclass.service;


import com.study.outclass.Entity.UserImg;
import com.study.outclass.repository.UserImgRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserImgService {

   @Value("${userImgLocation}")
   private String userImgLocation;

   private final UserImgRepository userImgRepository;
   private final FileService       fileService;

   public void  saveUserImg(UserImg userImg, MultipartFile userImgFile) throws IOException {
       String oriImgName = userImgFile.getOriginalFilename();   // 오리지날 파일이름 저장
       String imgName="";
       String imgUrl="";

       if(!StringUtils.isEmpty(oriImgName)){    //파일이름 있는 경우
           imgName = fileService.uploadFile( userImgLocation ,oriImgName, userImgFile.getBytes());
           imgUrl = "/images/user" + imgName;
       }

       userImg.updateUserImg(oriImgName, imgName, imgUrl);
       //디비에 저장
       userImgRepository.save(userImg);

   }
}
