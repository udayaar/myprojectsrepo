package au.com.inteliment.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import au.com.inteliment.model.CountResponse;
import au.com.inteliment.model.TopCountResponse;
import au.com.inteliment.pojo.CountRequest;
import au.com.inteliment.service.transformer.RequestTransformer;

@Service ("intelimentService")
public class IntelimentServiceImpl implements IntelimentService{

	private Logger logger = Logger.getLogger(IntelimentService.class);

	@Override
	public List<CountResponse> retrieveCountOfGivenWords(String request) throws Exception{
		List<CountResponse> countResponseList = null;
		CountResponse countResponse = null;
		int wordCount = 0;
		CountRequest countRequest = RequestTransformer.createCountRequest(request);
		List<String> searchTextList = countRequest.getSearchText();
		countResponseList = new ArrayList<CountResponse>(searchTextList.size());			
		for(String searchText : searchTextList){
			wordCount = countWord(searchText);
			countResponse = new CountResponse();
			countResponse.setGivenWord(searchText);
			countResponse.setWordCount(wordCount);
			countResponseList.add(countResponse);
		}
		return countResponseList;
	}

	@Override
	public List<TopCountResponse> retrieveTopCounts(int requiredTopCount) throws Exception {

		BufferedReader br = null;   
		List<TopCountResponse> topCountResponseList = new ArrayList<TopCountResponse>(requiredTopCount);
		TopCountResponse topCountResponse = null;
		InputStream is = null;
		Map<String, Integer> wordMap = new HashMap<String, Integer>();
		try {

			is = getClass().getClassLoader().getResourceAsStream("sampleFile.txt");
			if(is!=null){
				br = new BufferedReader(new InputStreamReader(is));
				String line = null;
				while((line = br.readLine()) != null){
					StringTokenizer st = new StringTokenizer(line, " ");
					while(st.hasMoreTokens()){
						String tmp = st.nextToken().toLowerCase();
						tmp = tmp.replaceAll("[,.:]", "");
						if(wordMap.containsKey(tmp)){
							wordMap.put(tmp, wordMap.get(tmp)+1);
						} else {
							wordMap.put(tmp, 1);
						}
					}
				}
			}else{
				throw new Exception("InputStream is null");
			}
		}finally{
			try{
				if(br != null) br.close();
			}catch(Exception ex){
				logger.error("Exception occurred when closing buffer reader",ex);
				throw new Exception(ex.getMessage());
			}
		}

		List<Entry<String, Integer>> sortedList = sortByValue(wordMap);
		int count = 0;
		for(Map.Entry<String, Integer> entry:sortedList){
			if(count<requiredTopCount){
				topCountResponse = new TopCountResponse();
				topCountResponse.setWord(entry.getKey());
				topCountResponse.setWordCount(entry.getValue());
				topCountResponseList.add(topCountResponse);
				count++;
			}else{
				break;
			}        	
		}
		return topCountResponseList;
	}

	@Override
	public String retrieveSampleFile() throws Exception {
		
		BufferedReader br = null;  		
		StringBuilder outputFileContent = new StringBuilder();
		InputStream is = null;		
		try {

			is = getClass().getClassLoader().getResourceAsStream("sampleFile.txt");
			if(is!=null){
				br = new BufferedReader(new InputStreamReader(is));
				String line = null;
				while((line = br.readLine()) != null){
					outputFileContent.append(line);
					outputFileContent.append("<br/>");
				}
			}else{
				throw new Exception("InputStream is null");
			}
		}finally{
			try{
				if(br != null) br.close();
			}catch(Exception ex){
				logger.error("Exception occurred when closing buffer reader",ex);
				throw new Exception(ex.getMessage());
			}
		}
		return outputFileContent.toString();
	}

	/**
	 * Sorts the given Map object in descending order based on the number of occurrence
	 * of the word
	 * @param wordMap
	 * @return Sorted List of objects
	 */
	private List<Entry<String, Integer>> sortByValue(Map<String, Integer> wordMap){

		Set<Entry<String, Integer>> set = wordMap.entrySet();
		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
		Collections.sort( list, new Comparator<Map.Entry<String, Integer>>()
		{
			public int compare( Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2 )
			{
				return (o2.getValue()).compareTo( o1.getValue() );
			}
		} );
		return list;
	}

	/**
	 * Counts the number of occurrence of the given word in the paragraph
	 * @param word
	 * @return number of occurrence of the given word
	 * @throws Exception
	 */
	private int countWord(String word) throws Exception {
		int count = 0;
		InputStream is = getClass().getClassLoader().getResourceAsStream("sampleFile.txt");
		if(is!=null){
			Scanner scanner = new Scanner(is);		
			while (scanner.hasNextLine()) {
				String nextToken = scanner.next();
				nextToken = nextToken.replaceAll("[,.:]", "");			
				if (nextToken.equalsIgnoreCase(word))
					count++;
			}
			scanner.close();
			return count;
		}else{
			throw new Exception("InputStream is null");
		}

	}


}
