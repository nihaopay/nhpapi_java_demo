package com.aurfy.haze.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpRequest {

	private static final Logger logger = LoggerFactory.getLogger(HttpRequest.class);

	/**
	 * inquiry method
	 * 
	 * @param strURL
	 * @param 请求参数应该是
	 *            name1=value1&name2=value2 的形式。
	 * @return
	 */
	public static String sendAuthPost(String strURL, String param, String token) {
		String result = "";
		PrintWriter out = null;
		BufferedReader in = null;
		HttpURLConnection conn = null;
		try {
			URL realUrl = new URL(strURL);
			conn = (HttpURLConnection) realUrl.openConnection();
			conn.addRequestProperty("Authorization", token);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			out = new PrintWriter(conn.getOutputStream());
			out.print(param);
			out.flush();
			try {
				in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} catch (Exception e) {
				in = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}

			String line = "";

			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			logger.warn("Send POST exception", e);
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		logger.info("Get resp result:{}", result);
		return result;
	}

	/**
	 * inquiry get method
	 * 
	 * @param strURL
	 * @param 请求参数应该是
	 *            name1=value1&name2=value2 的形式。
	 * @return
	 */
	public static String sendAuthGet(String strURL, String param, String token) {
		String result = "";
		PrintWriter out = null;
		BufferedReader in = null;
		HttpURLConnection conn = null;
		try {
			URL realUrl = new URL(strURL);
			conn = (HttpURLConnection) realUrl.openConnection();
			conn.setRequestMethod("GET");
			conn.addRequestProperty("Authorization", token);
			conn.setDoInput(true);
			if (StringUtils.isNotBlank(param)) {
				conn.setDoOutput(true);
				out = new PrintWriter(conn.getOutputStream());
				out.print(param);
				out.flush();
			}
			try {
				in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} catch (Exception e) {
				in = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}

			String line = "";

			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			logger.warn("Send POST exception", e);
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		logger.info("Get resp result:{}", result);
		return result;
	}

}
