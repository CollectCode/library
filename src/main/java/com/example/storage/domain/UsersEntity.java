package com.example.storage.domain;

import com.example.storage.dto.UserCRUDRequest;
import com.example.storage.dto.UserDto;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Column(name = "user_name", columnDefinition = "CHAR(20)", nullable = false)
	private String username;

	@Column(name = "user_pw", columnDefinition = "VARCHAR(30)", nullable = false)
	private String password;

	@Column(name = "user_phone", columnDefinition = "CHAR(13)")
	private String phone;
	
	@Column(name = "user_dept", columnDefinition = "CHAR(20)")
	private String dept;

	@Column(name = "user_info", columnDefinition = "VARCHAR(255)")
	private String info;

	@Enumerated(EnumType.STRING)
	@Column(name = "user_role", columnDefinition = "CHAR(5)", nullable = false)
	private Roles role;

	public UsersEntity update(UserCRUDRequest request) {
		UserDto dto = request.getUser();
		this.username = dto.getUsername();
		this.password = dto.getPassword();
		this.phone = dto.getPhone();
		this.dept = dto.getDept();
		this.info = dto.getInfo();
		this.role = dto.getRole();
		return this;
	}
}
