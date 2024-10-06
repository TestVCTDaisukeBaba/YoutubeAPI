package com.vicent.vtutor.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;
import com.vicent.vtutor.config.VtutorConstants;
import com.vicent.vtutor.dto.SampleDto;
import com.vicent.vtutor.dto.YoutubeDataAPISearchDto;
import com.vicent.vtutor.service.YoutubeDataAPISearchService;
import com.vicent.vtutor.util.VtutorUtil;

// コントローラークラスである事を明示
// コントローラークラスは入力チェック・マッピング・データの加工以外何も書かない事
@Controller
public class SampleController {

	// 使用するServiceクラスを定義
	@Autowired
	YoutubeDataAPISearchService youtubeSearch;

	// sampleページにアクセスした際の初期表示
	@GetMapping("/sample")
	public String getSample(Model model) throws Exception {

		// インスタンスを作成
		YoutubeDataAPISearchDto searchDto = new YoutubeDataAPISearchDto();
		
		// 検索機能の項目（リファクタリング練習のためあえてべた書き）
		searchDto.setPart("id,snippet");
		
		// 検索条件：検索キーワードをセット（固定）
		searchDto.setQ(VtutorConstants.SAMPLE_Q_STRING);
		
		// 検索条件：チャンネルIDを追加（ヴィセントのIDで固定）
		searchDto.setChannelId(VtutorConstants.SAMPLE_CHANNEL_ID);
		
		// 検索条件：動画のみを検索条件にする様にセット（リファクタリング練習のためあえてべた書き）
		searchDto.setType("video");
		
		// 返却条件：取得したい項目を指定。今回はVideoIDと動画のタイトル、およびデフォルトのＵＲＬを取得（リファクタリング練習のためあえてべた書き）
		searchDto.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
		
		// 検索条件:定数ファイルから決められた検索上限数を取得
		searchDto.setMaxResults(VtutorConstants.SAMPLE_NUMBER_OF_VIDEOS_RETURNED);

		// 検索結果をリストにして取り出し
		List<SearchResult> searchResultList = youtubeSearch.YoutubeSearch(searchDto);

		// modelを用意し画面に表示予定
		// 結果リストを作成
		List<SampleDto> samples = jsonToModel(searchResultList.iterator(), model);

		// 取得結果を格納
		model.addAttribute("samples", samples);

		return "sample";
	}

	// 取得したJSONをモデルに詰め込む
	private static List<SampleDto> jsonToModel(Iterator<SearchResult> iteratorSearchResults, Model model)
			throws Exception {

		// 結果リスト
		List<SampleDto> samples = new ArrayList<SampleDto>();

		// 取得結果が存在しない場合
		if (!iteratorSearchResults.hasNext()) {
			System.out.println(" There aren't any results for your query.");
		}

		// 取得結果分だけ繰り返し処理
		while (iteratorSearchResults.hasNext()) {

			// 結果格納用DTO
			SampleDto sample = new SampleDto();

			// 取得結果を取り出し
			SearchResult singleVideo = iteratorSearchResults.next();

			// リソースIDを取得
			ResourceId rId = singleVideo.getId();

			// リソースIDがvideoの時のみ処理。プレイリストやチャンネル(channel、playlist)は指定しない
			if (rId.getKind().equals("youtube#video")) {

				// ビデオIDを格納
				sample.setVideoId(rId.getVideoId());

				// タイトルを格納
				sample.setVideoTitle(singleVideo.getSnippet().getTitle());

				// サムネイルを格納。サイズはdefault、medium、highから洗濯
				Thumbnail thumbnail = (Thumbnail) singleVideo.getSnippet().getThumbnails().get("default");
				sample.setVideoURL(thumbnail.getUrl());

			}
			samples.add(sample);
		}
		return samples;
	}
}