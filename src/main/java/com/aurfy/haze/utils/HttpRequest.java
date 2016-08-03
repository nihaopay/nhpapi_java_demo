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

	// private static void sendPost(String strURL, String param, String token) {
	// try {
	// HttpPost post = new HttpPost(strURL);
	// // 创建参数队列
	// List<NameValuePair> formparams = new ArrayList<NameValuePair>();
	//
	// UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, "UTF-8");
	// post.setEntity(entity);
	// CloseableHttpResponse response = null;
	// CloseableHttpClient httpClient = null;
	// try {
	// httpClient = HttpClientBuilder.create().build();
	// response = httpClient.execute(post);
	//
	// int statuscode = response.getStatusLine().getStatusCode();
	// System.out.println(response.getEntity());
	// EntityUtils.consume(response.getEntity());
	// System.out.println(response.getEntity());
	//
	// // item.setHttpStatus(HttpStatus.parseByCode(statuscode + ""));
	// // logger.info("txnID {} s2s response status:{}",item.getID(),item.getHttpStatus());
	// // if (HttpStatus.OK.equals(item.getHttpStatus())) {
	// // item.setDeliveryStatus(DeliveryStatusEnum.SUCCEED);
	// // } else {
	// // item.setDeliveryStatus(DeliveryStatusEnum.FAILED);
	// // }
	// } finally {
	// try {
	// httpClient.close();
	// } catch (IOException e) {
	// // ignore
	// logger.warn("error closing http client.");
	// }
	// IOUtils.close(response);
	//
	// }
	// } catch (Exception e) {
	// logger.error("error send post request", e);
	// } finally {
	// //item.setUpdateDate(new Date());
	// }
	// }

}
