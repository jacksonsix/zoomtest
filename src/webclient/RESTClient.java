package webclient;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;




public class RESTClient {
	public static final Logger LOGGER = LoggerFactory.getLogger(RESTClient.class);


    static Map<String, Object> tokenBodyParams = new HashMap<>();
    //@Value("${batchorder.url}")
//	private String batchOrderServiceUrl="http://vsplordsvc01d.fleet.ad:8087/api/batch/start"; // DEV
	private String batchOrderServiceUrl="http://vsplordsvc03d:8087/api3/batch/start"; // DEV 2
	
//	private String batchServiceUrl="http://vsplordsvc01d.fleet.ad:8087/api/batch/batch-service/request/process"; // DEV
	private String batchServiceUrl="http://vsplordsvc03d:8087/api3//batch/batch-service/request/process"; // DEV 2
    
   // @Value("${oauthtoken.url}")
	private String devTokenUrl="http://vsplfrock02d.fleet.ad:8090/restapi/oauth2/accessToken";
	
	public void setServiceUrl(String serviceUrl) {
		this.batchOrderServiceUrl = serviceUrl;
	}

	
	public Long getProcessBatchResponse( @RequestBody Object params)  {
    	RestTemplate restTemplate = new RestTemplate();
    	LOGGER.info("getResponse> "+params);
    	LOGGER.info("serviceUrl: "+batchServiceUrl);
    	if(batchOrderServiceUrl == null) {
    		LOGGER.warn("Environment is not applicable: "+batchServiceUrl);
    		return -1L;
    	}
    	LOGGER.info("restTemplate: "+restTemplate);
        HttpHeaders restTemplateHeaders = new HttpHeaders();
        restTemplateHeaders.add("Authorization", getOauthToken());
        final HttpEntity<Object> entity = new HttpEntity<>(params, restTemplateHeaders);

        try {
			final ResponseEntity<Long> restResp = restTemplate.postForEntity(batchServiceUrl, entity, Long.class);
            if (restResp.getStatusCode() != HttpStatus.OK) {
            	LOGGER.error("Service unavalable! "+restResp.getStatusCode());
            	return -1L;
            }
			final Long fcResp = restResp.getBody();
            LOGGER.info("<getResponse: "+fcResp);
            return fcResp;
        } catch (Exception ex) {
            LOGGER.error("@@@@@ Error while call batch service source: "+ ex.getMessage());
            LOGGER.warn(ExceptionUtils.getStackTrace(ex));
            return -1L;
        }
    }
	
    public Map<String, Object> getStartOrderBatchResponse( @RequestBody Map<String, Object> params)  {
    	RestTemplate restTemplate = new RestTemplate();
    	LOGGER.info("getResponse> "+params);
    	LOGGER.info("serviceUrl: "+batchOrderServiceUrl);
    	if(batchOrderServiceUrl == null) {
    		LOGGER.warn("Environment is not applicable: "+batchOrderServiceUrl);
    		return getErrorResponse();
    	}
    	LOGGER.info("restTemplate: "+restTemplate);
    	String oauthToken = getOauthToken();
        HttpHeaders restTemplateHeaders = new HttpHeaders();
        restTemplateHeaders.add("Authorization", oauthToken);
        final HttpEntity<Object> entity = new HttpEntity<>(params, restTemplateHeaders);
        try {
            @SuppressWarnings("rawtypes")
			final ResponseEntity<Map> restResp = restTemplate.postForEntity(batchOrderServiceUrl, entity, Map.class);
            if (restResp.getStatusCode() != HttpStatus.OK) {
            	LOGGER.error("Service unavalable! "+restResp.getStatusCode());
            	return getErrorResponse();
            }
            @SuppressWarnings("unchecked")
			final Map<String, Object> fcResp = restResp.getBody();
            LOGGER.info("<getResponse: "+fcResp);
            return fcResp;
        } catch (Exception ex) {
            LOGGER.error("@@@@@ Error while call service source: "+ ex.getMessage());
            LOGGER.warn(ExceptionUtils.getStackTrace(ex));
            return getErrorResponse();
        }

    }

    private Map<String, Object> getErrorResponse(){
    	 return new HashMap<>();
    }
    
    public String getOauthToken() {
    	LOGGER.info("getOauthToken> ");
    	RestTemplate restTemplate = new RestTemplate();
		/*
		 * restTemplate.setMessageConverters(Arrays.asList( new
		 * StringHttpMessageConverter(), new FormHttpMessageConverter(), new
		 * MappingJackson2HttpMessageConverter()));
		 */
    	HttpHeaders restTemplateHeaders = new HttpHeaders();
        restTemplateHeaders.add("applicationToken", "8677166ce5c44fb0b693df8d201475ae");
        restTemplateHeaders.add("applicationName", "ordering");
        restTemplateHeaders.add("Content-Type", "application/x-www-form-urlencoded");
        restTemplateHeaders.add("Accept", "*/*");
        
        MultiValueMap<String, Object> bodyParams = new LinkedMultiValueMap<>();
        bodyParams.setAll(tokenBodyParams);
        final HttpEntity<Object> entity = new HttpEntity<>(bodyParams, restTemplateHeaders);
        LOGGER.info("httpEntity: "+entity);
        try {
            ResponseEntity<?> restResp = restTemplate.exchange(devTokenUrl, HttpMethod.POST, entity, String.class);
            if (restResp.getStatusCode() != HttpStatus.OK) {
            	String emessage = "Service unavalable! ";
            	LOGGER.error(emessage+restResp.getStatusCode());
            	return emessage;
            }
            
			final String fcResp = restResp.getBody().toString();
            LOGGER.info("<getResponse: "+fcResp);
            @SuppressWarnings("unchecked")
            Map<String, String> respMap = null; //JacksonUtil.fromString(fcResp, Map.class);
            LOGGER.info("responseMap: "+respMap);
            String token = respMap.get("access_token");
            LOGGER.info("<getOauthToken: "+token);
            return token;
        } catch (Exception ex) {
            LOGGER.error("@@@@@ Error while call Oauth source: "+ ex.getMessage());
            LOGGER.warn(ExceptionUtils.getStackTrace(ex));
            return ex.getMessage();
        }

    }
    
    static {
    	tokenBodyParams.put("clientId", "orderingApp");
    	tokenBodyParams.put("clientSecret", "!23OrderApp@");
    	tokenBodyParams.put("scope", "uid");
    	tokenBodyParams.put("grantType", "password");
    	tokenBodyParams.put("password", "testuat12");
    	tokenBodyParams.put("userName", "tcsint");
    	
    }
}