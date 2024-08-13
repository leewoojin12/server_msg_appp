package com.woojin.dto;

import io.swagger.annotations.ApiModelProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserReq {

 
	private String username;
    private String password;
    private String email;
    private String nickname;
    public UserReq(String nickname) {
        this.nickname = nickname;
        
    }
    private String friendnickname;

    
    
    private int imageFile;
    
    
    
    
    public String getFriendnickname() {
        return friendnickname;
    }
    
    
    
}
