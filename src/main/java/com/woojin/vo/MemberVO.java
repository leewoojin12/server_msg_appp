		package com.woojin.vo;
		
		
		
		//데이터컬럼 생성 하는거 
		
		import javax.persistence.*;
	
		import javax.validation.constraints.Email;
		import javax.validation.constraints.NotBlank;
		import javax.validation.constraints.Size;
		
		import lombok.*;
		
		@Entity
		@Data
		@Builder
		@AllArgsConstructor
		@NoArgsConstructor
		
		public class MemberVO {
		
		    @Id
		    @GeneratedValue(strategy = GenerationType.IDENTITY)
		    private Long id;
		
		    @Email(message = "이메일 형식이 올바르지 않습니다.")
		    @NotBlank(message = "이메일은 필수 입력 값입니다.")
		    @Column(nullable = false, unique = true)
		    private String email;
	
		    @NotBlank(message = "사용자 이름은 필수 입력 값입니다.")
		    private String username;
		    
		    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
		    @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
		    @Column(nullable = false)
		    private String password;
	
		    @NotBlank(message = "닉네임 필수 입력 값입니다.")
		    @Column(nullable = false, unique = true)  // 추가
		    private String nickname;
		    
		    private String friendnickname;
		    
		    }
