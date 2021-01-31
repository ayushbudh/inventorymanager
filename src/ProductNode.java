// -------------------------------- ProductNode Class -------------------------------------- 

 class ProductNode 
{
	 	// Declaring instance variables of the class
       ProductNode next;
       String ProductName;
       String Locator;
       ProductNode head;
       double Price;
       int Quantity;
       
       // Default Constructor
       ProductNode()
       {
    	   next=null;
    	   this.Price=0;
    	   this.Quantity=0;
    	   this.Locator="";
    	   this.ProductName="";
    			
       }
       
       // Parameterized Constructor
       ProductNode(String productName, String locator, int quantity, double price)
       {
    	   next=null;
    	   this.Price=price;
    	   this.Quantity=quantity;
    	   this.Locator=locator;
    	   this.ProductName= productName;
       }
       
       // getter methods to get the product information
       public String getProductName() {
    	   return this.ProductName;
       }
       
       public String getLocator() {
    	   return this.Locator;
       }
       public int getQuantity() {
    	   return this.Quantity;
       }
       public double getPrice() {
    	   return this.Price;
       }
       
       // this addProduct method is used by the newlist reference variable of class ProductNode
       public void addProduct(String productName, String locator, int quantity, double price){
   		
   		// if head is null then we create a new head
   		if (head == null){ 
   			head = new ProductNode(productName,locator,quantity,price); 
   			return; 
   		} 
   		
   		// if above condition is false new product needs to be added in the newlist inventory
   		ProductNode newProduct = new ProductNode(productName,locator,quantity,price); 

   		// getting the lastProduct so that the next of the lastProduct can be set to the newProduct 
   		ProductNode lastNode = head; 
   		while (lastNode.next != null) {
   			lastNode = lastNode.next; 
   		}

   		// making the current lastProduct in the inventory point to the newProduct so that newProduct becomes new lastProduct in the inventory
   		lastNode.next = newProduct; 	
   		return; 
   		} 
}