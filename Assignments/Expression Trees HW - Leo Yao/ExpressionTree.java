public class ExpressionTree {
    public boolean isOperator(String s) {
		return (s.equals("*") || s.equals("/") || s.equals("+") || s.equals("-") || s.equals("^"));
	}

	public Node buildTree(String postfix) {
		ArrayStack<Node> stack = new ArrayStack<>();
		String tokens[] = postfix.split(" ");
		for (String token : tokens) {
			if (!isOperator(token)) {
				Node operandNode = new Node(token);
				stack.push(operandNode);
			} else {
				Node operatorNode = new Node(token);
				Node operand2 = stack.pop();
				Node operand1 = stack.pop();
				operatorNode.leftChild = operand1;
				operatorNode.rightChild = operand2;
				stack.push(operatorNode);
			}
		}
		return stack.pop();
	}

	//preorder traversal
	public void prefix(Node node) {
		if (node != null) {
			System.out.print(node.element + " ");
			prefix(node.leftChild);
			prefix(node.rightChild);
		}
	}
	
	//inorder traversal
	public void infix(Node node) {
		if (node != null) {
			if (node.leftChild == null && node.rightChild == null) {
				System.out.print(node.element + " ");
			} else {
			System.out.print("(");
			infix(node.leftChild);
			System.out.print(node.element + " ");
			infix(node.rightChild);
			System.out.print(")");
			}
		}
	}

	//postorder traversal
	public void postfix(Node node) {
		if (node != null) {
			postfix(node.leftChild);
			postfix(node.rightChild);
			System.out.print(node.element + " ");
		}
	}
}
