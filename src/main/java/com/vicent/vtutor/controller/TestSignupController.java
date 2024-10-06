package com.vicent.vtutor.controller;

import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.vicent.vtutor.constant.MessageConst;
import com.vicent.vtutor.dto.TestDto;
import com.vicent.vtutor.dto.TestSignupDto;
import com.vicent.vtutor.entity.TestUserInfo;
import com.vicent.vtutor.service.TestLoginService;
import com.vicent.vtutor.service.TestSignupService;
import com.vicent.vtutor.util.TestAppUtil;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー登録画面 Controlle
 */
@Controller
@RequiredArgsConstructor
public class TestSignupController {
	
	/**　ユーザー登録画面 Service */
	private final TestSignupService service;
	
	/** メッセージソース */
	private final MessageSource messageSource;

	/**
	 * 初期表示
	 * 
	 * @param model モデル
	 * @param testdto 入力情報
	 * @return 表示画面
	 */
	@GetMapping("/test_signup")
	public String view(Model model, TestSignupDto testSignupDto) {
		return "test_signup";
	}
	
	/**
	 * ユーザ登録
	 * 
	 * @param model モデル
	 * @param testdto 入力情報
	 * @return 表示画面
	 */
	@PostMapping("/test_signup")
	public void testSignup(Model model, TestSignupDto testSignupDto) {
		var userInfoOpt = service.resistUserInfo(testSignupDto);
		var message = TestAppUtil.getMessage(messageSource,judgeMessageKey(userInfoOpt));
		model.addAttribute("message", message);
		
//		if(userInfoOpt.isEmpty()) {
//			var errorMsg = TestAppUtill.getMessage(messageSource, MessageConst.SIGNUP_EXISTED＿LOGIN_ID);
//			System.out.println(errorMsg);
//			model.addAttribute("message", errorMsg);
//		} else {
//			var message = TestAppUtill.getMessage(messageSource, MessageConst.SIGNUP_RESIST_SUCCED);
//			System.out.println(message);
//			model.addAttribute("message", message);
//		}
	}
	
	/**
	 * ユーザ登録の結果メッセージキーを判断する
	 * @param userInfoOpt　ユーザ登録検索結果(登録済みだった場合はEmpty)
	 * @return メッセージキー
	 */
	
	public String judgeMessageKey(Optional<TestUserInfo> userInfoOpt) {
		if(userInfoOpt.isEmpty()) {
			return MessageConst.SIGNUP_EXISTED＿LOGIN_ID;

		} else {
			return MessageConst.SIGNUP_RESIST_SUCCED;
		}
	}
	

}