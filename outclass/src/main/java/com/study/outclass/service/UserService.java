package com.study.outclass.service;


import com.study.outclass.Entity.Users;
import com.study.outclass.dto.UserDto;
import com.study.outclass.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

    // @Autowired
    private final UserRepository userRepository;

    public Users saveUser(Users users){
        // 멤버가 있는지 조회
        validateDuplicationUser(users);
        return  userRepository.save(users);  //일반 회원가입

    }

    public Optional<Users> findByemail(String email){
        return userRepository.findByuserEmail(email);
    }

    private void validateDuplicationUser(Users user) {

        Optional<Users> findUser = userRepository.findByuserEmail(user.getUserEmail());
        if(findUser.isPresent()){
           throw  new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }
        Optional<Users> findUser2 = userRepository.findByuserNickname(user.getUserNickname());
        if(findUser2.isPresent()){
            throw new IllegalArgumentException("이미 존재하는 닉네임입니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email)
                                          throws UsernameNotFoundException {
      Users users = userRepository.findByuserEmail(email).orElseThrow(()->
              new UsernameNotFoundException("해당 사용자없습니다."+email));
      log.info("===> [login user ] : " + users.getUserEmail());

      return User.builder()
              .username(users.getUserEmail())
              .password(users.getUserPw())
              .roles(users.getRole().toString())
              .build();
    }
}
