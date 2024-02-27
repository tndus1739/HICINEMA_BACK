package com.himedia.hicinema.member;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import oracle.jdbc.proxy.annotation.Post;


@RequiredArgsConstructor              //  final로 선언된 필드에 대한 생성자를 자동으로 생성
@Controller
@RequestMapping("/member")
public class MemberController {

	// 생성자 주입
	private final MemberService  memberService;
	
	// 회원가입 페이지 출력 요청
	// http://localhost:8010/member/register 
	@GetMapping("/register")
	public String register (MemberCreateForm memberCreateForm) {
		
		return "member/register";
	}
	
	// BindingResult : 유효성 검증결과를 받아올 때
	
	
	
	// 회원가입 =================
	
	@PostMapping("/register")
	public String register (@Valid MemberCreateForm memberCreateForm , BindingResult bindingResult) {
		
		System.out.println("======회원가입==========");
		System.out.println(memberCreateForm.getName());
		System.out.println(memberCreateForm.getEmail());
		System.out.println(memberCreateForm.getPassword1());
		System.out.println(memberCreateForm.getPassword2());
		System.out.println(memberCreateForm.getPhone());
		System.out.println("======회원가입==========");
		
		
		
		if(bindingResult.hasErrors()) {
			return "member/register";
		}
		
		// 2개의 비밀번호가 일치하지 않을 때 (필드명, 오류 코드, 오류 메시지)
		if(!memberCreateForm.getPassword1().equals(memberCreateForm.getPassword2())) {    
			bindingResult.rejectValue("password2", "passwordInCorrect", 
                    "비밀번호가 일치하지 않습니다.");
            return "member/register";
			
		}
		
		try {
				memberService.create(
									memberCreateForm.getName(),
									memberCreateForm.getMember_id(),
									memberCreateForm.getPassword1(), 
									memberCreateForm.getEmail(),
									memberCreateForm.getPhone()
									);
		} catch(DataIntegrityViolationException e) {
			
			e.printStackTrace();
			bindingResult.reject("signupFailed", "이미 등록된 이용자입니다.");
			return "member/register";
		} catch(Exception e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed", e.getMessage());  // e.getMessage : 구체적인 오류메세지
			return "member/register";
		}
		
        return "redirect:/";
	}
	
//	@PostMapping("/register")
//	public String save(@ModelAttribute MemberDTO memberDTO) {
//		System.out.println ("성공" );
//		System.out.println("memberDTO = " + memberDTO);
//		
//		memberService.create(memberDTO);
//		
//		return "member/login";
//	}
//	
//	
	
	
	 // 로그인 ===================
	
	  @GetMapping("/login")
	    public String login() {
	        return "member/login";
	    }
	
}
