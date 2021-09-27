import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;

public class PrintPath {

	public String printPath(Node root, String src, String dest) {
		int i = 0;
		StringBuilder sb = new StringBuilder();
		String NODE_NOT_PRESENT = "\nBoth " + src + " and " + dest + " are not present in the tree";
		LinkedHashSet<Node> fullPath = findDistance(root, new Node(src, null), new Node(dest, null));
		if (fullPath.isEmpty())
			return NODE_NOT_PRESENT;
		else {
			sb.append("You are in the ");
			for (Node node : fullPath) {
				if (i == 0)
					sb.append(node.value + ".");
				else if (i!=0 && i != fullPath.size() - 1)
					sb.append(" Go to " + node.value);
				else
					sb.append(" get " + node.value + ".");
				
				i++;
			}
		}

		return sb.toString();

	}

	/**
	 * Helper function to find the level of a given node present in a binary tree
	 * and create a path to lca(lowest common ancestor) node
	 * @param root
	 * @param value
	 * @param level
	 * @param path
	 * @return
	 */
	public int findLevel(Node root, String value, int level, ArrayList<Node> path) {
		// base case
		if (root == null) {
			path.remove(path.size() - 1);// path.clear();
			return Integer.MIN_VALUE;
		}

		// return level if the node is found
		if (root.value.equalsIgnoreCase(value)) {
			path.add(root);
			return level;
		}

		path.add(root);
		// search node in the left subtree
		int left = findLevel(root.left, value, level + 1, path);

		// if the node is found in the left subtree, return the left child
		if (left != Integer.MIN_VALUE) {
			return left;
		}
		if(!path.contains(root))
			path.add(root);
		// otherwise, continue the search in the right subtree
		return findLevel(root.right, value, level + 1, path);
	}

	/**
	 * Function to find the lowest common ancestor of given nodes `x` and `y`, where
	 * both `x` and `y` are present in the binary tree.
	 * 
	 * @param root
	 * @param x
	 * @param y
	 * @return
	 */
	public Node findLCA(Node root, Node x, Node y) {
		// base case 1: if the tree is empty
		if (root == null) {
			return null;
		}

		// base case 2: if either `x` or `y` is found
		if (root.value.equalsIgnoreCase(x.value) || root.value.equalsIgnoreCase(y.value)) {
			return root;
		}

		// recursively check if `x` or `y` exists in the left subtree
		Node left = findLCA(root.left, x, y);

		// recursively check if `x` or `y` exists in the right subtree
		Node right = findLCA(root.right, x, y);

		// if `x` is found in one subtree and `y` is found in the other subtree,
		// update lca to the current node
		if (left != null && right != null) {
			return root;
		}

		// if `x` and `y` exist in the left subtree
		if (left != null) {
			return left;
		}

		// if `x` and `y` exist in the right subtree
		if (right != null) {
			return right;
		}

		return null;
	}

	/**
	 * Function to find the distance between node `x` and node `y` in a given binary
	 * tree rooted at `root` node
	 * 
	 * @param root
	 * @param x
	 * @param y
	 * @return
	 */
	public LinkedHashSet<Node> findDistance(Node root, Node x, Node y) {
		ArrayList<Node> xPath = new ArrayList<Node>();
		ArrayList<Node> yPath = new ArrayList<Node>();
		LinkedHashSet<Node> fullPath = new LinkedHashSet<Node>();

		// `lca` stores the lowest common ancestor of `x` and `y`
		Node lca = null;
		BTree bt = new BTree();
		// call LCA procedure only if both `x` and `y` are present in the tree
		if (bt.isNodePresent(root, y.value) && bt.isNodePresent(root, x.value)) {
			lca = findLCA(root, x, y);
		} else {
			return fullPath;
		}
		// return distance of `x` from lca + distance of `y` from lca
		findLevel(lca, x.value, 0, xPath);
		findLevel(lca, y.value, 0, yPath);

		Collections.reverse(xPath); // so that we get the correct path direction when we print
		fullPath.addAll(xPath);
		fullPath.addAll(yPath);
		return fullPath;
	}

}
