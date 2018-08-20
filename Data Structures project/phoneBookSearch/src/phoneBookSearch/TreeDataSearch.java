/**
 * Author: Jason Smith
 * Date: 7-18-17
 * Title: phoneBookSearch
 * Purpose: Tests with method of searching is better for an online phone book
 */
package phoneBookSearch;

public class TreeDataSearch {
	public Node root;
	
	//JCS TREEDATASEARCH CONSTRUCTOR
	 public TreeDataSearch() {
		this.root = null;
	}
	
	class Node {
		
		String nodeData;
		String lastName;
		String email;
		String phoneNumber;
		Node parent;
		Node leftNode;
		Node rightNode;
		
		//JCS CREATE CONTRUCTOR
		public Node (String nodeData, String lastName, String email, String phoneNumber) {
			//JCS CONSTRUCTOR
			this.nodeData = nodeData;
			this.lastName = lastName;
			this.email = email;
			this.phoneNumber = phoneNumber;
			parent = null;
			leftNode = null;
			rightNode = null;
		}
	}
	
	public void addNode(Node node, String firstName, String lastName, String email, String phoneNumber) {
		
		//JCS CHECK IF THERE IS A NAME IN THE ROOT
		if(root == null) { //JCS THERE IS NOTHING IN THE TREE SO ADD NEW NODE
			node = new Node(firstName, lastName, email, phoneNumber);
			System.out.println(firstName + " " + lastName + " was added to tree");
			root = node;
			//JCS RETURN EXITS 
			return;
		}//JCS CHECK CONDITIONS IF ROOT DOES EXIST 
		else if (firstName.compareToIgnoreCase(node.nodeData) < 0 && node.leftNode == null) { //JCS FIND IF FIRST NAME IS LESS THAN CURRENT NODE AND PUT IN LEFT NULL NODE
			node.leftNode = new Node(firstName, lastName, email, phoneNumber); //JCS PUT DATA IN LEFT NODE
			node.leftNode.parent = node; //JCS GIVE LEFT NODE THE VALUE OF NODE 
			System.out.println(firstName + " " + lastName + " was added to tree");
		}
		else if (firstName.compareToIgnoreCase(node.nodeData) > 0 && node.rightNode == null) {//JCS FIND IF FIRST NAME IS LESS THAN CURRENT NODE AND PUT IN RIGHT NULL NODE
			node.rightNode = new Node(firstName, lastName, email, phoneNumber); //JCS PUT DATA IN RIGHT NODE
			node.rightNode.parent = node; //JCS GIVE RIGHT PARENT THE VALUE OF NODE
			System.out.println(firstName + " " + lastName + " was added to tree");
		}
		else {//JCS IF NO NULL NODES CALL METHOD RECURSIVE
			if (firstName.compareToIgnoreCase(node.nodeData) < 0) {
				addNode(node.leftNode, firstName, lastName, email, phoneNumber);
			}
			else {
				addNode(node.rightNode, firstName, lastName, email, phoneNumber);
			}
		}
		if (firstName.compareToIgnoreCase(node.nodeData) == 0) {
			System.out.println("Entry " + firstName + " " + lastName + " already exists");
		} 
		
	}
	
	public void display(Node root) {
		if(root != null) {
			display(root.leftNode);
			System.out.println(root.nodeData + " " + root.lastName + "\n" + root.email + "\n" + root.phoneNumber + "\n");
			display(root.rightNode);
			
		}
		
	}

