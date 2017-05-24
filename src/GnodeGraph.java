import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class GnodeGraph {
	public final static int MAX_DEPTH = 6;
	public final static int MAX_CHILDREN = 6;
	final static String[] alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	private GNode root;
	private int depth;
	private int maxWidth;
	private static int index = 0;     //index used to pick up the name for the next node
	
	public GnodeGraph(int d, int w) {
		//create a graph with the specified depth, 
		//each node has random number of children 0 ~ max_width,
		//with name as one from the alphabet set
		this.depth = d;
		this.maxWidth = w;
		
		if(depth > MAX_DEPTH) {
			System.out.println("input depth is too big: " + depth +"\n");
			this.depth = MAX_DEPTH;
		}
		if(maxWidth > MAX_CHILDREN) {
			System.out.println("input width is too big: " + maxWidth);
			this.maxWidth = MAX_CHILDREN;
		}
			
		this.root = new GNode(pick_name());
		//System.out.printf("depth: "+depth+" "+root.getName()+"\n");
		
		if(depth > 0) {
			Random rand = new Random();
			int random = rand.nextInt((maxWidth - 2) + 1) + 2;
			//System.out.printf("random: "+ random + "\n");
			for(int i=0; i<random; i++) {
				GNode child = new GnodeGraph(depth-1, maxWidth).getRoot();
				root.addChild(child);
			}
		}
			
	}
	
	//Return an ArrayList which contains every non-duplicated nodes
	public static ArrayList<String> walkGraph(GNode gnode) {
		String name = gnode.getName();
		
		ArrayList<String> arr;
		GNode[] children = gnode.getChildren();
		int length = children.length;
		//System.out.printf("walkGraph length: "+length +"\n");
		
		if(length == 0) {
			// it is the end node
			arr = new ArrayList<String>();
			arr.add(name);
		}
		else {
			HashSet<String> newSet = new HashSet<String>();
			newSet.add(name);	
			
			//walk through all the children
			GNode child;
			for(int i = 0; i<length; i++) {
				child = children[i];
				newSet.addAll(walkGraph(child));
			}
			arr = new ArrayList<String>(newSet);
		}
		
		return arr;
	}
	
	public static ArrayList<ArrayList<String>> paths(GNode gnode) {
		String name = gnode.getName();
		
		ArrayList<ArrayList<String>> arrListPath = null;
		ArrayList<String> arrListNode;
		arrListPath = new ArrayList<ArrayList<String>>();
		
		GNode[] children = gnode.getChildren();
		int length = children.length;
		//System.out.printf("walkGraph length: "+length +"\n");
		
		if(length == 0) {
			// it is the end node
			arrListNode = new ArrayList<String>();
			arrListNode.add(name);

			arrListPath.add(arrListNode);
		}
		else {
			//walk through all the children
			GNode child;
			for(int i = 0; i<length; i++) {
				child = children[i];
				ArrayList<ArrayList<String>> arrListChildPath = paths(child);
				ArrayList<String> childPath;
				for(int j=0; j<arrListChildPath.size(); j++) {
					childPath = arrListChildPath.get(j);
					childPath.add(0, name);
					arrListChildPath.set(j, childPath);
				}
				arrListPath.addAll(arrListChildPath);
			}
		}
		return arrListPath;
	}
	
	public GNode getRoot() {
		return this.root;
	}
	
	private String pick_name() {
		if(index >= 26) index = 0;
		String name = alphabet[index];
		index++;
		return name;
	}
	
	public static void main(String args[]) {
		
		GnodeGraph graph = new GnodeGraph(4, 3);
		GNode graph_root = graph.getRoot();
		 
		ArrayList<String> arrList = walkGraph(graph_root);
		System.out.printf("walkGraph arrayList: "+arrList.size() + "\n");
		System.out.printf("(");
		for (String p : arrList)
		    System.out.printf(p + " " );
		System.out.println(")\n");
		
		ArrayList<ArrayList<String>> arrPaths = paths(graph_root);
		System.out.printf("paths arrayList: "+arrPaths.size() + "\n");
		for (ArrayList<String> path: arrPaths) {
			System.out.printf("(");
			
			for (int i=0; i<path.size()-1; i++) {
			    System.out.printf( path.get(i) + " ");
			}
			System.out.printf( path.get(path.size()-1));
			System.out.println(")");
		}
		
	}

}
