// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )	   --> Insert x
// void remove( x )	   --> Remove x
// Comparable find( x )   --> Return item that matches x
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )	 --> Return true if empty; else false
// void makeEmpty( )	  --> Remove all items
// void printTree( )	  --> Print tree in sorted order

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class modifiedBST
{
	/**
	 * Construct the tree.
	 */
	public modifiedBST( )
	{
		root = null;
	}

	/**
	 * Remove from the tree. Nothing is done if x is not found.
	 * @param x the item to remove.
	 */
	public void remove( Comparable x )
	{
		root = remove( x, root );
	}
 
	/**
	 * Find the smallest item in the tree.
	 * @return smallest item or null if empty.
	 */
	public Comparable findMin( )
	{
		return elementAt( findMin( root ) );
	}

	/**
	 * Find the largest item in the tree.
	 * @return the largest item of null if empty.
	 */
	public Comparable findMax( )
	{
		return elementAt( findMax( root ) );
	}

	/**
	 * Find an item in the tree.
	 * @param x the item to search for.
	 * @return the matching item or null if not found.
	 */
	public Comparable find( Comparable x )
	{
		return elementAt( find( x, root ) );
	}

	/**
	 * Make the tree logically empty.
	 */
	public void makeEmpty( )
	{
		root = null;
	}

	/**
	 * Test if the tree is logically empty.
	 * @return true if empty, false otherwise.
	 */
	public boolean isEmpty( )
	{
		return root == null;
	}

 
	/**
	 * Print the tree contents in sorted order.
	 */
	public void printTree( )
	{
		if( isEmpty( ) )
			System.out.println( "Empty tree" );
		else
			printTree( root );
	}

	/**
	 * Internal method to get element field.
	 * @param t the node.
	 * @return the element field or null if t is null.
	 */
	private Comparable<?> elementAt( TreeNode<?> t )
	{
		return t == null ? null : (Comparable<?>) t.element;
	}

	/**
	 * Internal method to insert into a subtree.
	 * @param x the item to insert.
	 * @param t the node that roots the tree.
	 * @return the new root.
	 */
	public TreeNode insert( Comparable x, TreeNode t, double distance)
	{
/* 1*/	  if( t == null )
/* 2*/		  t = new TreeNode( x, null, null, distance);
/* 3*/	  else if( x.compareTo( t.element ) < 0 )
/* 4*/		  t.left = insert( x, t.left, distance + 1);
/* 5*/	  else if( x.compareTo( t.element ) > 0 )
/* 6*/		  t.right = insert( x, t.right, distance + 1);
/* 7*/	  else
/* 8*/		  ;  // Duplicate; do nothing
/* 9*/	  return t;
	}

 	public TreeNode insertBad ( Comparable x, TreeNode t )
	{
	  if( t == null )
		  t = new TreeNode( x, null, null, 1.0);
	  else
		  t.left = insertBad( x, t.left );
	  return t;
	}

	/**
	 * Internal method to remove from a subtree.
	 * @param x the item to remove.
	 * @param t the node that roots the tree.
	 * @return the new root.
	 */
	private TreeNode remove( Comparable x, TreeNode t )
	{
		if( t == null )
			return t;   // Item not found; do nothing
		if( x.compareTo( t.element ) < 0 )
			t.left = remove( x, t.left );
		else if( x.compareTo( t.element ) > 0 )
			t.right = remove( x, t.right );
		else if( t.left != null && t.right != null ) // Two children
		{
			t.element = findMin( t.right ).element;
			t.right = remove( (Comparable) t.element, t.right );
		}
		else
			t = ( t.left != null ) ? t.left : t.right;
		return t;
	}

	/**
	 * Internal method to find the smallest item in a subtree.
	 * @param t the node that roots the tree.
	 * @return node containing the smallest item.
	 */
	private TreeNode findMin( TreeNode t )
	{
		if( t == null )
			return null;
		else if( t.left == null )
			return t;
		return findMin( t.left );
	}

	/**
	 * Internal method to find the largest item in a subtree.
	 * @param t the node that roots the tree.
	 * @return node containing the largest item.
	 */
	private TreeNode findMax( TreeNode t )
	{
		if( t != null )
			while( t.right != null )
				t = t.right;

		return t;
	}
 

	/**
	 * Internal method to find an item in a subtree.
	 * @param x is item to search for.
	 * @param t the node that roots the tree.
	 * @return node containing the matched item.
	 */
	private TreeNode find( Comparable x, TreeNode t )
	{
		if( t == null )
			return null;
		if( x.compareTo( t.element ) < 0 )
			return find( x, t.left );
		else if( x.compareTo( t.element ) > 0 )
			return find( x, t.right );
		else
			return t;	// Match
	}

	/**
	 * Internal method to print a subtree in sorted order.
	 * @param t the node that roots the tree.
	 */
	private void printTree( TreeNode t )
	{
		if( t != null )
		{
			printTree( t.left );
			System.out.println( t.element );
			printTree( t.right );
		}
	}

	  /** The tree root. */
	private TreeNode root;

	public boolean equals (Object o)
	{
		if (!(o instanceof modifiedBST))
			return false;
		
		modifiedBST other = (modifiedBST) o;
		
		return equals (this.root, other.root);
	}
	
	private static boolean equals (TreeNode th, TreeNode other)
	{
		if ((th == null) && (other == null))
			return true;
                else if ((th == null) || (other == null))
                        return false;
 		else if (!(th.element.equals (other.element)))
			return false;
		else 
			return (equals (th.left, other.left)  && 
				   equals (th.right, other.right));		
	}

	// use a min-heap to order the vertex distances
	public TreeNode findClosest(TreeNode root, Comparable target)
	{
		// create a min-heap
		BinaryHeap<TreeNode> heap = new BinaryHeap<TreeNode>();
		// insert the root with distance 0
		heap.insert(new TreeNode(root, null, null, 0));
		// while the heap is not empty
		while (!heap.isEmpty()) {
			// get the min element
			TreeNode min = heap.deleteMin();
			// while min is not null and min is not the target
			while (min != null && !min.element.equals(target)) {
				// if min is the target, return it
				if (min.element.equals(target)) {
					return min;
				}
				
				// place min's children into the heap
				// use the distance from the root to the child as the sort key
				// set child.distance += parent.distance each insertion
				// for the root node, set distance to 0				
				if (min.left != null) {
					min.left.distance += min.distance;
					heap.insert(min.left);
				}
				if (min.right != null) {
					min.right.distance += min.distance;
					heap.insert(min.right);
				}
			}			
		}
		// return null if the target is not found
		return null;
	}

	// read the file and build the tree
	public TreeNode buildBinaryTree(String fileName) {
		try {
			Scanner scanner = new Scanner(new File(fileName));
			Stack<TreeNode> stack = new Stack<>();

			while (scanner.hasNext()) {
				String temp = scanner.next();

				if (temp.equals("(")) {
					// Do nothing
				} else if (temp.equals(")")) {
					TreeNode rightChild = stack.pop();
					TreeNode leftChild = stack.pop();
					TreeNode parent = stack.pop();

					parent.left = leftChild;
					parent.right = rightChild;

					stack.push(parent);
				} else {
					TreeNode node = new TreeNode(temp,null, null,  Double.parseDouble(scanner.next()));
					stack.push(node);
				}
			}
			scanner.close();
			// At the end, the stack should contain a single TreeNode, which is the root
			return stack.pop();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null; // Handle the exception according to your needs
		}
	}
	
	public static void main(String args[]) {
		// read the file and build the tree
		modifiedBST tree = new modifiedBST();

		// tree.buildBinaryTree(args[0]);
		tree.buildBinaryTree("a 0 ( b 3 c 7 ( d 1 e 3 ) )");
	    // Read the tree and search for "*"
		// Return the distance of "*" or null if not found
		// Return the shortest distance of "*" if it appeared multiple times
		TreeNode result = tree.findClosest(tree.root,"*");
		if (result != null) {
			System.out.println("Found \"*\" at distance " + result.distance + ".");
		} else {
			System.out.println("* not found!");
		}
	}
}
