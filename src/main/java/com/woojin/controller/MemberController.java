package com.woojin.controller;

import com.woojin.dto.UserReq;
import com.woojin.dto.UserRes;
import com.woojin.service.MemberService;

import com.woojin.mapper.*;
import com.woojin.vo.MemberVO;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.Map;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Api(tags = { "Member 관리 API" })
@RestController
@RequestMapping("/members")
public class MemberController {
	private final MemberService memberService;
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@ApiOperation(value = "Hello World 반환", notes = "간단한 인사말을 반환합니다.")
	@GetMapping("/hello")
	public String hello() {
		logger.info("GET /members/hello 요청 도달");
		return "hello";
	}

	@ApiOperation(value = "회원 가입", notes = "회원 가입을 처리합니다.")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "username", value = "회원 이름", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "password", value = "회원 비밀번호", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "email", value = "회원 이메일", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "nickname", value = "회원 아이디", required = true, dataType = "string", paramType = "query"), })
	@PostMapping("/signUp/do")
	public ResponseEntity<String> signUp(@RequestBody MemberVO memberVO) {
		logger.info("Received memberVO: {}", memberVO);

		try {
			memberService.addMember(memberVO);
			return ResponseEntity.ok("회원 가입이 성공적으로 완료되었습니다.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}

	@ApiOperation(value = " 로그인 기능", notes = "로그인을 합니다")
	@PostMapping("/Login")
	public ResponseEntity<String> idcheck(@RequestBody UserReq userReq , HttpSession session) {
		try {
			if (memberService.idcheck(userReq) == true) {
				if(memberService.getFriend(userReq) ==null) {
					String username = memberService.getUser(userReq);
					String nickname = userReq.getNickname();
					session.setAttribute("nickname", nickname);
					
					return ResponseEntity.ok(null);

                
				}else {
					String friend = memberService.getFriend(userReq);
					return ResponseEntity.ok(friend);
                 
				}
				
 				
                
			} else {
				 
				return ResponseEntity.ok("로그인 안돼");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());

		}	
	}
	@PostMapping("/addfriend")
	public ResponseEntity<String> addfriend(@RequestBody UserReq userReq , HttpSession session) {
		
		try {
			
			
			//String currentnickname = (String) session.getAttribute("nickname");
			//System.out.print("쿠키 아이디 " + currentnickname +"dd");
			
			memberService.addfriend( userReq.getFriendnickname(),userReq.getNickname());
			
			String friendname = memberService.addgetFriend(userReq.getNickname());
			System.out.println("이름 " + friendname);
			
			
			return ResponseEntity.ok(friendname);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
			
			
		}
		
	}

	@DeleteMapping("/members")
	public void deleteAllMembers() {
		memberService.deleteAllMembers();
	}

	@PostMapping("/user/insertImage")
	public ResponseEntity<Integer> insertImage(@RequestParam("imageFile") MultipartFile imageFile,
			@RequestBody UserReq userReq) {
		int resultimage = memberService.insertImage(imageFile, userReq);

		return ResponseEntity.ok(resultimage);
	}


}