	public Node searchTree(Node node, String firstName, String lastName) {
		String wholeNameFromNodes = null;
		String wholeNameStr = null;
		if (node != null) {
		//JCS GET WHOLE NAME FROM NODE BECAUSE FIRST AND LAST ARE USED FOR SEARCH
		wholeNameFromNodes = node.nodeData + node.lastName;
		//JCS CREATE WHOLE NAME FROM INPUT FOR COMPARISON
		wholeNameStr = (firstName + lastName);
		}
		if (node == null) {//JCS CHECK THAT THERE IS A FIRST NAME IN THE NODE BEFORE PROCEEDING
			System.out.println("The entry does not exist in the tree");
			return node;
		}

		//JCS COMPARE NAMES IN THE NODE TO THE NAMES INPUT IF RETURNS -1 or 1 THE NAME DOES NOT EXIST IN NODE DO NOT PROCEED
		if (node.nodeData.compareToIgnoreCase(firstName) != 0) {
			System.out.println("The entry does not exist in the binary tree or was entered wrong");
			return node;
		}
		
		//JCS COMPARE FIRST AND LAST  OF BOTH NODE AND INPUT
		if (wholeNameFromNodes.equalsIgnoreCase(wholeNameStr)) {
			System.out.println("\nEntry exists in tree:" + "\n" + node.nodeData + " " + node.lastName + "\n" + node.email + "\n" + node.phoneNumber + "\n");
			
		}
		else
        {//JCS IF CANNOT FIND MATCH IN PARENT RECURSE THROUGH CHILDREN
            if (wholeNameStr.compareToIgnoreCase(wholeNameFromNodes) < 0)
            {
                searchTree(node.leftNode, firstName, lastName);
            }
            if (wholeNameStr.compareToIgnoreCase(wholeNameFromNodes) > 0)
            {
                searchTree(node.rightNode, firstName, lastName);
            }
            
        }
		return node;
		
	}

	public Node delete(Node node, String firstName, String lastName) {
		String wholeStr = null;
		String wholeNode = null;
		if (node != null) {
		//JCS SET TO NULL IN CASE FIRST AND LAST ARE NON EXISTENT
		wholeStr = (firstName + lastName).toUpperCase();
		wholeNode = (node.nodeData + node.lastName).toUpperCase();
		}
		//JCS NODE SHOULD NOT BE NULL BUT JUST IN CASE HANDLE WITH ERROR MESSAGE
		if (node == null) {
			System.out.println("\nThere is nothing to delete");
			return node;
		}
		
		//JCS DECIDE WHICH SIDE OF THE TREE TO GO USING RECURRSION
		if (wholeStr.compareToIgnoreCase(wholeNode) < 0) {//JCS GO LEFT IF FIRST AND LAST NAME IS LESS THAN ROOT
			node.leftNode = delete(node.leftNode, firstName, lastName);
		} else if (wholeStr.compareToIgnoreCase(wholeNode) > 0) {//JCS ELSE GO DOWN THE RIGHT SIDE OF TREE
			node.rightNode = delete(node.rightNode, firstName, lastName);
		} else {
			if (node.rightNode == null || node.leftNode == null) { //JCS IF NO RIGHT OR LEFT MEANS ONLY ONE NODE OR IS A LEAF
				Node holdingNode = null;
				if (node.leftNode == null) {
					holdingNode = node.rightNode;//JCS NO LEFT CHILD GO DOWN RIGHT CHILD PATH
				}else {
					holdingNode = node.leftNode;//JCS VICE VERSA
				}
				
				if (holdingNode == null) { //JCS NO PLACE TO GO FOR REPLACEMENT NODE
					return null;
				}else {
					return holdingNode;
				}
			}else {//JCS GET REPLACEMENT NODE
				Node replacementNode = getReplacement(node);
				node.nodeData = replacementNode.nodeData;//JCS REPLACE NODE TO BE DELETED 
				//JCS RECURSE THROUGH DELETE TO LINK RIGHT NODES
				node.rightNode = delete(node.rightNode, replacementNode.nodeData, node.lastName);
				return node;
			}
		}
	
	return node;
		
			
	}

	private Node getReplacement(Node node) {
		//JCS GO RIGHT THEN CONTINUE DOWN LEFT PATH FOR REPLACEMENT NODE RETURN NODE THAT WILL REPLACE DELETED NODE
		Node holdNode = node.rightNode;
		
		while (holdNode.leftNode != null) {
			holdNode = holdNode.leftNode;
		}
		
		return holdNode;
	}
}

