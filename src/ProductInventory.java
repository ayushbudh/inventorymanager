// -------------------------------- ProductNode Class --------------------------------------
 class ProductInventory 
{
	// creating inventoryHead which is the head element of the linked list 
	ProductNode inventoryHead = new ProductNode(); 
	
	// creating newlist which is the head element of the linked list in which sorted linked list will be stored based on the quantity in descending order
	ProductNode newlist = new ProductNode();
	
	// max node which will be useful in removeMaximum method to return reference of the maxium node from the inventory
	ProductNode max=null;

	// shows the current inventory with all the changes made (Ex: removeMaximum(), removeProduct(), sort())
	public void showInventory(){
		
		System.out.println(" =================================================================");
		System.out.println(" ║                       ---INVENTORY---                         ║");
		System.out.println(" =================================================================");
		System.out.println("\n");
		System.out.println("-------------------------------------------------------------------");
		System.out.printf("%15s%20s%15s%15s\n","Product Name","Locator","Quantity","Price");
		System.out.println("-------------------------------------------------------------------");
		
		// curr is set to inventoryHead.next since invetoryHead is 0 and we do not need to show as in our inventory. It's just used to make the combining of 
		// the new sorted list easier
		ProductNode curr = inventoryHead.next;
		
		// traversing through each product in the inventory and printing it's information
		while(curr!=null)
		{
			
			 System.out.printf("%-28s%-18s%-14d%-22.2f\n",curr.getProductName(),curr.getLocator(),curr.getQuantity(),curr.getPrice());

			curr=curr.next;
		}
		System.out.println("\n\n");
		
	}
	
	// getting the total quantity of the products in the inventory
	public int getTotalQuantity(){
		
		ProductNode temp = inventoryHead.next;
		
		int getTotalQuantity=0;
		
		while(temp!=null) {
			getTotalQuantity= getTotalQuantity + temp.getQuantity();
			
			temp =temp.next;
		}
		return getTotalQuantity;
	}

	// getting the largest element from the inventory
	int largestElement(){  
	      
	    // variable max is initialized with the value of 10 which is the next element to the head of the inventoryHead linked-list
	    int max = inventoryHead.next.getQuantity();  
	   
	    // setting index to 0 so that the maximum value obtained can be found in the inventory and index can be calculated 
	    int index =0;
	    
	    // setting the current pointer point to the head of the inventoryHead linked-list
	    ProductNode curr = inventoryHead;
	    
	    // traversing through the inventory linked-list
	    while (curr != null){
	    	
	        // getting the product with the maximum quantity from the inventory.  
	        if (max < curr.getQuantity())  
	        {
	            max = curr.getQuantity();  
	        }
	        curr = curr.next;   
	    } 
	    // getting the index position of the node in the inventory linked-list so that it can be deleted by deleteProduct method
	    curr = inventoryHead;
	    while(curr!=null){
	    	
	    	index++;
	    	if(max==curr.getQuantity())
	    	{
	    		break;
	    	}
	    	curr=curr.next;
	    }

	    return index-1;  
	}  
	
	ProductNode deleteProduct(int position) {
		
		/* Handling Base Cases*/
		
        // If linked list is empty return null as the deletedNode 
        if ( inventoryHead== null) 
            return inventoryHead; 
  
        // Storing head node in the prevNode reference variable
        ProductNode prevNode = inventoryHead; 
  
        // If the prevNode or prevNode.next points to null value then we return prevNode as the deletednode
        if (prevNode == null || prevNode.next == null) {
        	return prevNode; 	
        }
        
        // If the position is 0 which means the head node needs to be removed from the linked-list
        if (position == 0) 
        { 
        	// changing the head of inventoryHead linked-list
        	inventoryHead = prevNode.next;   
            return prevNode; 
        } 
  
        // finding the previous node of the node that needs to be deleted
        for (int i=0; prevNode!=null && i<position-1; i++) {
        	prevNode = prevNode.next; 	
        }
  
        // storing the deleted node as the node after the previous node which will be deleted
        ProductNode deleted = prevNode.next;
        
        // deleting the node 
        prevNode.next = prevNode.next.next; 
        
        // returning the deleted node
        return deleted;
    }
    
	// removing maximum element in the inventory using method deletNode and largestElement
	// if the inventoryHead.next is null then the inventory is empty and no maximum element is present. Hence, it can't be removed, so the method throws
	// Product exception
	public ProductNode removeMaximum() {
		
		if(inventoryHead.next==null) {throw new ProductException("Inventory is Empty!Cannot Remove maximum Product.");}
		max = deleteProduct(largestElement());
		return max;
	}
	
