package com.aurfy.haze.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.aurfy.haze.utils.HttpRequest;
import com.aurfy.haze.web.vo.API.APICancelReqVO;
import com.aurfy.haze.web.vo.API.APICaptureReqVO;
import com.aurfy.haze.web.vo.API.APIExpressPayReqVO;
import com.aurfy.haze.web.vo.API.APIListTraReqVO;
import com.aurfy.haze.web.vo.API.APIRefundReqVO;
import com.aurfy.haze.web.vo.API.APIReleaseReqVO;
import com.aurfy.haze.web.vo.API.APISecurePayReqVO;
import com.aurfy.haze.web.vo.API.APISingleQueryReqVO;

@Controller
public class APIController {
	private static final Logger logger = LoggerFactory.getLogger(APIController.class);
	
	

	@RequestMapping(value = "/APISecurePay", method = RequestMethod.POST)
	public @ResponseBody void APISecurePay(HttpServletRequest request,
			@ModelAttribute("payReq") @Validated APISecurePayReqVO payReq, BindingResult errors,
			HttpServletResponse resp) {
		try {
			if (errors.hasErrors()) {
				resp.getWriter().print(errors.getAllErrors());
			}
			logger.info("Do API SecurePay: " + payReq.getInput());
			String result = HttpRequest.sendAuthPost(payReq.getUrl(), payReq.getInput(), "Bearer " + payReq.getToken());
			logger.info("get response from API: " + result);
			resp.setCharacterEncoding("UTF-8");
			resp.setHeader("Content-type", "text/html");// jquery post must return json type
			resp.getWriter().print(result);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
	}

	@RequestMapping(value = "/APIExpressPay", method = RequestMethod.POST)
	public @ResponseBody void APIExpressPay(HttpServletRequest request, @Validated APIExpressPayReqVO payReq,
			BindingResult errors, HttpServletResponse resp) {
		try {
			if (errors.hasErrors()) {
				resp.getWriter().print(errors.getAllErrors());
			}
			logger.info("Do API ExpressPay: " + payReq.getInput());
			String result = HttpRequest.sendAuthPost(payReq.getUrl(), payReq.getInput(), "Bearer " + payReq.getToken());
			logger.info("get response from php_API: " + result);
			resp.setCharacterEncoding("UTF-8");
			resp.setHeader("Content-type", "application/json");// jquery post must return json type
			resp.getWriter().print(result);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
	}

	@RequestMapping(value = "/APIRefund", method = RequestMethod.POST)
	public @ResponseBody void APIRefund(HttpServletRequest request, @Validated APIRefundReqVO payReq,
			BindingResult errors, HttpServletResponse resp) {
		try {
			if (errors.hasErrors()) {
				resp.getWriter().print(errors.getAllErrors());
			}
			logger.info("Do API Refund: " + payReq.getInput());
			String url = payReq.getUrl().replace("{transaction_id}", payReq.getTransaction_id());
			String result = HttpRequest.sendAuthPost(url, payReq.getInput(), "Bearer " + payReq.getToken());
			logger.info("get response from php_API: " + result);
			resp.setCharacterEncoding("UTF-8");
			resp.setHeader("Content-type", "application/json");// jquery post must return json type
			resp.getWriter().print(result);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
	}

	@RequestMapping(value = "/APICapture", method = RequestMethod.POST)
	public @ResponseBody void APICapture(HttpServletRequest request, @Validated APICaptureReqVO payReq,
			BindingResult errors, HttpServletResponse resp) {
		try {
			if (errors.hasErrors()) {
				resp.getWriter().print(errors.getAllErrors());
			}
			logger.info("Do API Capture: " + payReq.getInput());
			String url = payReq.getUrl().replace("{transaction_id}", payReq.getTransaction_id());
			String result = HttpRequest.sendAuthPost(url, payReq.getInput(), "Bearer " + payReq.getToken());
			logger.info("get response from php_API: " + result);
			resp.setCharacterEncoding("UTF-8");
			resp.setHeader("Content-type", "application/json");// jquery post must return json type
			resp.getWriter().print(result);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
	}

	@RequestMapping(value = "/APIRelease", method = RequestMethod.POST)
	public @ResponseBody void APIRelease(HttpServletRequest request, @Validated APIReleaseReqVO payReq,
			BindingResult errors, HttpServletResponse resp) {
		try {
			if (errors.hasErrors()) {
				resp.getWriter().print(errors.getAllErrors());
			}
			logger.info("Do API Release: " + payReq.getTransaction_id());
			String url = payReq.getUrl().replace("{transaction_id}", payReq.getTransaction_id());
			String result = HttpRequest.sendAuthPost(url, "", "Bearer " + payReq.getToken());
			logger.info("get response from php_API: " + result);
			resp.setCharacterEncoding("UTF-8");
			resp.setHeader("Content-type", "application/json");// jquery post must return json type
			resp.getWriter().print(result);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
	}

	@RequestMapping(value = "/APICancel", method = RequestMethod.POST)
	public @ResponseBody void APICancel(HttpServletRequest request, @Validated APICancelReqVO payReq,
			BindingResult errors, HttpServletResponse resp) {
		try {
			if (errors.hasErrors()) {
				resp.getWriter().print(errors.getAllErrors());
			}
			logger.info("Do API Cancel: " + payReq.getTransaction_id());
			String url = payReq.getUrl().replace("{transaction_id}", payReq.getTransaction_id());
			String result = HttpRequest.sendAuthPost(url, "", "Bearer " + payReq.getToken());
			logger.info("get response from php_API: " + result);
			resp.setCharacterEncoding("UTF-8");
			resp.setHeader("Content-type", "application/json");// jquery post must return json type
			resp.getWriter().print(result);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
	}

	@RequestMapping(value = "/APISingleQuery", method = RequestMethod.POST)
	public @ResponseBody void APISingleQuery(HttpServletRequest request, @Validated APISingleQueryReqVO payReq,
			BindingResult errors, HttpServletResponse resp) {
		try {
			if (errors.hasErrors()) {
				resp.getWriter().print(errors.getAllErrors());
			}
			logger.info("Do API Single query: " + payReq.getTransaction_id());
			String url = payReq.getUrl().replace("{transaction_id}", payReq.getTransaction_id());
			String result = HttpRequest.sendAuthGet(url, null, "Bearer " + payReq.getToken());
			logger.info("get response from php_API: " + result);
			resp.setCharacterEncoding("UTF-8");
			resp.setHeader("Content-type", "application/json");// jquery post must return json type
			resp.getWriter().print(result);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
	}

	@RequestMapping(value = "/APIListTransactions", method = RequestMethod.POST)
	public @ResponseBody void APIListTransactions(HttpServletRequest request, @Validated APIListTraReqVO payReq,
			BindingResult errors, HttpServletResponse resp) {
		try {
			if (errors.hasErrors()) {
				resp.getWriter().print(errors.getAllErrors());
			}
			logger.info("Do API List transactions: " + payReq.getInput());
			String url = payReq.getUrl() + "?" + payReq.getInput();
			String result = HttpRequest.sendAuthGet(url, null, "Bearer " + payReq.getToken());
			logger.info("get response from php_API: " + result);
			resp.setCharacterEncoding("UTF-8");
			resp.setHeader("Content-type", "application/json");// jquery post must return json type
			resp.getWriter().print(result);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/APIQrcode", method = RequestMethod.POST)
	public ModelAndView APIQrcode(HttpServletRequest request,
			@ModelAttribute("payReq") @Validated APISecurePayReqVO payReq, BindingResult errors,
			HttpServletResponse resp) {
		ModelAndView mv = new ModelAndView("qrCodePay");
		try {
			if (errors.hasErrors()) {
				resp.getWriter().print(errors.getAllErrors());
			}
			logger.info("Do API Qrcode: " + payReq.getInput());
			System.out.println(payReq.getUrl() + "================");
			String result = HttpRequest.sendAuthPost(payReq.getUrl(), payReq.getInput(), "Bearer " + payReq.getToken());
			logger.info("get response from php_API: " + result);
			Map<Object, Object> map = (Map<Object, Object>) JSONArray.parse(result);
			mv.addObject("resultMap", map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return mv;
	}
}
