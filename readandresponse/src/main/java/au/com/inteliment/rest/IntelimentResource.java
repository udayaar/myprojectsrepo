package au.com.inteliment.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import au.com.inteliment.model.CountResponse;
import au.com.inteliment.model.TopCountResponse;
import au.com.inteliment.service.IntelimentService;

@Path("/")
public class IntelimentResource {	

	private Logger logger = Logger.getLogger(IntelimentResource.class);	

	@Autowired
	private IntelimentService intelimentService;

	@POST
	@Path("/search")	
	@Consumes("application/json")
	@Produces("application/json")
	public Object retrieveCountOfGivenWords(String request){
		if(logger.isDebugEnabled()){
			logger.debug("Inside retrieveCountOfGivenWords");
		}		
		try{
			List<CountResponse> countResponseList = intelimentService.retrieveCountOfGivenWords(request);
			return getJSONResponse(countResponseList);
		}catch(Exception e){
			logger.error("Exception occurred and returning error response",e);
			return getJSONErrorResponse(e.getMessage());
		}			
	}

	@GET
	@Path("/top/{requiredTopCount}")	
	@Produces("text/csv")
	public Object retrieveTopCounts(@PathParam("requiredTopCount") int requiredTopCount){
		if(logger.isDebugEnabled()){
			logger.debug("Inside retrieveTopCounts");
		}
		try{
			List<TopCountResponse> topCountResponseList = intelimentService.retrieveTopCounts(requiredTopCount);
			return getCSVResponse(topCountResponseList);
		}catch(Exception e){
			logger.error("Exception occurred and returning error response",e);
			return getCSVErrorResponse(e.getMessage());
		}
	}
	
	@GET
	@Path("/retrieveSampleFile")	
	@Produces("text/html")
	public Object retrieveSampleFile(){
		if(logger.isDebugEnabled()){
			logger.debug("Inside retrieveSampleFile");
		}
		try{
			String sampleFileContent = intelimentService.retrieveSampleFile();
			return getHTMLResponse(sampleFileContent);
		}catch(Exception e){
			logger.error("Exception occurred and returning error response",e);
			return getJSONErrorResponse(e.getMessage());
		}
	}

	private String getJSONResponse(List<CountResponse> countResponseList){		
		CountResponse countResponse = null;

		StringBuilder responseContent = new StringBuilder("{");
		responseContent.append("\"counts\":[");	
		for(int i=0;i<countResponseList.size();i++){
			countResponse = countResponseList.get(i);
			responseContent.append("{");
			responseContent.append(convertToJSONElementFormat(countResponse.getGivenWord(), countResponse.getWordCount()));			
			responseContent.append("}");
			if(i != (countResponseList.size()-1))
				responseContent.append(",");
		}			
		responseContent.append("]}");
		return responseContent.toString();
	}
	
	
	private String getCSVResponse(List<TopCountResponse> topCountResponseList){		

		StringBuilder responseContent = new StringBuilder();		
		for(TopCountResponse topCountResponse : topCountResponseList){			
			responseContent.append(topCountResponse.getWord());			
			responseContent.append("|");
			responseContent.append(topCountResponse.getWordCount());
			responseContent.append("\n");			
		}		
		return responseContent.toString();
	}

	/**
	 * This method will converts the given key and value in JSON format
	 * @param key
	 * @param value
	 * @return JSON String
	 */
	private String convertToJSONElementFormat(String key, int value){
		String keyWithQuotes = "\"" + key + "\"";
		String valueWithQuotes = "\"" + value + "\"";
		return keyWithQuotes + ":" + valueWithQuotes;
	}

	/**
	 * converts the given key, value pair in JSON format
	 * @param key
	 * @param value
	 * @return
	 */
	private String convertToJSONElementFormat(String key, String value){
		String keyWithQuotes = "\"" + key + "\"";
		String valueWithQuotes = "\"" + value + "\"";
		return keyWithQuotes + ":" + valueWithQuotes;
	}

	/**
	 * This method will form the final CSV Error response
	 * @param errorWrapper
	 * @return
	 */
	private String getCSVErrorResponse(String errorResponse){
		StringBuilder responseContent = new StringBuilder();
		responseContent.append("error_response|");			
		responseContent.append(errorResponse);			
		return responseContent.toString();
	}
	
	/**
	 * This method will form the final JSON Error response
	 * @param errorWrapper
	 * @return
	 */
	private String getJSONErrorResponse(String errorResponse){
		StringBuilder responseContent = new StringBuilder("{");
		responseContent.append("\"error_response\":{");			
		responseContent.append(convertToJSONElementFormat("error", "" + errorResponse));		
		responseContent.append("}");
		return responseContent.toString();
	}
	
	private String getHTMLResponse(String sampleFileContent){
		StringBuilder responseContent = new StringBuilder("<HTML>");
		responseContent.append("<HEAD>");
		responseContent.append("<BODY>");
		responseContent.append("<p>");
		responseContent.append(sampleFileContent);
		responseContent.append("</p>");
		responseContent.append("</BODY>");
		responseContent.append("</HEAD>");
		responseContent.append("</HTML>");		
		return responseContent.toString();		
	}
	
	/**
	 * @return the intelimentService
	 */
	public IntelimentService getIntelimentService() {
		return intelimentService;
	}

	/**
	 * @param intelimentService the intelimentService to set
	 */	
	public void setIntelimentService(IntelimentService intelimentService) {
		this.intelimentService = intelimentService;
	}


}
