package au.com.inteliment.service.transformer;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import au.com.inteliment.pojo.CountRequest;

public class RequestTransformer {
	private static Logger logger = Logger.getLogger(RequestTransformer.class);
	
	public static CountRequest createCountRequest(String request){
		if(logger.isDebugEnabled()) {
			logger.debug("request:"+request);
		}
		CountRequest countRequest = null;
		try{
			countRequest = (CountRequest)jsonToObjectConverter(request, CountRequest.class);
		}catch (JsonProcessingException e) {
			logger.error("JsonProcException", e);
			e.printStackTrace();
		}
		if(logger.isDebugEnabled()) {
			logger.debug("CountRequest: "+countRequest);
		}
		return countRequest;
	}
	
	public static Object jsonToObjectConverter(String jsonStr, Class<?> clazz) throws JsonProcessingException {
		Object obj= null;
		ObjectMapper mapp = new ObjectMapper();
		try {
			obj=  mapp.readValue(jsonStr.getBytes(), clazz);
		} catch (Exception e) {				
			e.printStackTrace();
		}
		return obj;		 
	}
}
