package utils;

import java.io.PrintWriter;
import java.util.Vector;

/**
 *
 * @author Kannan
 *
 */
public class Node {
	private boolean leaf;	//indicates whether the node is a leaf node or has children
	private String value;	//in case of a leaf node, contains value to be printed.
							//in case of datadecl node, contains the function name
	private Vector<Node> children; //list of child nodes
	private boolean datadecl;	// indicates whether this is a datadecl node
	public int index;					//used only in case of expression nodes, contains the index of the local array containing the value
	public boolean local;				//used only in case of id nodes, indicates whether id belongs to local or global array
	private Vector<Integer> indexList;	//used only in case of expr list, contains indexes of parameters

	/**
	 * Constructor
	 */
	public Node()
	{
		leaf = true;
		value = "";
		children = new Vector<Node>();
		datadecl = false;
		index = -1;
		indexList = new Vector<Integer>();
		local = true;
	}

	/**
	 * Set node as a leaf node
	 * @param value
	 */
	public void setTerminalValue(String value)
	{
		leaf = true;
		this.value = value;
	}

	/**
	 * Set node as a data declaration node
	 * @param value = function Name
	 */
	public void setDataDeclValue(String value)
	{
		leaf = false;
		datadecl = true;
		this.value = value;
	}

	/**
	 * Set as a non-leaf node
	 */
	public void setAsIntermediate()
	{
		leaf = false;
	}

	/**
	 * Add child to list
	 * @param n <-child
	 * @return
	 */
	public boolean addChild(Node n)
	{
		if(n == null)
			return false;
		setAsIntermediate();
		children.add(n);
		return true;
	}

	/**
	 * Add parameter index to list
	 * @param i <- index of local array containing parameter value
	 */
	public void addToIndexList(int i)
	{
		indexList.add(i);
	}

	/**
	 * @return List of local array indexes containing paramter values
	 */
	public Vector<Integer> getIndexList(){
		return indexList;
	}


	public void concatIndexList(Vector<Integer> vec){
		for(int i = 0; i< vec.size(); i++)
		{
			addToIndexList(vec.elementAt(i));
		}
	}


	public boolean isLeaf() {
		return leaf;
	}

	public String getValue() {
		return value;
	}

	public boolean isDataDecl() {
		return datadecl;
	}

	public Vector<Node> getChildren() {
		return children;
	}
}
