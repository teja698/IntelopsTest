package com.spring.service;

import com.spring.dto.DropDownDataDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.base_dao.IGenericDao;
import com.spring.dto.AddItem;
import com.spring.dto.ApiResponse;
import com.spring.dto.CheckoutDto;
import com.spring.dto.CourseRequest;
import com.spring.dto.CustomException;
import com.spring.dto.LmsResponse;
import com.spring.dto.MyResponse;
import com.spring.iservice.IBaseService;
import com.spring.model.ConfigMaster;
import com.spring.model.ConfigSlave;
import com.spring.model.Email;
import com.spring.model.LMSConstant;
import com.spring.model.Sms;

import com.spring.model.UserDto;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@Service(value = "baseService")
@Primary
public class BaseServiceImpl implements IBaseService {

	@SuppressWarnings("rawtypes")
	@Autowired
	protected IGenericDao genericDao;

	static Logger logger = Logger.getLogger(BaseServiceImpl.class);

	@Retry(name = "lms", fallbackMethod = "userFallbackRetry")
	@Bulkhead(name = "lms", fallbackMethod = "userFallback", type = Bulkhead.Type.SEMAPHORE)
	// @HystrixCommand(fallbackMethod = "userFallback")
	@CircuitBreaker(name = "lms", fallbackMethod = "userFallback")
	public UserDto getUserById(int userId) {
		UserDto result = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			result = restTemplate.getForObject("http://localhost:8765/token/getUserById?userId=" + userId,
					UserDto.class);
		} catch (Exception e) {
			String stackTrace = ExceptionUtils.getStackTrace(e);
			logger.error(stackTrace);

		}
		return result;

	}

	@Retry(name = "lms", fallbackMethod = "userFallbackRetry")
	@Bulkhead(name = "lms", fallbackMethod = "userFallback", type = Bulkhead.Type.SEMAPHORE)
	// @HystrixCommand(fallbackMethod = "userFallback")
	@CircuitBreaker(name = "lms", fallbackMethod = "userFallback")
	public MyResponse getNoOfUsers(CourseRequest req) {
		ResponseEntity<MyResponse> result = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			result = restTemplate.postForEntity("http://localhost:8082/token/getNoOfUsers", req, MyResponse.class);
			if (result.getBody() == null) {
				throw new CustomException("User  is unexpectedly null. Unable to proceed.");
			} else {
				return result.getBody();
			}

		} catch (Exception e) {
			String stackTrace = ExceptionUtils.getStackTrace(e);
			logger.error(stackTrace);
		}

		return new MyResponse();
	}

	@Retry(name = "lms", fallbackMethod = "userFallbackRetry")
	@Bulkhead(name = "lms", fallbackMethod = "userFallback", type = Bulkhead.Type.SEMAPHORE)
	// @HystrixCommand(fallbackMethod = "userFallback")
	@CircuitBreaker(name = "lms", fallbackMethod = "userFallback")
	public MyResponse getNoOfByAdmin(CourseRequest req) {
		ResponseEntity<MyResponse> result = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			result = restTemplate.postForEntity("http://localhost:8082/token//getNoOfStudents", req, MyResponse.class);
			if (result.getBody() == null) {
				throw new CustomException("data  is unexpectedly null. Unable to proceed.");

			} else {
				return result.getBody();
			}
		} catch (Exception e) {
			String stackTrace = ExceptionUtils.getStackTrace(e);
			logger.error(stackTrace);

		}
		return new MyResponse();

	}

	@Retry(name = "lms", fallbackMethod = "userFallbackRetry")
	@Bulkhead(name = "lms", fallbackMethod = "userFallback", type = Bulkhead.Type.SEMAPHORE)
	// @HystrixCommand(fallbackMethod = "userFallback")
	@CircuitBreaker(name = "lms", fallbackMethod = "userFallback")
	public ResponseEntity<LmsResponse> getAllCourse(CourseRequest req) {
		ResponseEntity<LmsResponse> result = null;

		try {
			RestTemplate restTemplate = new RestTemplate();
			result = restTemplate.postForEntity("http://localhost:8090/api/getAllCoursesBySearch", req,
					LmsResponse.class);
		}

		catch (Exception e) {
			String stackTrace = ExceptionUtils.getStackTrace(e);
			logger.error(stackTrace);

		}
		return result;

	}

	@Retry(name = "lms", fallbackMethod = "userFallbackRetry")
	@Bulkhead(name = "lms", fallbackMethod = "userFallback", type = Bulkhead.Type.SEMAPHORE)
	// @HystrixCommand(fallbackMethod = "userFallback")
	@CircuitBreaker(name = "lms", fallbackMethod = "userFallback")
	public ResponseEntity<LmsResponse> getCourseById(int course) {
		ResponseEntity<LmsResponse> result = null;
		CourseRequest req = new CourseRequest();
		try {
			req.setCourse(course);
			RestTemplate restTemplate = new RestTemplate();
			result = restTemplate.postForEntity("http://localhost:8090/api/getCourseById", req, LmsResponse.class);
		}

		catch (Exception e) {
			String stackTrace = ExceptionUtils.getStackTrace(e);
			logger.error(stackTrace);

		}
		return result;

	}

	@Retry(name = "lms", fallbackMethod = "userFallbackRetry")
	@Bulkhead(name = "lms", fallbackMethod = "userFallback", type = Bulkhead.Type.SEMAPHORE)
	// @HystrixCommand(fallbackMethod = "userFallback")
	@CircuitBreaker(name = "lms", fallbackMethod = "userFallback")
	public ResponseEntity<DropDownDataDto> getDropDownSetupData() {
		ResponseEntity<DropDownDataDto> result = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			result = restTemplate.getForEntity("http://localhost:8090/api/getDropDownData", DropDownDataDto.class);
		} catch (Exception e) {
			String stackTrace = ExceptionUtils.getStackTrace(e);
			logger.error(stackTrace);

		}
		return result;
	}

	@Retry(name = "lms", fallbackMethod = "userFallbackRetry")
	@Bulkhead(name = "lms", fallbackMethod = "userFallback", type = Bulkhead.Type.SEMAPHORE)
	// @HystrixCommand(fallbackMethod = "userFallback")
	@CircuitBreaker(name = "lms", fallbackMethod = "userFallback")
	public ResponseEntity<LmsResponse> getCourseTopic(int courseTopicId) {
		ResponseEntity<LmsResponse> result = null;
		CourseRequest req = new CourseRequest();
		try {
			req.setCourseTopic(courseTopicId);
			RestTemplate restTemplate = new RestTemplate();
			result = restTemplate.postForEntity("http://localhost:8090/api/getCourseTopic", req, LmsResponse.class);
		}

		catch (Exception e) {
			String stackTrace = ExceptionUtils.getStackTrace(e);
			logger.error(stackTrace);

		}
		return result;

	}

	@Retry(name = "lms", fallbackMethod = "userFallbackRetry")
	@Bulkhead(name = "lms", fallbackMethod = "userFallback", type = Bulkhead.Type.SEMAPHORE)
	// @HystrixCommand(fallbackMethod = "userFallback")
	@CircuitBreaker(name = "lms", fallbackMethod = "userFallback")
	public ResponseEntity<MyResponse> getAllPurchased(int userId) {
		ResponseEntity<MyResponse> result = null;
		CourseRequest req = new CourseRequest();
		try {
			req.setInstitute(userId);
			RestTemplate restTemplate = new RestTemplate();
			result = restTemplate.postForEntity("http://localhost:8092/payment/getAllPurchasedCourses", req,
					MyResponse.class);

		}

		catch (Exception e) {
			String stackTrace = ExceptionUtils.getStackTrace(e);
			logger.error(stackTrace);

		}
		return result;
	}

	@Retry(name = "lms", fallbackMethod = "userFallbackRetry")
	@Bulkhead(name = "lms", fallbackMethod = "userFallback", type = Bulkhead.Type.SEMAPHORE)
	// @HystrixCommand(fallbackMethod = "userFallback")
	@CircuitBreaker(name = "lms", fallbackMethod = "userFallback")
	public ResponseEntity<MyResponse> getAllHighestCourse(int userId) {
		ResponseEntity<MyResponse> result = null;
		JSONObject json = new JSONObject();
		try {
			json.put(LMSConstant.USER_ID, userId);
			RestTemplate restTemplate = new RestTemplate();
			result = restTemplate.postForEntity("http://localhost:8082/token/getBatchMembersHighestPurchasedCourses",
					json, MyResponse.class);

		}

		catch (Exception e) {
			String stackTrace = ExceptionUtils.getStackTrace(e);
			logger.error(stackTrace);

		}
		return result;

	}

	@Retry(name = "lms", fallbackMethod = "userFallbackRetry")
	@Bulkhead(name = "lms", fallbackMethod = "userFallback", type = Bulkhead.Type.SEMAPHORE)
	// @HystrixCommand(fallbackMethod = "userFallback")
	@CircuitBreaker(name = "lms", fallbackMethod = "userFallback")
	public ResponseEntity<JSONObject> getAllCoursesBycategory(Set<Integer> set) {
		ResponseEntity<JSONObject> result = null;

		try {
			RestTemplate restTemplate = new RestTemplate();
			result = restTemplate.postForEntity("http://localhost:8090/api/getAllPurchasedCoursesByCategory", set,
					JSONObject.class);
		}

		catch (Exception e) {
			String stackTrace = ExceptionUtils.getStackTrace(e);
			logger.error(stackTrace);

		}
		return result;

	}

	@Retry(name = "lms", fallbackMethod = "userFallbackRetry")
	@Bulkhead(name = "lms", fallbackMethod = "userFallback", type = Bulkhead.Type.SEMAPHORE)
	// @HystrixCommand(fallbackMethod = "userFallback")
	@CircuitBreaker(name = "lms", fallbackMethod = "userFallback")
	public ResponseEntity<LmsResponse> getAllCoursesDurationList(Set<Integer> set) {
		ResponseEntity<LmsResponse> result = null;

		try {
			RestTemplate restTemplate = new RestTemplate();
			result = restTemplate.postForEntity("http://localhost:8090/api/getAllCoursesDuration", set,
					LmsResponse.class);
		}

		catch (Exception e) {
			String stackTrace = ExceptionUtils.getStackTrace(e);
			logger.error(stackTrace);

		}
		return result;

	}

	@Retry(name = "lms", fallbackMethod = "userFallbackRetry")
	@Bulkhead(name = "lms", fallbackMethod = "userFallback", type = Bulkhead.Type.SEMAPHORE)
	// @HystrixCommand(fallbackMethod = "userFallback")
	@CircuitBreaker(name = "lms", fallbackMethod = "userFallback")
	public ResponseEntity<LmsResponse> getAllCoursesCategory() {
		ResponseEntity<LmsResponse> result = null;

		try {

			WebClient webClient = WebClient.create("http://localhost:8090");
			result = webClient.post()
					.uri("/api/getAllCategories")
					.accept(MediaType.APPLICATION_JSON)
					.retrieve()
					.bodyToMono(new ParameterizedTypeReference<ResponseEntity<LmsResponse>>() {
					})
					.block();
		}

		catch (Exception e) {
			String stackTrace = ExceptionUtils.getStackTrace(e);
			logger.error(stackTrace);

		}
		return result;

	}

	@Retry(name = "lms", fallbackMethod = "userFallbackRetry")
	@Bulkhead(name = "lms", fallbackMethod = "userFallback", type = Bulkhead.Type.SEMAPHORE)
	// @HystrixCommand(fallbackMethod = "userFallback")
	@CircuitBreaker(name = "lms", fallbackMethod = "userFallback")
	public ResponseEntity<LmsResponse> getAllapplyRecommendationAlgorithm(Set<Integer> set) {
		ResponseEntity<LmsResponse> result = null;

		try {

			RestTemplate restTemplate = new RestTemplate();
			result =

					restTemplate.postForEntity("http://localhost:8090/api/applyRecommendationAlgorithm", set,
							LmsResponse.class);
		}

		catch (Exception e) {
			String stackTrace = ExceptionUtils.getStackTrace(e);
			logger.error(stackTrace);

		}
		return result;

	}

	@Retry(name = "lms", fallbackMethod = "userFallbackRetry")
	@Bulkhead(name = "lms", fallbackMethod = "userFallback", type = Bulkhead.Type.SEMAPHORE)
	// @HystrixCommand(fallbackMethod = "userFallback")
	@CircuitBreaker(name = "lms", fallbackMethod = "userFallback")
	public ResponseEntity<Integer> getUserDuration(CourseRequest reqs) {
		ResponseEntity<Integer> result = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			result = restTemplate.postForEntity("http://localhost:8090/api/getAllCoursesDuration", reqs, Integer.class);
		}

		catch (Exception e) {
			String stackTrace = ExceptionUtils.getStackTrace(e);
			logger.error(stackTrace);

		}
		return result;

	}

	@Retry(name = "lms", fallbackMethod = "userFallbackRetry")
	@Bulkhead(name = "lms", fallbackMethod = "userFallback", type = Bulkhead.Type.SEMAPHORE)
	// @HystrixCommand(fallbackMethod = "userFallback")
	@CircuitBreaker(name = "lms", fallbackMethod = "userFallback")
	public List<UserDto> getUsers() {
		List<UserDto> result = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<List<UserDto>> rateResponse = restTemplate.exchange("http://localhost:8082/user/getUser",
					HttpMethod.GET, null, new ParameterizedTypeReference<List<UserDto>>() {
					});
			result = rateResponse.getBody();
		} catch (Exception e) {
			String stackTrace = ExceptionUtils.getStackTrace(e);
			logger.error(stackTrace);

		}
		return result;

	}

	@Retry(name = "lms", fallbackMethod = "userFallbackRetry")
	@Bulkhead(name = "lms", fallbackMethod = "userFallback", type = Bulkhead.Type.SEMAPHORE)
	// @HystrixCommand(fallbackMethod = "userFallback")
	@CircuitBreaker(name = "lms", fallbackMethod = "userFallback")
	public void createUser(UserDto user) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.postForEntity("http://localhost:8082/token/createUser", user, JSONObject.class);
		} catch (Exception e) {
			String stackTrace = ExceptionUtils.getStackTrace(e);
			logger.error(stackTrace);

		}

	}

	@Retry(name = "lms", fallbackMethod = "userFallbackRetry")
	@Bulkhead(name = "lms", fallbackMethod = "userFallback", type = Bulkhead.Type.SEMAPHORE)
	// @HystrixCommand(fallbackMethod = "userFallback")
	@CircuitBreaker(name = "lms", fallbackMethod = "userFallback")
	public ResponseEntity<ApiResponse> sendSms(Sms sms) {
		ResponseEntity<ApiResponse> result = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			result = restTemplate.postForEntity("http://localhost:8089/send/sendSMS", sms, ApiResponse.class);

		} catch (Exception e) {
			String stackTrace = ExceptionUtils.getStackTrace(e);
			logger.error(stackTrace);

		}
		return result;

	}

    /**
     * Creates a session for a checkout item.
     * Sends a POST request to an external API endpoint using WebClient and retrieves the session ID from the response.
     *
     * @param checkoutItem An object of type CheckoutDto containing the details of the checkout item.
     * @return The session ID generated for the checkout item.
     * @throws CustomException If the session ID is null.
     */
    
	@Retry(name = "lms", fallbackMethod = "userFallbackRetry")
	@Bulkhead(name = "lms", fallbackMethod = "userFallback", type = Bulkhead.Type.SEMAPHORE)
	@CircuitBreaker(name = "lms", fallbackMethod = "userFallback")
	public ResponseEntity<ApiResponse> addTocart(AddItem cartDto) {
		try {
			RestTemplate restTemplate = new RestTemplate();

			ResponseEntity<ApiResponse> result = restTemplate.postForEntity("http://localhost:8093/api/addToCart",
					cartDto,
					ApiResponse.class);

			return result;
		} catch (Exception e) {
			String stackTrace = ExceptionUtils.getStackTrace(e);
			logger.error(stackTrace);
			return null;
		}
	}

	@Retry(name = "lms", fallbackMethod = "userFallbackRetry")
	@Bulkhead(name = "lms", fallbackMethod = "userFallback", type = Bulkhead.Type.SEMAPHORE)
	// @HystrixCommand(fallbackMethod = "userFallback")
	@CircuitBreaker(name = "lms", fallbackMethod = "userFallback")
	public ResponseEntity<LmsResponse> getCartItems(int userId) {
		ResponseEntity<LmsResponse> result = null;
		try {
			JSONObject json = new JSONObject();
			json.put("LMSConstant.USER_ID", userId);
			RestTemplate restTemplate = new RestTemplate();

			result = restTemplate.postForEntity("http://localhost:8093/cart/getAllItemsInCart", json,
					LmsResponse.class);

		} catch (Exception e) {
			String stackTrace = ExceptionUtils.getStackTrace(e);
			logger.error(stackTrace);

		}
		return result;

	}

	@Retry(name = "lms", fallbackMethod = "userFallbackRetry")
	@Bulkhead(name = "lms", fallbackMethod = "userFallback", type = Bulkhead.Type.SEMAPHORE)
	// @HystrixCommand(fallbackMethod = "userFallback")
	@CircuitBreaker(name = "lms", fallbackMethod = "userFallback")
	public ResponseEntity<LmsResponse> getWishlistItems(int userId) {
		ResponseEntity<LmsResponse> result = null;
		JSONObject json = new JSONObject();
		try {
			json.put(LMSConstant.USER_ID, userId);
			RestTemplate restTemplate = new RestTemplate();

			result = restTemplate.postForEntity("http://localhost:8094/wish/getAllItemsFromWishList", json,
					LmsResponse.class);

		} catch (Exception e) {
			String stackTrace = ExceptionUtils.getStackTrace(e);
			logger.error(stackTrace);

		}
		return result;

	}

	@Retry(name = "lms", fallbackMethod = "userFallbackRetry")
	@Bulkhead(name = "lms", fallbackMethod = "userFallback", type = Bulkhead.Type.SEMAPHORE)
	// @HystrixCommand(fallbackMethod = "userFallback")
	@CircuitBreaker(name = "lms", fallbackMethod = "userFallback")
	public ResponseEntity<ApiResponse> removeAllItemsFromcart(int userId) {
		ResponseEntity<ApiResponse> result = null;
		try {
			JSONObject json = new JSONObject();
			json.put(LMSConstant.USER_ID, userId);
			RestTemplate restTemplate = new RestTemplate();
			result = restTemplate.postForEntity("http://localhost:8094/cart/removeAllItemsFromcart", json,
					ApiResponse.class);

		} catch (Exception e) {
			String stackTrace = ExceptionUtils.getStackTrace(e);
			logger.error(stackTrace);

		}
		return result;

	}

	@Retry(name = "lms", fallbackMethod = "userFallbackRetry")
	@Bulkhead(name = "lms", fallbackMethod = "userFallback", type = Bulkhead.Type.SEMAPHORE)
	// @HystrixCommand(fallbackMethod = "userFallback")
	@CircuitBreaker(name = "lms", fallbackMethod = "userFallback")
	public Map<String, Object> getPlan(int userId) throws CustomException {
		Map<String, Object> result = null;
		JSONObject json = new JSONObject();
		JSONObject response = new JSONObject();

		try {
			RestTemplate restTemplate = new RestTemplate();
			ObjectMapper oMapper = new ObjectMapper();

			json.put("userId", userId);
			ResponseEntity<JSONObject> list = restTemplate.postForEntity("http://localhost:8097/plan/getAllPlans",
					json, JSONObject.class);
			response = list.getBody();
			result = oMapper.convertValue(response.get("list"), Map.class);
			if (result == null) {
				throw new CustomException("data  is unexpectedly null. Unable to proceed.");

			} else {
				return result;
			}
		} catch (IllegalArgumentException e) {
			String stackTrace = ExceptionUtils.getStackTrace(e);
			logger.error(stackTrace);

		}
		return result;

	}

	@Retry(name = "lms", fallbackMethod = "userFallbackRetry")
	@Bulkhead(name = "lms", fallbackMethod = "userFallback", type = Bulkhead.Type.SEMAPHORE)
	// @HystrixCommand(fallbackMethod = "userFallback")
	@CircuitBreaker(name = "lms", fallbackMethod = "userFallback")
	public ResponseEntity<ApiResponse> sendEmail(Email email) {
		ResponseEntity<ApiResponse> result = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			result = restTemplate.postForEntity("http://localhost:8089/send/sendEmail", email, ApiResponse.class);

		} catch (Exception e) {
			String stackTrace = ExceptionUtils.getStackTrace(e);
			logger.error(stackTrace);

		}
		return result;

	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	@Override
	public Map<String, Object> getDropDownData() {
		Map<String, Object> response = new HashMap<>();
		try {

			genericDao.setClazz(ConfigMaster.class);
			List<ConfigMaster> configList = genericDao.findAll();
			if (configList != null) {
				for (ConfigMaster configMaster : configList) {

					String key = configMaster.getKey();
					List<ConfigSlave> list = configMaster.getConfigSlave();
					response.put(key, list);
				}
			} else {
				return new HashMap<>();
			}
		} catch (Exception e) {
			String stackTrace = ExceptionUtils.getStackTrace(e);
			logger.error(stackTrace);
		}
		return response;

	}

	@SuppressWarnings({ "unused" })
	private UserDto userFallback(int userId, Throwable throwable) {
		logger.info("userFallback invoked");
		return new UserDto();
	}

	@SuppressWarnings({ "unused" })
	private UserDto userFallbackRetry(int userId, HttpClientErrorException throwable) {
		logger.info("userFallbackRetry invoked");
		return new UserDto();
	}

}
