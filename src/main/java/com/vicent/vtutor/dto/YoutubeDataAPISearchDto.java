package com.vicent.vtutor.dto;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Component
public class YoutubeDataAPISearchDto {
	
	// 必須項目のみ初期化
	public YoutubeDataAPISearchDto(){
		this.type = "id,snippet";
	}
	
	// 他に追加したい検索条件があったら適宜追加してください。
	// 参考:https://developers.google.com/youtube/v3/docs/search/list?hl=ja
	
	// 戻り値のデータフォーマットを指定。指定した値に対応しJSONの形が変わる
	@NotNull
	private String part;
	// 検索ワード
	private String q;
	// 検索条件
	private String type;
	// 検索上限
	private long maxResults;
	// チャンネルID
	private String channelId;
	// 返却項目
	private String fields;
	
	
	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		this.part = part;
	}
	public String getQ() {
		return q;
	}
	public void setQ(String q) {
		this.q = q;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getMaxResults() {
		return maxResults;
	}
	public void setMaxResults(long maxResults) {
		this.maxResults = maxResults;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getFields() {
		return fields;
	}
	public void setFields(String fields) {
		this.fields = fields;
	}
	

}