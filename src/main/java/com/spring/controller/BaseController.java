package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.spring.dto.AddItem;
import com.spring.dto.ApiResponse;
import com.spring.dto.CustomException;
import com.spring.iservice.IBaseService;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

public class BaseController {

	@Autowired
	protected IBaseService service;

	String securityUrl = "http://localhost:8765";
	static Logger logger = Logger.getLogger(BaseController.class);

	@Retry(name = "lms", fallbackMethod = "getFallbackRetry")
	@Bulkhead(name = "lms", fallbackMethod = "getFallback", type = Bulkhead.Type.SEMAPHORE)
	// @HystrixCommand(fallbackMethod = "getFallback")
	@CircuitBreaker(name = "lms", fallbackMethod = "getFallbackTimeout")
	public String validateToken(String token, String role) {
		String responseJson = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			responseJson = restTemplate
					.getForObject(securityUrl + "token/validate-token?token=" + token + "&role=" + role, String.class);
		} catch (Exception e) {
			String stackTrace = ExceptionUtils.getStackTrace(e);
			logger.error(stackTrace);

		}
		return responseJson;
	}

	@Retry(name = "lms", fallbackMethod = "userFallbackRetry")
	@Bulkhead(name = "lms", fallbackMethod = "userFallback", type = Bulkhead.Type.SEMAPHORE)
	// @HystrixCommand(fallbackMethod = "userFallback")
	@CircuitBreaker(name = "lms", fallbackMethod = "userFallback")
	public ResponseEntity<ApiResponse> addTocart(AddItem cartDto) {
		ResponseEntity<ApiResponse> result = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			result = restTemplate.postForEntity("http://localhost:8011/api/addToCart", cartDto, ApiResponse.class);
		} catch (Exception e) {
			String stackTrace = ExceptionUtils.getStackTrace(e);
			logger.error(stackTrace);

		}
		return result;

	}

    @Retry(name = "lms", fallbackMethod = "getFallbackRetry")
    @Bulkhead(name = "lms", fallbackMethod = "getFallback", type = Bulkhead.Type.SEMAPHORE)
    @CircuitBreaker(name = "lms", fallbackMethod = "getFallbackTimeout")
    public int getIdByUser(String token) {
		Integer responseJson = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			String url = securityUrl + "/token/getUserId?token=" + token;
			responseJson = restTemplate.getForObject(url, Integer.class);
			if (responseJson != null && responseJson != 0) {
				return responseJson;
			} else {
				throw new CustomException("User ID is unexpectedly null. Unable to proceed.");
			}
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return responseJson != null ? responseJson : 0;
	}

	public Map<String, Object> getDropDownData() {

		Map<String, Object> list = null;
		try {
			list = service.getDropDownData();
		}

		catch (Exception e) {

			String stackTrace = ExceptionUtils.getStackTrace(e);
			logger.error(stackTrace);

		}
		return list;
	}

	@SuppressWarnings({ "unused" })
	private String validateTokenFallback(String token, String role, Throwable throwable) {
		logger.info("validateTokenFallback invoked");
		return "Falling back : " + token;
	}

	@SuppressWarnings({ "unused" })
	private String validateTokenFallbackRetry(String token, String role, HttpClientErrorException throwable) {
		logger.info("validateTokenFallbackRetry invoked");
		return "Falling back Retry: " + token;
	}

}
