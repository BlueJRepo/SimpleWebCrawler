package au.com.bluej.domain;

import java.util.ArrayList;
import java.util.List;

public class Node{
	
	private String url;
	private String title;
	private List<Node> nodes = new ArrayList<Node>();
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Node> getNodes() {
		return nodes;
	}
	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
	
	
	@Override
	public boolean equals(Object o) {
	    // self check
	    if (this == o) return true;
	    // null check
	    if (o == null) return false;
	    // type check and cast
	    if (getClass() != o.getClass())  return false;
	    Node myNode = (Node) o;
	    // field comparison
	    return myNode.url.equals(this.url);
	}
	
	@Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + url.hashCode();     
        return result;
    }
	
}
