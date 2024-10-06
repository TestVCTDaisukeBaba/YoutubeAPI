package com.vicent.vtutor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vicent.vtutor.entity.TestUserInfo;

/**
 * ユーザ情報テーブルDAO
 */
@Repository
public interface TestUserInfoRepository extends JpaRepository<TestUserInfo, String>{

}
