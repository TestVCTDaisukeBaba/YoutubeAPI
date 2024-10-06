package com.vicent.vtutor.util;

import java.util.Locale;

import org.springframework.context.MessageSource;

/**
 * アプリケーション共通クラス
 */
public class TestAppUtil {
	
	/**
	 * メッセージIDからメッセージを取得する
	 * @param messageSource メッセージソース
	 * @param key　メッセージキー
	 * @param params 置換文字郡
	 * @return メッセージ
	 */
	public static String getMessage(MessageSource messageSource,String key, Object...params) {
		return messageSource.getMessage(key, params, Locale.JAPAN);
	}
}
