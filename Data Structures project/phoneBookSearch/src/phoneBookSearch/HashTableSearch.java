/**
 * Author: Jason Smith
 * Date: 7-18-17
 * Title: phoneBookSearch
 * Purpose: Tests with method of searching is better for an online phone book
 * Revision 8-7-17
 * Changed hash table structure to include nodes
 */

package phoneBookSearch;

public class HashTableSearch {
	public hashNode root;
	hashNode[] hashArray;
	
	public HashTableSearch () {
		this.root = null;
	}
	
class hashNode {
		int key;
		String firstName;
		String lastName;
		String email;
		String phoneNumber;
		hashNode leftNode;
		hashNode rightNode;
		
		public hashNode(int key, String firstName,String lastName, String email, String phoneNumber) {
			//JCS CONSTRUCTOR
			this.key = key;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.phoneNumber = phoneNumber;
			leftNode = null;
			rightNode = null;
		}
	}
	public int getKey(String value, int tableSize) {
		// GET STRING AND FIND HASHCODE JCS
		int key = Math.abs((value.hashCode() % tableSize)); // NEED MATH.ABS TO KEEP THE KEY NON NEGATIVE JCS

		return key;
	}

	// THIS IS FOR CREATING THE ARRAY USED WITH THE HASHTABLE JCS
	public void createHashArray(int arraySize) {
		hashArray = new hashNode[arraySize];
		for (int i = 0; i < hashArray.length; i++) {
			hashArray[i] = new hashNode(0, null, null, null, null);
		}
	}

	public void insertEntry(String firstName, String lastName, String email, String phoneNumber, int arraySize) {
		//JCS CONCATENATE THE FIRST AND LAST NAME FOR HASH METHOD
		String wholeNmeStr = null;
		if (firstName != null && lastName != null) {
			//JCS USER HAS INPUT VALID NAMES FOR FIRST AND LAST
			wholeNmeStr = (firstName + lastName).toUpperCase();
		}else {
			System.out.println("Please enter the whole name\n");
			return;
		}
		
		//JCS USE WHOLE NAME TO CREATE A HASH KEY
		int key = getKey(wholeNmeStr, arraySize);
		hashNode existingNode = hashArray[key];
		
		if (existingNode.firstName != null) { //JCS THIS MEANS THAT THERE IS A VALUE THERE ADD NEW NODE TO RIGHT NODE
			hashNode addedNode = new hashNode(0, null, null, null, null);
			
			addedNode.key = key;
			addedNode.firstName = firstName;
			addedNode.lastName = lastName;
			addedNode.email = email;
			addedNode.phoneNumber = phoneNumber;
			
			existingNode.rightNode = addedNode;
			addedNode.leftNode = existingNode;
			
			System.out.println(addedNode.firstName + " " + addedNode.lastName + " has been added to the hash table using the hash of: " + key + "\n");
		
		}else if (existingNode.firstName == null){
			hashNode addedNode = hashArray[key];
			addedNode.key = key;
			addedNode.firstName = firstName;
			addedNode.lastName = lastName;
			addedNode.email = email;
			addedNode.phoneNumber = phoneNumber;
			
			System.out.println(addedNode.firstName + " " + addedNode.lastName + " has been added to the hash table using the hash of: " + key + "\n");
		}
	}

	public void removeEntry(int key) {
		hashNode nodeTodelete = hashArray[key];
				
		if (nodeTodelete.firstName != null) { //JCS THERE IS A VALUE AT THIS LOCATION DELETE
			nodeTodelete = hashArray[key];
			String removalInfoStr = nodeTodelete.firstName + " " + nodeTodelete.lastName;
			if (nodeTodelete.rightNode == null && nodeTodelete.leftNode == null) { //JCS THERE IS NOTHING ATTACHED TO THE NODE OKAY TO DELETE
				nodeTodelete = null;
				hashArray[key] = new hashNode(0, null, null, null, null);
				
			}else if (nodeTodelete.rightNode != null || nodeTodelete.leftNode != null) {
				hashNode right = nodeTodelete.rightNode;
				hashNode left = nodeTodelete.leftNode;
				left.rightNode = right;
				right.leftNode = left;
				nodeTodelete = new hashNode(0, null, null, null, null);
			}
			System.out.println(removalInfoStr + " using the hash " + key + " has been removed from the hash table\n");
		}else if (nodeTodelete.firstName == null){
			//JCS THERE IS NOTHING TO DELETE
			System.out.println("The entry does not exist in the hash table or was entered wrong\n");
			return;
		}
	}
	
