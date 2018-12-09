
/**
A class of bags whose entries are stored in a chain of linked nodes.
The bag is never full.
@author Frank M. Carrano
 * This code is from Chapter 4 of
 * Data Structures and Abstractions with Java 4/e
 *      by Carrano
 * 
 * The toString method is overwritten to give a nice display of the items in
 * the bag in this format Bag{Size:# [1] [2] [3] [4] }
 * //- * @version 4.0
 */
 
 import java.util.Random;
 import java.lang.Math;
 
 
 
public final class LinkedBag<T> implements BagInterface<T> {

    private Node firstNode; // Reference to first node
    private int numberOfEntries;

    public LinkedBag() {
        firstNode = null;
        numberOfEntries = 0;
    } // end default constructor

    /** Sees whether this bag is empty.
    @return true if the bag is empty, or false if not */
    public boolean isEmpty() {
        return numberOfEntries == 0;
    } // end isEmpty

    /** Gets the current number of entries in this bag.
    @return the integer number of entries currently in the bag */
    public int getCurrentSize() {
        return numberOfEntries;
    } // end getCurrentSize


    /** Adds a new entry to this bag.
    @param newEntry The object to be added as a new entry.
    @return True. */
    public boolean add(T newEntry) // OutOfMemoryError possible
    {
        // Add to beginning of chain:
        Node newNode = new Node(newEntry);
        newNode.next = firstNode; // Make new node reference rest of chain
        // (firstNode is null if chain is empty)
        firstNode = newNode; // New node is at beginning of chain
        numberOfEntries++;
        return true;
    } // end add

    /** Retrieves all entries that are in this bag.
    @return A newly allocated array of all the entries in the bag. */
    public T[] toArray() {
        // the cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numberOfEntries]; // Unchecked cast
        int index = 0;
        Node currentNode = firstNode;
        while ((index < numberOfEntries) && (currentNode != null)) {
            result[index] = currentNode.data;
            index++;
            currentNode = currentNode.next;
        } // end while
        return result;
    } // end toArray

    /** Counts the number of times a given entry appears in this bag.
    @param anEntry The entry to be counted.
    @return The number of times anEntry appears in the bag. */
    public int getFrequencyOf(T anEntry) {
        int frequency = 0;
        int loopCounter = 0;
        Node currentNode = firstNode;
        while ((loopCounter < numberOfEntries) && (currentNode != null)) {
            if (anEntry.equals(currentNode.data)) {
                frequency++;
            }
            loopCounter++;
            currentNode = currentNode.next;
        } // end while
        return frequency;
    } // end getFrequencyOf
    
    public boolean contains(T anEntry) {
        boolean found = false;
        Node currentNode = firstNode;
        while (!found && (currentNode != null)) {
            if (anEntry.equals(currentNode.data)) 
                found = true;
             else 
                currentNode = currentNode.next;
        } // end while
        return found;
    } // end contains

    /** Removes one occurrence of a given entry from this bag, if possible.
    @param anEntry The entry to be removed.
    @return True if the removal was successful, or false otherwise. */

    public boolean remove(T anEntry) {
        boolean result = false;
        Node nodeN = getReferenceTo(anEntry);
        if (nodeN != null) {
            nodeN.data = firstNode.data; // Replace located entry with entry
                // in first node
            firstNode = firstNode.next; // Remove first node
            numberOfEntries--;
            result = true;
        } // end if
        return result;
    } // end remove

// Locates a given entry within this bag.
// Returns a reference to the node containing the entry, if located,
// or null otherwise.
    private Node getReferenceTo(T anEntry) {
        boolean found = false;
        Node currentNode = firstNode;
        while (!found && (currentNode != null)) {
            if (anEntry.equals(currentNode.data)) {
                found = true;
            } else {
                currentNode = currentNode.next;
            }
        } // end while
        return currentNode;
    } // end getReferenceTo

    public void clear() {
        while (!isEmpty()) {
            remove();
        }
    } // end clear

    private class Node {

        private T data; // Entry in bag
        private Node next; // link to next node

        private Node(T dataPortion) {
            this(dataPortion, null);
        } // end constructor

        private Node(T dataPortion, Node nextNode) {
            data = dataPortion;
            next = nextNode;
        } // end constructor
    } // end Node

