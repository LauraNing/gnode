
public class GNode implements INode {
	private String name;
	private GNode[] children;
	private int length = 0;
	
	public GNode(String node_name) {
		name = node_name;
		children = new GNode[0];
		length = 0;
	}
	
	public String getName() {
		return name;
	}
	
	public GNode[] getChildren() {
		return children;
	}
	
	public void addChild(GNode child) {
		GNode[] temp = new GNode[length + 1];
	    System.arraycopy(children, 0, temp, 0, children.length);
	    children = temp;
		children[length] = child;
		length++;
	}	
}
