

public interface Node{
	
	public Node getLeft(); //returns null if no left node
	public Node getRight(); //returns null if no right node
	public Integer getValue(); 
	public void setLeft(Node node);
	public void setRight(Node node);
	public Node getParent();
	public void setParent(Node node);
	
}
