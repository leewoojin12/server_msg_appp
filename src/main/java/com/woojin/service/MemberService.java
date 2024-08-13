package com.woojin.service;
import org.springframework.jdbc.core.JdbcTemplate; // 삭제 '

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.woojin.mapper.MemberMapper;
import com.woojin.vo.MemberVO;
import com.woojin.dto.*;
import org.springframework.web.multipart.MultipartFile;
import com.woojin.exception.*;



import java.util.Base64;

import java.io.IOException;


@Service
public class MemberService {
	private final MemberMapper memberMapper;

	public MemberService(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}

	@Transactional
	public void addMember(MemberVO memberVO) throws DuplicateEmailException, DuplicateNicknameException {
		if (memberMapper.emailExists(memberVO.getEmail())) {
			throw new DuplicateEmailException("이미 존재하는 이메일입니다.");
		} else if (memberMapper.nicknameExists(memberVO.getNickname())) {
			throw new DuplicateNicknameException("이미 존재하는 아이디입니다.");
		}

		else {
			memberMapper.save(memberVO);
		}
	}
	
	
	
	@Transactional
	public void addfriend(String friendnickname,String nickname) {
		System.out.println("2" + friendnickname + " " + nickname);
		
		MemberVO member= memberMapper.findByfriendnickname(friendnickname ,nickname);
		MemberVO member2 = memberMapper.findByNickname(friendnickname);
		if(member2==null) {
			throw new UserNotFoundException("사용자를찾을수없습니다");
 			
		}else {
			if(member != null ) {
				System.out.print(member);
				System.out.println("11");
			 }else {
				 System.out.println(friendnickname + " " + nickname);
				 
				 memberMapper.addfriend(friendnickname, nickname);
			 }	
		}
	}
	
	
	
	
	
	@Transactional
    public boolean idcheck(UserReq userReq) throws UserNotFoundException {
		
		MemberVO member = memberMapper.findByNickname(userReq.getNickname());
 
		MemberVO member2 = memberMapper.passwordfind(userReq.getPassword());


		if(member==null) {
			

			throw new UserNotFoundException("사용자를찾을수없습니다");
			
		}else if(member2==null) {
			throw new Nopepassword("비밀번호가 틀림  ");
			
			
		}
		else {
			return true;
			
		}
		
	}
	
	
	 @Autowired
	    private JdbcTemplate jdbcTemplate;

	    public void deleteAllMembers() {
	        String sql = "DELETE FROM memberVO";
	        jdbcTemplate.execute(sql);
	    }
	    
	 
    @Transactional
    public String getUser(UserReq userReq) {
        UserRes userRes = memberMapper.findByUsername(userReq.getNickname());
        if (userRes == null) {
            throw new UserNotFoundException("사용자를 찾을 수 없습니다");
        }
        else {
        	return userRes.getUsername();
        }

	    
    }
    
    @Transactional

    	public int insertImage(MultipartFile imageFile , UserReq userReq) {
    	try {
            // 이미지 파일을 Base64 문자열로 변환
            String imageFileBase64 = Base64.getEncoder().encodeToString(imageFile.getBytes());
            int result = memberMapper.insertImage(imageFileBase64, userReq.getNickname());
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;  
        }
    }
	/*public MemberVO getUserInfo(String nickname) {
		return memberMapper.findusername(nickname);
	}*/
	    
    @Transactional
	public String getFriend(UserReq userReq) {
    		MemberVO memberVO = memberMapper.findByfriend(userReq.getNickname());
    		
	    	return memberVO != null ? memberVO.getFriendnickname() : null;
			
	}	
    @Transactional
	public String addgetFriend(String nickname) {
     		MemberVO memberVO = memberMapper.findByfriend(nickname);
    		System.out.println("memberVO " + memberVO);

    	return memberVO != null ? memberVO.getFriendnickname() : null;
		
	}	
	

}


