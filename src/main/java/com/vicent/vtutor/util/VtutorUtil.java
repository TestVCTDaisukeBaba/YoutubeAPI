package com.vicent.vtutor.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTube.Search;

public class VtutorUtil {

	/** Global instance properties filename. */
	private static String PROPERTIES_FILENAME = "youtube.properties";

	/** Global instance of the HTTP transport. */
	/** HTTP通信用の定義を追加. */
	private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

	/** JSONの取り扱い機能. */
	private static final JsonFactory JSON_FACTORY = new JacksonFactory();

	/** youtubeのAPIインスタンス. */
	private static YouTube youtube;

	// youtubeインスタンスを作成
	public YouTube youtubeInst() throws Exception {
		youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
			public void initialize(HttpRequest request) throws IOException {
			}
		}).setApplicationName("vtutor").build();

		return youtube;
	}

	// youtube.propertiesを読み込み
	public Properties youtubeProp() throws Exception {
		Properties properties = new Properties();
		try {
			// SearchメソッドはYoutubeAPIのメソッド
			InputStream in = Search.class.getResourceAsStream("/" + PROPERTIES_FILENAME);
			properties.load(in);
		} catch (IOException e) {
			System.err.println(
					"There was an error reading " + PROPERTIES_FILENAME + ": " + e.getCause() + " : " + e.getMessage());
			System.exit(1);
		}
		return properties;
	}
}