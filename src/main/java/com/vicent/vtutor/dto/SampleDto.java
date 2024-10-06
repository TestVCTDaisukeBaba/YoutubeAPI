package com.vicent.vtutor.dto;

import org.springframework.stereotype.Component;

//サンプルページ用にDTOを作成。
//なお、FORMやENTITYなど分かりにくい概念があるが、以下サイトを参照し、適切に作成する事。
//参考：https://cs27.org/wiki/kobespiral2021/?SpringBoot/DTO
//参考:https://qiita.com/tk10/items/26a3b50c2ad8694f8643

@Component
public class SampleDto {
	// 動画ID
	private String videoId;
	// 動画タイトル
	private String videoTitle;
	// 動画URL
	private String videoURL;

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getVideoTitle() {
		return videoTitle;
	}

	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}

	public String getVideoURL() {
		return videoURL;
	}

	public void setVideoURL(String videoURL) {
		this.videoURL = videoURL;
	}
}