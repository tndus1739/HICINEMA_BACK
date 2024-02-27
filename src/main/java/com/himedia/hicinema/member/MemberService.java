package com.himedia.hicinema.member;

import java.time.LocalDateTime;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class MemberService {

	 // 클래스에 final로 선언된 필드들에 대한 생성자를 자동으로 생성
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;

	
	public Member create(String name, String member_id, String password ,
			String email, String phone) {
        Member mem = new Member();
        
        mem.setName(name);
        mem.setMember_id(member_id);
        mem.setPassword(passwordEncoder.encode(password));
        mem.setEmail(email);
        mem.setPhone(phone);
        mem.setRegdate(LocalDateTime.now());
//        mem.setDelDate(delDate);
//        mem.setStatus(status);
        this.memberRepository.save(mem);
        return mem;
        
//        , LocalDateTime regdate , 
//		LocalDateTime delDate , String status 
//        
        
        // BCryptPasswordEncoder : 암호화하여 비밀번호를 저장
        // 객체를 직접 new로 생성하는 방식보다는 PasswordEncoder 객체를 빈으로 등록해서 사용하는 것이 좋음
//        
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();    
//        mem.setPassword(passwordEncoder.encode(password));
        
    }
	
	
	
	
}
