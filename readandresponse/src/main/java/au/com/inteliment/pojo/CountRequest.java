package au.com.inteliment.pojo;

import java.util.List;

public class CountRequest {

	private List<String> searchText;

	/**
	 * @return the searchText
	 */
	public List<String> getSearchText() {
		return searchText;
	}

	/**
	 * @param searchText the searchText to set
	 */
	public void setSearchText(List<String> searchText) {
		this.searchText = searchText;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CountRequest [searchText=" + searchText + "]";
	}	
}