    /** Removes one unspecified entry from this bag, if possible.
    @return Either the removed entry, if the removal was successful,
    or null. */
    public T remove() {
        T result = null;

        // MODIFY THIS METHOD TO REMOVE A RANDOM ITEM FROM THE BAG

				//imported Random Class and Math Class
				//The Bound of a Random function cannot be zero and hence Random shall be inside firstNode!=null
			
			
		if (firstNode != null) {
				//Generating a Random Position for removal
			Random rand=new Random();
			int removePosition=(int)Math.floor(rand.nextInt(numberOfEntries)+1);
		
				//if random Position is the first position then do this
			if(removePosition==1){
				result = firstNode.data;
				firstNode = firstNode.next; // Remove first node from chain
				numberOfEntries--;
			}
			else{
					//if random Position is >=2 then do this.
					//Traverse by scout till before the node to be removed.
				Node scout = firstNode;
				
				for(int i=0;i<removePosition-2;i++){
					scout = scout.next;
				}
					//remove the node and send its data to the result.
				numberOfEntries--;
				result=scout.next.data;
				scout.next=scout.next.next;
			}
        
        } // end if


        return result;
    } // end remove

    /** Override the toString method so that we can inspect the contents of the bag.
     * @return A string representation of the contents of the bag. */
    public String toString() {

        String result = "Bag{Size:" + numberOfEntries + " ";

        Node scout = firstNode;

        for (int index = 0; index < numberOfEntries; index++) {
            result += "[" + scout.data + "] ";
            scout = scout.next;
        } // end for

        result += "}";
        return result;
    } // end toString

    /*********************************************************************
     * 
     * METHODS TO BE COMPLETED
     * 
     * 
     ************************************************************************/
    
    /** Check to see if two bags are equals.  
     * @param aBag Another object to check this bag against.
     * @return True if the two bags contain the same objects with the same frequencies.
     */
    public boolean equals(LinkedBag<T> aBag) {
        boolean result = false; // result of comparison of bags

        // COMPLETE THIS METHOD 

			//If the number Of Entries are not equal the two chains cannot be equal.
		if(aBag.numberOfEntries!=numberOfEntries)
			result=false;
		else{
				//If entries are equal, then traverse each data element and check for equality.
			result=true;
				//Even The Order is irrespective while checking.
				
				//Point to firstNode of  This Object.
			
			Node scoutThis=firstNode;
			
			for (int index = 0; index < numberOfEntries; index++) {
				
					//CheckOrderIrrespective is to check if the element is found anywhere in another Chain.
				boolean checkOrderIrrespective=false;
				Node scout = aBag.firstNode;
				
					//If data is not found anywhere then result is false i.e. They are not equal.
				for(int secondChainIndex=0; secondChainIndex < numberOfEntries; secondChainIndex++){
					if(scout.data.equals(scoutThis.data)){
					checkOrderIrrespective=true;
					break;
					}
					scout = scout.next;
				}
				
					//If result is still false, then the data is non-existent
				if(!checkOrderIrrespective){
					result=false;
					break;
				}
				
				scoutThis=scoutThis.next;
			} //end of for
				//The loop shall iterate untill the end of both the chains are reached.
			
		}
		
        return result;
    }  // end equals

    /** Duplicate all the items in a bag.
     * @return True if the duplication is possible.
     */
    public boolean duplicateAll() {
        boolean success = true; // should always return true
                                // if there is a problem allocating nodes
                                // an exception will be thrown

        // COMPLETE THIS METHOD 
		Node scout=firstNode;
		int originalNumberOfEntries=numberOfEntries;
			//scoutThis shall be null for testBag that's null.
			//if testBag would be null then number of entries will be Zero and hence it will not go in loop and give true.
			//OriginalNumberOfEntries as numberOfEntries will increase as elements are added into it.
		
		try{
			for (int index = 0; index < originalNumberOfEntries; index++) {
				add(scout.data);
				scout = scout.next;
			} 
		}catch(Exception e){
			success=false;
		}
			//Catching exception in case the exception of PROBLEM ALLOCATING NODES occurs!
		
        return success;
    }  // end duplicateAll

    /** Remove all duplicate items from a bag
     */
    public void removeDuplicates() {

        // COMPLETE THIS METHOD

			//Duplicates are possible at the minimum when the numberOfEntries are >=2
		if(numberOfEntries>=2){
			Node scout=firstNode;
			
				//Outer Loop for Scout, which represents the element that shall be compared for equality with every other.
			for(int index=0;index<numberOfEntries;index++){
				Node secondScout=scout;
				int elementsDeleted=0;
				
					//The loop that shall compare every element with the Scout element
				for(int secondIndex=index+1;secondIndex<numberOfEntries;secondIndex++){
					boolean deleted=false;
					
						//Loop to compare the Second Scout Element to Scout Element.
					if(secondScout.next.data.equals(scout.data)){
						secondScout.next=secondScout.next.next;
						elementsDeleted++;
						deleted=true;
					}
					
						//Move the pointer only if the element has NOT matched with the Source element and hence is not deleted.
					if(!deleted)
						secondScout=secondScout.next;
				}
					//change the number of Entries to new value;
				numberOfEntries=numberOfEntries-elementsDeleted;
				
				scout=scout.next;
			}
		}
		
        return;
    }  // end removeDuplicates
}
