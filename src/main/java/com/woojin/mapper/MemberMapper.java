package com.woojin.mapper;

import org.apache.ibatis.annotations.Mapper;


import com.woojin.dto.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import com.woojin.vo.MemberVO;
import org.springframework.web.multipart.MultipartFile;

@Mapper
public interface MemberMapper {
	
	// 회원가입 기능중 이메일 , 아이디 중복 검사 
    @Select("SELECT COUNT(*) > 0 FROM memberVO WHERE email = #{email}")
    boolean emailExists(@Param("email") String email);
    
    @Select("SELECT COUNT(*) > 0 FROM memberVO WHERE nickname = #{nickname}")
    boolean nicknameExists(@Param("nickname")String nickname);
    //회원가입
    void save(@Param("mem") MemberVO memberVO);
    
    
    
    
    
    
    //로그인 기능( 아이디 , 비밀번호 조회 ) 
    @Select("select username from membervo where username=#{username} ")
    boolean namefind(@Param("username")String username);
    
    @Select("select password from membervo where password=#{password} ")
    MemberVO passwordfind(@Param("password")String password);
    
    @Select("select nickname from membervo where nickname=#{nickname} ")
    MemberVO findByNickname(@Param("nickname")String nickname);
    //		
    
    
    
    @Select("select friendnickname from membervo where nickname=#{nickname} ")
    MemberVO findByfriend(@Param("nickname")String nickname);
    

    @Select("SELECT username FROM membervo WHERE nickname = #{nickname} ")
    UserRes findByUsername(@Param("nickname")String nickname);
    
    @Select("SELECT friendnickname FROM membervo WHERE nickname = #{nickname} ")
    MemberVO findByfriendnickname(@Param("friendnickname")String friendnickname , @Param("nickname") String nickname);
    
    
    
    //이미지업뎃
    @Insert("Update profile SET Image = #{imagefile} where username=#{nickname}")
    int insertImage(@Param("imagefile") String imagefile ,@Param("nickname") String nickname);
    //친구추가
    void addfriend(@Param("friendnickname")  String friendnickname  ,@Param("nickname") String nickname);
}