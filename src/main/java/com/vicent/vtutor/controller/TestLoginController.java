package com.vicent.vtutor.controller;

import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.vicent.vtutor.constant.MessageConst;
import com.vicent.vtutor.dto.TestDto;
import com.vicent.vtutor.service.TestLoginService;
import com.vicent.vtutor.util.TestAppUtil;

import lombok.RequiredArgsConstructor;

/**
 * ログイン画面 Controlle
 */
@Controller
@RequiredArgsConstructor
public class TestLoginController {
	
	/**　ログイン画面 Service */
	private final TestLoginService service;
	
	/** PaswwordEncoder */
	private final PasswordEncoder passwordEncoder;
	
	/** メッセージソース */
	private final MessageSource messageSource;
	
	/**
	 * 初期表示
	 * 
	 * @param model モデル
	 * @param testdto 入力情報
	 * @return 表示画面
	 */
	@GetMapping("/test_login")
	public String view(Model model, TestDto testdto) {
		return "test_login";
	}
	
	/**
	 * ログイン
	 * 
	 * @param model モデル
	 * @param testdto 入力情報
	 * @return 表示画面
	 */
	@PostMapping("/test_login")
	public String login(Model model, TestDto testdto) {
		var userInfo = service.searchUserById(testdto.getLoginId());
		// パスワードはハッシュ化したものを使用する
		var encorderPassword = passwordEncoder.encode(testdto.getPassword());
		System.out.println(encorderPassword);
		var isCorrectUserAuth = userInfo.isPresent() && passwordEncoder.matches(testdto.getPassword(), userInfo.get().getPassword());
		if(isCorrectUserAuth) {
			return "redirect:/test";
		}else {
			// エラーメッセージはプロパティファイルで管理する
			var errorMsg = TestAppUtil.getMessage(messageSource, MessageConst.LOGIN_WRONG_INPUT );
			System.out.println(errorMsg);
			model.addAttribute("errorMsg", errorMsg);
			return "test_login";
		}
		
	}
	

}