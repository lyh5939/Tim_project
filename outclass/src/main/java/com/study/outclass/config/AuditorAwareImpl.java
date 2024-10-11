package com.study.outclass.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl  implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {

       Authentication authentication = SecurityContextHolder
                                          .getContext()
                                          .getAuthentication();
       String userEmail = "";
       if(authentication != null){
           userEmail = authentication.getName();  // Session정보중  username에 해당값을 가져옴
       }
       return userEmail.describeConstable();  // Optional 데이터 넘길때 사용하는 방법
    }
}