	// sortInventory method to sort the products in the inventory in an descending order based on their quantity
	public void sortInventory(){
		
		ProductNode maxNode;
		int index=0;
		
		maxNode = removeMaximum();
		// setting the head of the newlist as the removed first maximum product from the inventory
		newlist.head=maxNode;
		// adding this maxNode to the sorted newList
		newlist.addProduct(maxNode.ProductName, maxNode.Locator, maxNode.Quantity, maxNode.Price);
		
		// calculating the number of nodes so that the loop removes all the products from the inventoryHead linked list and stores it in the newlist linked list
		int numofnodes=countallProducts()-1;
		while(index<numofnodes)
		{
			maxNode = removeMaximum();
			newlist.addProduct(maxNode.ProductName, maxNode.Locator, maxNode.Quantity, maxNode.Price);
			
			index++;
		}
		
		// setting the head of the inventoryHead as the newlist.head, so that inventory head points to the newly constructed linked list and products information 
		// can be retrieved using inventoryHead
		inventoryHead=newlist.head;
		
	}
	
	// counts all the products in the inventory
	public int countallProducts(){
		
		ProductNode curr=inventoryHead;
		int count=0;
		
		while(curr!=null)
		{	count++;
			curr=curr.next;
		}
		
		return count;
	}
	
	// adds the product in the inventory as well as in the sorted newlist
	// adds the nodes at the end of the linked list so the node that has been added points to the value null
	public void addProduct(String productName, String locator, int quantity, double price){
		
		// storing the temp as the next of the inventoryHead since inventoryHead is 0
		ProductNode temp =inventoryHead.next;
		
		// checking whether the product that needs to be added has same name and locator in the inventory to avoid assigning of different products at same location 
		if(temp!=null){
			if((temp.getProductName().equalsIgnoreCase(productName)&&temp.getLocator().equalsIgnoreCase(locator))){
				throw new ProductException("ProductName/Locator name conflict with already existing ProductName/Locator name. ");
			}
			else if((temp.getPrice()<=0|| temp.getQuantity()<0)) {
				throw new ProductException("Price/Quantity invalid! Product cannot be added.");
			}
			temp =temp.next;
		}
		
		// creating new product if the above condition is false
		ProductNode newProduct = new ProductNode(productName,locator,quantity,price); 

		// assigning the next of the newProduct as null, since it will be placed at the end of the inventory
		newProduct.next = null; 							

		// getting the lastP roduct so that the next of the lastProduct can be set to the newProduct
		ProductNode lastProduct = inventoryHead; 
		while (lastProduct.next != null) 
			lastProduct = lastProduct.next; 

		// making the current lastProduct in the inventory point to the newProduct so that newProduct becomes new lastProduct in the inventory
		lastProduct.next = newProduct; 	
		
		return; 
	} 

	// removes the product from the inventory with the parsed productName and locator arguments
	public void removeProduct(String productName, String locator){
		
		// if the inventoryHead.next is empty then the inventory is empty and no products can be removed so the method throws the exception
		if(inventoryHead.next==null) {
			throw new ProductException("Inventory is Empty! Cannot Remove Product: "+productName+" Location: "+locator);
		}
		
		// else we traverse through the inventory and remove the product with the matching productName and locator
		ProductNode temp = inventoryHead.next;
		int index=0;
		while(temp!=null){
			
			index++;
			if(temp.getProductName().equalsIgnoreCase(productName)&&temp.getLocator().equalsIgnoreCase(locator)){
				break;
			}
			
			temp=temp.next;
		}
		// removing the product
		deleteProduct(index); 
	
		
	}
	
	// counting the quantity of the product that is present in the inventory and returning the value of it
	public int countProduct(String productName){
		int totalQuantity=0;
		ProductNode temp = inventoryHead.next;
		while(temp!=null) {
			if(temp.getProductName().equalsIgnoreCase(productName)) {
				totalQuantity+=temp.getQuantity();
			}
			
			temp =temp.next;
		}
	return totalQuantity;
	}
	
	// counting the NeedeQuantity for a particular product by subtracting neededQuantity from the current Quantity of the product in the inventory
	public int countNeededQuantity(String productName, int neededQuantity)
	{ 
		int quantity;
		quantity = neededQuantity-countProduct(productName);
		return quantity;
	}
	
	// class ProductExcpetion used for throwing exception for edge cases.
	class ProductException extends RuntimeException {
		 public ProductException(String s) {
		 super(s); 
		 }
	} // end ProductException
}// End ProductInventory
 