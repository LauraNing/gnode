# gnode
GNode class represents a GNode, with a String name and a variable number of child GNodes as its attributes, it has the two interfaces implemented: 
public String getName();
public GNode[] getChildren(); 

GnodeGraph class can be used to instanciate an acyclic graph with gnodes, with the graph maximal depth and maximal number of children that a gnode can have specified. The name string of any gnode is picked up from the 26 alphabet characters. 
