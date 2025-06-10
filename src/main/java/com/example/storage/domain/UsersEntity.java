package com.example.storage.domain;

import com.example.storage.dto.UserCRUDRequest;
import com.example.storage.dto.UserDto;
import com.example.storage.enums.Roles;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UsersEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Column(name = "user_name", columnDefinition = "VARCHAR(20)", nullable = false)
	private String username;

	@Column(name = "user_pw", columnDefinition = "VARCHAR(255)", nullable = false)
	private String password;

	@Column(name = "user_phone", columnDefinition = "VARCHAR(13)")
	private String phone;
	
	@Column(name = "user_dept", columnDefinition = "VARCHAR(20)")
	private String dept;

	@Column(name = "user_info", columnDefinition = "VARCHAR(255)")
	private String info;

	@Enumerated(EnumType.STRING)
	@Column(name = "user_role", columnDefinition = "CHAR(5)", nullable = false)
	private Roles role;

	public void updateUsername(String username) {
		if(username == null)	{
			return;
		}
		this.username = username;
	}

	public void updatePassword(String password) {
		if(password == null)	{
			return;
		}
		this.password = password;
	}

	public void updatePhone(String phone) {
		if(phone == null)	{
			return;
		}
		this.phone = phone;
	}

	public void updateDept(String dept) {
		if(dept == null)	{
			return;
		}
		this.dept = dept;
	}

	public void updateInfo(String info) {
		if(info == null)	{
			return;
		}
		this.info = info;
	}

	public void updateRole(Roles role) {
		if(role == null)	{
			return;
		}
		this.role = role;
	}

	public UsersEntity update(UserCRUDRequest request) {
		updateUsername(request.getUsername());
		updatePassword(request.getPassword());
		updatePhone(request.getPhone());
		updateDept(request.getDept());
		updateInfo(request.getInfo());
		updateRole(request.getRole());
		return this;
	}
}
