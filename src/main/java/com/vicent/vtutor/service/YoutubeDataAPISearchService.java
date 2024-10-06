package com.vicent.vtutor.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.vicent.vtutor.config.VtutorConstants;
import com.vicent.vtutor.dto.YoutubeDataAPISearchDto;
import com.vicent.vtutor.util.VtutorUtil;

@Service
public class YoutubeDataAPISearchService {

	public List<SearchResult> YoutubeSearch(YoutubeDataAPISearchDto dto) throws Exception {

		// utilインスタンスを作成
		VtutorUtil util = new VtutorUtil();

		// YouTubeインスタンスを作成
		YouTube youtube = util.youtubeInst();

		// Youtube Data APIで使用する検索KEYを取得
		String apiKey = util.youtubeProp().getProperty("youtube.apikey");

		// 検索機能:partに検索条件を設定
		// 【参考：https://developers.google.com/youtube/v3/docs/search/list?hl=ja】
		YouTube.Search.List search = youtube.search().list(dto.getPart());

		// 検索条件：API_KEYをセット
		search.setKey(apiKey);

		// 検索条件：検索キーワードをセット
		if (!StringUtils.isEmpty(dto.getQ())) {
			search.setQ(dto.getQ());
		}

		// 検索条件：動画の種類をセット
		if (!StringUtils.isEmpty(dto.getType())) {
			search.setType(dto.getType());
		}

		// 検索条件：チャンネルIDをセット
		if (!StringUtils.isEmpty(dto.getChannelId())) {
			search.setChannelId(dto.getChannelId());
		}

		// 検索条件：上限数をセット
		if (!(dto.getMaxResults() > 0)) {
			search.setMaxResults(dto.getMaxResults());
		}

		// 検索条件：返却JSONの形をセット
		if (!StringUtils.isEmpty(dto.getFields())) {
			search.setFields(dto.getFields());
		}

		// 検索処理を実行
		SearchListResponse searchResponse = search.execute();

		return searchResponse.getItems();

	}

}