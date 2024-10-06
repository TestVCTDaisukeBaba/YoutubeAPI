package com.vicent.vtutor.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vicent.vtutor.entity.TestUserInfo;
import com.vicent.vtutor.repository.TestUserInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * ログイン画面 ervice
 */
@Service
@RequiredArgsConstructor
public class TestLoginService {
		
	/**ユーザー情報テーブルDAO */
		private final TestUserInfoRepository repository;
		
		/**
		 * ユーザ情報テーブル主キー検索
		 * @param loginId ログインID
		 * @return ユーザ情報テーブルを主キー検索した結果
		 */
		public Optional<TestUserInfo> searchUserById(String loginId){
			return repository.findById(loginId);
		}
}

