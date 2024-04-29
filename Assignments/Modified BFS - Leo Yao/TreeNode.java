public class TreeNode<AnyType> implements Comparable<TreeNode<AnyType>>{
	// Friendly data; accessible by other package routines
	AnyType element;	    // The data in the node
	TreeNode<AnyType> left;		    // Left child
	TreeNode<AnyType> right;		    // Right child
    Double distance;        // Distance from root
	// Constructors
	public TreeNode( AnyType theElement )
	{
		this( theElement, null, null, 1);
	}

	public TreeNode( AnyType theElement, TreeNode<AnyType> lt, TreeNode<AnyType> rt, double distance)
	{
		element  = theElement;
		left	 = lt;
		right	= rt;
        this.distance = distance;
	}

    //compareTo method
    public int compareTo(TreeNode<AnyType> other){
        return this.distance.compareTo(other.distance);
    }
}
