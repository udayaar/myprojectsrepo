package au.com.inteliment.model;

public class CountResponse {
	
	private String givenWord;
	
	private int wordCount;

	/**
	 * @return the givenWord
	 */
	public String getGivenWord() {
		return givenWord;
	}

	/**
	 * @param givenWord the givenWord to set
	 */
	public void setGivenWord(String givenWord) {
		this.givenWord = givenWord;
	}

	/**
	 * @return the wordCount
	 */
	public int getWordCount() {
		return wordCount;
	}

	/**
	 * @param wordCount the wordCount to set
	 */
	public void setWordCount(int wordCount) {
		this.wordCount = wordCount;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CountResponse [givenWord=" + givenWord + ", wordCount=" + wordCount + "]";
	}	
}
