package com.example.storage.domain;

import com.example.storage.enums.Roles;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UsersEntity {
	
	@Id
	private String userId;
	
	@Column(name = "user_pw", columnDefinition = "VARCHAR(30)", nullable = false)
	private String password;
	
	@Column(name = "user_name", columnDefinition = "CHAR(20)", nullable = false)
	private String name;
	
	@Column(name = "user_phone", columnDefinition = "CHAR(13)")
	private String phone;
	
	@Column(name = "user_dept", columnDefinition = "CHAR(20)")
	private String dept;

	@Column(name = "user_info", columnDefinition = "VARCHAR(255)")
	private String info;

	@Enumerated(EnumType.STRING)
	@Column(name = "user_role", columnDefinition = "CHAR(5)", nullable = false)
	private Roles role;
}
