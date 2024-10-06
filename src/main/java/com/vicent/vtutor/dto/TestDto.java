package com.vicent.vtutor.dto;

import lombok.Data;

/**
 * ログイン画面 form
 */
@Data
public class TestDto {
	/**ログインID*/
	private String loginId;
	
	/**パスワード*/
	private String password;
}
