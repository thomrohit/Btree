
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BtreeTest {

    @Test
    public void givenABinaryTree_WhenAddingElements_ThenTreeNotEmpty() {

        BTree bt = createBinaryTree();

        assertFalse(bt == null);
    }

    @Test
    public void givenABinaryTree_WhenAddingElements_ThenTreeContainsThoseElements() {

        BTree bt = createBinaryTree();

        assertTrue(bt.isNodePresent(BTree.root,"House"));
        assertTrue(bt.isNodePresent(BTree.root,"Kitchen"));
        assertFalse(bt.isNodePresent(BTree.root,"-knife"));
    }

    @Test
    public void givenABinaryTree_WhenLookingForNonExistingElement_ThenReturnsFalse() {

    	BTree bt = createBinaryTree();

        assertFalse(bt.isNodePresent(BTree.root,"Pen"));
    }


    private BTree createBinaryTree() {
        BTree bt = new BTree();

        bt.add("Estate");
		bt.add("House");
		bt.add("Basement");
		bt.add("Kitchen");
		bt.add("-knife");
		bt.add("Estate");
		bt.add("House");
		bt.add("Basement");
		bt.add("Stairs");
		bt.add("Bedroom");
		bt.add("-pillow");

        return bt;
    }

}