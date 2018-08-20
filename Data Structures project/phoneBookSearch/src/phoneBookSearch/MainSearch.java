/**
 * Author: Jason Smith
 * Date: 7-18-17
 * Title: phoneBookSearch
 * Purpose: Tests with method of searching is better for an online phone book
 * revised 8-7-17
 * changed hash table size from 27 to 13
 */
package phoneBookSearch;

import java.util.Scanner;

public class MainSearch {
	

	public static void main(String[] args) {
		// create variables JCS
		String firstName = null;
		String lastName = null;
		String email = null;
		String phoneNumber = null;
		
		
		int tableSize = 13;// NUMBER OF BUCKETS IN HASH TABLE 
		//JCS 8-7-17 CHANGED TABLE SIZE FROM 27 TO 13
		
		//CREATE NEW INSTANCE OF HASHTABLESEARCH CLASS JCS
		HashTableSearch hashTable = new HashTableSearch();
		
		//CREATE INSTANCE OF TREEDATASEARCH
		TreeDataSearch treeTable = new TreeDataSearch();
		
		hashTable.createHashArray(tableSize);
		
		System.out.println("How would you like to access your phone book");
		System.out.println("1. Insert " + "\n" + "2. Delete" + "\n" + "3. Search" + "\n" + "4. Display Binary tree" + "\n" + "5. Display Hash table");
		
		
		Scanner input = new Scanner(System.in);
		
		
		while (true) {
			
			int inputInt = Integer.parseInt(input.nextLine().toString());
	    
		switch (inputInt) {
// ADD THE INPUT TO ARRAY JCS
		case 1:
			addEntry(firstName, lastName, email, phoneNumber, hashTable, tableSize, input, treeTable);
			
			break;
// REMOVE ENTRY FROM ARRAY JCS
		case 2:
			removeEntry(firstName, lastName, tableSize, hashTable, input, treeTable);
			
			break;
// SEARCH ARRAY FOR ENTRY JCS
		case 3:
			searchEntry(firstName, lastName, tableSize, hashTable, input, treeTable);
			
			break;
//JCS SHOW TREE
			case 4:
			System.out.println("The current Binary tree is:\n");
			treeTable.display(treeTable.root);	
			System.out.println("\nHow would you like to access your phone book");
			System.out.println("1. Insert " + "\n" + "2. Delete" + "\n" + "3. Search" + "\n" + "4. Display Binary tree" + "\n" + "5. Display Hash table");
			break;
			
//JCS SHOW HASH
			case 5:
			System.out.println("The current hash table is:\n");
			hashTable.displayHash();	
			System.out.println("\nHow would you like to access your phone book");
			System.out.println("1. Insert " + "\n" + "2. Delete" + "\n" + "3. Search" + "\n" + "4. Display Binary tree" + "\n" + "5. Display Hash table");
			break;
			
		default:
			inputInt = 1;
			break;
		}
		}
		
	}

	private static void searchEntry(String firstName, String lastName, int tableSize, HashTableSearch hashTable, Scanner input, TreeDataSearch treeTable) {
		//GET INPUT FROM USER JCS
		System.out.println("Who would you like to search for.");
		System.out.println("Enter the first name");
		firstName = input.nextLine().toString();
		
		
		System.out.println("Enter " + firstName + "'s last name");
		lastName = input.nextLine().toString();
		
		//JCS SEARCH HASH TABLE
		//JCS ORIGINAL CODE CHANGED 8-7-17 String searchForStr = (firstName + lastName).toUpperCase();
		//JCS ORIGINAL CODE CHANGED 8-7-17 int hashKey = hashTable.getKey(searchForStr, tableSize);
		//JCS ORIGINAL CODE CHANGED 8-7-17 hashTable.searchEntry(hashKey);
		
		//JCS NEW CODE
		hashTable.findEntry(firstName, lastName, tableSize);
		
		//JCS SEARCH THE BINARY TREE
		treeTable.searchTree(treeTable.root, firstName, lastName);
		
		
		System.out.println("\n" + "How would you like to access your phone book");
		System.out.println("1. Insert " + "\n" + "2. Delete" + "\n" + "3. Search"  + "\n" + "4. Display Binary tree" + "\n" + "5. Display Hash table");
			
	}

	private static void removeEntry(String firstName, String lastName, int tableSize, HashTableSearch hashTable, Scanner input, TreeDataSearch treeTable) {
		//GET INPUT FROM USER JCS
		System.out.println("Who would you like to remove");
		System.out.println("Enter the first name");
		firstName = input.nextLine().toString();
		
		System.out.println("Enter " + firstName + "'s last name");
		lastName = input.nextLine().toString();
		
		String whoToDeleteStr = (firstName + lastName).toUpperCase();
		int hashKey = hashTable.getKey(whoToDeleteStr, tableSize);
		//JCS ORIGINAL CODE CHANGED 8-7-17 hashTable.deleteEntry(hashKey);
		
		//JCS NEW CODE
		hashTable.removeEntry(hashKey);
		
		treeTable.delete(treeTable.root, firstName, lastName);
		
		
		System.out.println("\n" + "How would you like to access your phone book");
		System.out.println("1. Insert " + "\n" + "2. Delete" + "\n" + "3. Search"  + "\n" + "4. Display Binary tree" + "\n" + "5. Display Hash table");
			
	}

	private static void addEntry(String firstName, String lastName, String email, String phoneNumber, HashTableSearch hashTable, int tableSize, Scanner input, TreeDataSearch treeTable) {
		//GET INPUT FROM USER JCS
		System.out.println("Who would you like to add");
		System.out.println("Enter the first name");
		firstName = input.nextLine().toString();
		
		System.out.println("Enter " + firstName + "'s last name");
		lastName = input.nextLine().toString();
		
		System.out.println("Enter " + firstName + " " + lastName + "'s email");
		email = input.nextLine().toString();
		
		System.out.println("Enter " + firstName + " " + lastName + "'s 10 digit phone number");
		phoneNumber = input.nextLine().toString();
		//PASS ALL ENTRIES TO HASHTABLE CLASS AND ADDENTRY METHOD JCS
		//JCS ORIGINAL CODE CHANGED 8-7-17 hashTable.addEntry(firstName, lastName, email, phoneNumber,tableSize);
		
		//JCS NEW CODE
		hashTable.insertEntry(firstName, lastName, email, phoneNumber, tableSize);
		
		//JCS ADD THE INFORMATION TO THE BINARY TREE TABLE
		treeTable.addNode(treeTable.root, firstName, lastName, email, phoneNumber);
		
		
		System.out.println("\n" + "How would you like to access your phone book");
		System.out.println("1. Insert " + "\n" + "2. Delete" + "\n" + "3. Search" + "\n" + "4. Display Binary tree" + "\n" + "5. Display Hash table");
		
		
		
	}

	
}
	