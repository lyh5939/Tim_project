package com.study.outclass.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
public class FileService {

    public String uploadFile(String uploadPaht, String originalFileName, byte[] fileData)
            throws IOException {
        UUID uuid = UUID.randomUUID();  // 램덤한 파일이름 만들기...
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));   //확장자명 저장
        log.info("==> 확장자명 "+extension );
        String saveFileName = uuid.toString()+extension;   // 고유한 파일이름 저장
        String fileUploadFullUrl = uploadPaht + "/" + saveFileName;

        FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);
        fos.write(fileData);
        fos.close();

        return saveFileName;
    }

    public void deleteFile(String filePath){
       File deleteFile = new File(filePath);
       if( deleteFile.exists()){  //파일이 존재하면
           deleteFile.delete();   // 파일 삭제
           log.info("===> 파일을 삭제 헸습니다 : "+deleteFile);
       }else {
           log.info("===> 파일이 존재하지 않습니다 : "+deleteFile);
       }
    }
}
