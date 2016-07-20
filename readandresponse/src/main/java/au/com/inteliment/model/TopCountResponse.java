package au.com.inteliment.model;

public class TopCountResponse {
	private String word;
	private int wordCount;
	/**
	 * @return the word
	 */
	public String getWord() {
		return word;
	}
	/**
	 * @param word the word to set
	 */
	public void setWord(String word) {
		this.word = word;
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
		return "TopCountResponse [word=" + word + ", wordCount=" + wordCount + "]";
	}
}
