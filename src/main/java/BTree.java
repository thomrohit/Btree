import java.util.ArrayList;

/**
 * This Project dynamically reads a config file and stores the data into a
 * Binary Tree, with each node pointing to its respective child nodes and parent
 * node if present and will tell you the shortest path from the start location
 * to the object.
 * For more information please refer the Readme.md 
 * 
 * @author Rohit Thomas Email: thomrohit@gmail.com Date:
 *         26-September-2021
 *
 */
public class BTree {

	static Node root;
	String ptr = "";

	public static void main(String[] args) {

		ReadConfigFile rcf = new ReadConfigFile();
		BTree bt = new BTree();

		ArrayList<String> dataList = rcf.readConfig();
		for (String data : dataList) {
			bt.add(data);
		}
		PrintPath pp = new PrintPath();
		/*
		 * if(args.length < 2) {
		 * System.out.print("Please enter 2 values while running the program");
		 * System.exit(0); }
		 */
		System.out.print(pp.printPath(root,args[0],args[1]));
		//System.out.print(pp.printPath(root,"bedroom","keys"));
	}


	/**
	 * Method inserts the data from config file into a BTree default we always
	 * insert left first
	 * 
	 * @param current
	 * @param value
	 * @param parent
	 * @return
	 */
	private Node addRecursive(Node current, String value, Node parent) {
		if (current == null) {
			if (value.contains("-"))
				value = value.replace("-", ""); // remove - when inserting into Tree
			return new Node(value, parent);
		}
		boolean flg = isNodePresent(current, value);
		if (flg && !value.contains("-")) { // allow items to have duplicate entry into a different node
			ptr = value;
			return current;
		}

		if (current.left == null) {
			current.left = addRecursive(current.left, value, current); // we always store & search left first
		} else if (current.left != null && current.right != null) {
			// if (ptr.contains(current.value)) // NEED TO DO : ptr needs to know where to
			// go left or right
			addRecursive(current.right, value, current); // move right when the node is full
		} else if (current.left != null) {
			if (ptr.contains(current.value))
				current.right = addRecursive(current.right, value, current); // fill right when left is full
			else
				addRecursive(current.left, value, current);
		}
		return current;
	}

	public void add(String value) {
		root = addRecursive(root, value, null);
	}

	/**
	 * Check if a node is present or not
	 * 
	 * @param current
	 * @param value
	 * @return
	 */
	public boolean isNodePresent(Node current, String value) {
		if (current == null) {
			return false;
		}
		if (value.equalsIgnoreCase(current.value)) {
			return true;
		}

		return isNodePresent(current.left, value) || isNodePresent(current.right, value);
	}

}
