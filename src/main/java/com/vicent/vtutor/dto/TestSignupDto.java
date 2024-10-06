package com.vicent.vtutor.dto;

import lombok.Data;

/**
 * ユーザー登録画面 form
 */
@Data
public class TestSignupDto {
	/**ログインID*/
	private String loginId;
	
	/**パスワード*/
	private String password;
}
