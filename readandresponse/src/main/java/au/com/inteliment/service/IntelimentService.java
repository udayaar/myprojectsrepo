package au.com.inteliment.service;

import java.util.List;

import au.com.inteliment.model.CountResponse;
import au.com.inteliment.model.TopCountResponse;

public interface IntelimentService {
	/**
	 * Retrieves the count of occurrence of the given word in the paragraph
	 * @param request
	 * @return number of occurrence of the given word
	 * @throws Exception
	 */
	public List<CountResponse> retrieveCountOfGivenWords(String request) throws Exception;
	/**
	 * Retrieves the list of top occurrence of the words in the paragraph. The list size
	 * is based on the given input
	 * @param requiredTopCount
	 * @return a list of top occurrence of the words
	 * @throws Exception
	 */
	public List<TopCountResponse> retrieveTopCounts(int requiredTopCount) throws Exception;
	
	/**
	 * Retrieves the sample file and displays in HTML format
	 * @return
	 * @throws Exception
	 */
	public String retrieveSampleFile() throws Exception;
}
