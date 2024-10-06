package com.vicent.vtutor.service;

import java.util.Optional;

import org.dozer.Mapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vicent.vtutor.dto.TestSignupDto;
import com.vicent.vtutor.entity.TestUserInfo;
import com.vicent.vtutor.repository.TestUserInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー登録画面 ervice
 */
@Service
@RequiredArgsConstructor
public class TestSignupService {

	/**ユーザー情報テーブルDAO */
	private final TestUserInfoRepository repository;

	/** Dozer.mapper */
	private final Mapper mapper; 

	
	/** PaswwordEncoder */
	private final PasswordEncoder passwordEncoder;
	
	/**
	 * ユーザ情報テーブル 新規登録
	 * @param 入力情報
	 * @return 登録情報ユーザ情報Entity),既に同じユーザIDで登録がある場合はEmpty
	 */
	public Optional<TestUserInfo> resistUserInfo(TestSignupDto testSignupDto) {
		var userInfoExsitedOpt = repository.findById(testSignupDto.getLoginId());
		
		if(userInfoExsitedOpt.isPresent()) {
			return Optional.empty();
		}
		
//		var userInfo = new TestUserInfo();
//		userInfo.setLoginId(testSignupDto.getLoginId());
//		userInfo.setPassword(testSignupDto.getPassword());
		var userInfo = mapper.map(testSignupDto, TestUserInfo.class);
		
		var encoderPassword = passwordEncoder.encode(testSignupDto.getPassword());
		
		userInfo.setPassword(encoderPassword);
		System.out.println(userInfo);

		return Optional.of(repository.save(userInfo));
	}
}
