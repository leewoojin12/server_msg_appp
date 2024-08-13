package com.woojin.dto;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRes {
	private String username;
    private String password;
    private String email;
    private String nickname;
    private int imageFile;

    private String friendnickname;

}
