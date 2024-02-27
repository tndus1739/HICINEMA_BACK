package com.himedia.hicinema.member;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Data
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column (unique = true, nullable = false) 
	private String member_id;
	
	@Column(nullable = false , length = 100)
	private String password;
	
	@Column (unique = true, nullable = false) 
	private String email;
	
	@Column (unique = true) 
	private String phone;

//	@Column (nullable = false)
	private String role;

	private LocalDateTime regdate;
	
	private LocalDateTime delDate;

	private String status;

	

}
