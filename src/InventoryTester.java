/*
 * Name: Ayush Budhwani
 * Description: An inventory management program.
 */

/*------------------------------ InventoryTester Class ------------------------------------*/
public class InventoryTester 
{
	// main method
	public static void main(String[] args)
	{
		// creating inventory by instantiating class ProductInventory
		ProductInventory inventory = new ProductInventory();
		
		// Adding Products to inventory	
		inventory.addProduct("LCD Screen 4.2 x 9.1", "shelf2a", 2, 10);
		inventory.addProduct("Transistors 2T", "shelf2b", 3, 13
				);
		// Show inventory
		inventory.showInventory();
	
		// Sort Products in inventory
		inventory.sortInventory();
		
		// Show inventory After sorting
		inventory.showInventory();
		
		System.out.println(" =================================================================");
		System.out.println(" ║                      ---Audit Summary---                      ║");
		System.out.println(" =================================================================");
		System.out.println("\nAvailable Quantity of product \"LCD Screen 4.2 x 9.1\": "+inventory.countProduct("LCD Screen 4.2 x 9.1")); // Should return 14
		System.out.println("\nNeeded Quantity of product \"Transistors 2T\": "+inventory.countNeededQuantity("Transistors 2T", 15));		   //Should return 3 
		
		// removes element with the maximum quantity
		//inventory.removeMaximum();
		//inventory.removeProduct("Apple iphone 7 plus 32gb rosegold", "shelf4"); 
		
		System.out.println("\nTotal quantity: "+inventory.getTotalQuantity()); // Should return 12

	} 
}