	public void findEntry(String firstName, String lastName, int arraySize) {
		String wholeNmeStr = (firstName + lastName).toUpperCase();
		
		int key = getKey(wholeNmeStr, arraySize);
		hashNode lookNode = hashArray[key];
		
		if (lookNode.firstName != null) {
			if (lookNode.firstName.compareToIgnoreCase(firstName) == 0 && lookNode.lastName.compareToIgnoreCase(lastName) == 0) {
			String foundInfo = "Entry found using the hash of: " + key + "\n" + "First Name: " + lookNode.firstName + 
				" Last Name: " + lookNode.lastName + "\n" + 
				"Email: " + lookNode.email + "\n" + "Phone Number: " + lookNode.phoneNumber + "\n";
			
		System.out.println(foundInfo);
		}else {
			lookNode = lookNode.rightNode;
			if (lookNode.firstName.compareToIgnoreCase(firstName) == 0 && lookNode.lastName.compareToIgnoreCase(lastName) == 0) {
				String foundInfo = "Entry found using the hash of: " + key + "\n" + "First Name: " + lookNode.firstName + 
					" Last Name: " + lookNode.lastName + "\n" + 
					"Email: " + lookNode.email + "\n" + "Phone Number: " + lookNode.phoneNumber + "\n";
				
			System.out.println(foundInfo);
			}
		}
		}else{
			System.out.println("Please enter name again there was not a match in the hash table\n");
			return;
		}
	}
	
	public void displayHash() {
		hashNode displayNode;
		String infoToDisplay = null;
		
		for (int i = 0; i < hashArray.length; i++) {
			displayNode = hashArray[i];
			if (displayNode.firstName != null) {
				infoToDisplay = (i + ". First Name: " + displayNode.firstName + 
						" Last Name: " + displayNode.lastName + "\n" + 
						"Email: " + displayNode.email + "\n" + "Phone Number: " + displayNode.phoneNumber + "\n");
				System.out.println(infoToDisplay);	
				
			}
			if (displayNode.rightNode != null) {
				displayNode = displayNode.rightNode;
				infoToDisplay = (i + ". First Name: " + displayNode.firstName + 
						" Last Name: " + displayNode.lastName + "\n" + 
						"Email: " + displayNode.email + "\n" + "Phone Number: " + displayNode.phoneNumber + "\n");
				System.out.println(infoToDisplay);
			}
		}

	}
/////////////////////////////////////////JCS BELOW IS THE ORIGINAL CODE FROM FIRST SUBMISSION///////////////////////////////////////////////////////
	/*
	public void addEntry(String firstName, String lastName, String email, String phoneNumber, int arraySize) {
		// SET PASSED IN VARIABLES TO LOCAL VARIABLES FOR MANIPULATION JCS
		String wholeNameStr = (firstName + lastName).toUpperCase(); // JCS
																	// FORCED TO
																	// UPPERCASE
																	// TO AVOID
																	// DIFFERENT
																	// HASH KEYS

		int key = getKey(wholeNameStr, arraySize);
		String infoToAdd = "First name: " + firstName + " Last name: " + lastName + "\n" + "Email: " + email
				+ " Phone number: " + phoneNumber;
		if (hashArray[key] == null) {
			hashArray[key] = infoToAdd;
			System.out.println(infoToAdd + " was added using the hash of: " + key);
		}

	}

	public void deleteEntry(int key) {
		String information = hashArray[key];
		if (hashArray[key] == null) {
			System.out.println("Entry does not exist");
		} else {
			hashArray[key] = null;
			System.out.println(information + " using the hash of: " + key + " has been removed");
			return;
		}

	}

	public void searchEntry(int hashKey) { // USE HASH KEY TO FIND POSITION IN
											// ARRAY JCS
		String searchResultStr = hashArray[hashKey];
		if (searchResultStr == null) {
			System.out.println("The entry you have entered does not exist in the hash table\n");
			return;
		} else {
			System.out.println("Entry " + searchResultStr + "\n" + "Found in phone book using hash " + hashKey);
		}

	}
*/


}
