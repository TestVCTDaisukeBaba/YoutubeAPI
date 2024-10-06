package com.vicent.vtutor.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * ユーザ情報テーブル Entity
 */
@Entity
@Table(name="user_info")
@Data
public class TestUserInfo {
	
	/**ログインID*/
	@Id
	@Column(name="login_id")
	private String loginId;
	
	/**パスワード*/
	private String password;
	
}
