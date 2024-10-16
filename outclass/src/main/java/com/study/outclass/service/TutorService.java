package com.study.outclass.service;

import com.study.outclass.Entity.TutorSignUp;
import com.study.outclass.Entity.Users;
import com.study.outclass.constant.Role;
import com.study.outclass.repository.TutorRepository;
import com.study.outclass.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TutorService {

    private final TutorRepository tutorRepository;
    private final UserRepository userRepository;

    // 강사 신규 가입처리
    @Transactional
    public TutorSignUp saveTutor(TutorSignUp tutorSignUp) {
        // 멤버가 있는지 조회
        validateDuplicationUser(tutorSignUp);
        TutorSignUp tutorSignUp1 = tutorRepository.save(tutorSignUp);  //강사 가입
        // 회원정보의 Role을 TUTER로 변경
        Users users = userRepository.
                      findByuserNo(tutorSignUp.getUserNo()).orElse(null);
        users.changeRole(Role.TUTER);

        return tutorSignUp1 ;
    }

    // 강사번호로  강사 정보 가져오기
    public Optional<TutorSignUp> findbytNo(Long tNo){

        return tutorRepository.findBytNo(tNo) ;
    }


    private void validateDuplicationUser(TutorSignUp tutorSignUp) {

        Optional<TutorSignUp> findUser = tutorRepository.findByUserNo(tutorSignUp.getUserNo());
        if(findUser.isPresent()){
            throw  new IllegalArgumentException("이미 존재하는 강사ID입니다.");
        }
    }

}
