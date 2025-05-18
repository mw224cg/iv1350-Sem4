/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.pointofsale.integration;
import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.pointofsale.model.SaleDTO;
import se.kth.iv1350.pointofsale.model.SoldItem;

/**
 * Contains information about the items and their quantity in the inventory.
 * 
 */
public class ExternalInventorySystem {
    private final List<InventoryItem> inventory = new ArrayList<>();
    private static final ExternalInventorySystem EXTERNAL_INVENTORY_SYSTEM = new ExternalInventorySystem();
    
    /**
    * Creates an instance of the Inventory system and fills inventory with items 
    * and quantity of each item.
    */
    private ExternalInventorySystem(){
        addItems();
    }
    
    public static ExternalInventorySystem getExternalInventorySystem(){
        return EXTERNAL_INVENTORY_SYSTEM;
    }
    
    private void addItems(){
        inventory.add(new InventoryItem(new ItemDTO("Cucumber", 1234, 12.50, 6.0),50));
        inventory.add(new InventoryItem(new ItemDTO("Milk 1L", 1111, 15.95, 6.0),100));
        inventory.add(new InventoryItem(new ItemDTO("Butter 500g", 2222, 49.95, 15.0),120));
        inventory.add(new InventoryItem(new ItemDTO("Apple, red", 3333, 5.95, 6.0),100));
        inventory.add(new InventoryItem(new ItemDTO("Banana", 4444, 7.0, 6.0),400));
        inventory.add(new InventoryItem(new ItemDTO("Coca Cola, 33cl", 5555, 15.95, 20.0),100));
    }
    
    /**
     * Searches the inventory for an item based on its item ID
     * @param itemID                    The identifier for the searched item
     * @return                          A DTO describing the searched item
     * @throws ItemNotFoundException    Thrown if the itemID doesn't
     *                                  exist in the inventory
     */
    public ItemDTO getItemDescription(int itemID)throws ItemNotFoundException{
        if(itemID == 8008){
            throw new DataBaseFailureException("Inventory database can't be reached, check connection and try again...");
        }
        for(InventoryItem invItem : inventory){
            if(invItem.getItem().matchesItemID(itemID)){
                return invItem.getItem();
            }
        }
        throw new ItemNotFoundException(itemID);
    }
    
    /**
     * Updates the inventory based on a sale.
     * @param saleInformation       contains information about the sale.
     */
    public void updateInventory(SaleDTO saleInformation){
        if(saleInformation == null){
            return;
        }
        for(SoldItem soldItem : saleInformation.getSoldItems()){
            updateInventoryForItem(soldItem);
        }
    }
    
    private void updateInventoryForItem(SoldItem soldItem){
        ItemDTO soldItemDTO = soldItem.getItemDTO();
        int quantitySold = soldItem.getQuantitySold();

        InventoryItem inventoryItem = findInventoryItemByID(soldItemDTO.getItemID());
        if (inventoryItem != null) {
            reduceInventoryQuantity(inventoryItem, quantitySold);
        }
    }
    
    /**
     * Returns an InventoryItem with corresponding itemID. PackagePriv due to testing.
     * @param itemID        The searched itemID
     * @return              InventoryItem containing an ItemDTO with the
     *                      searched itemID, if no such InventoryItem exists
     *                      returns null.
     */
    InventoryItem findInventoryItemByID(int itemID) {
        for (InventoryItem item : inventory) {
            if (item.getItem().matchesItemID(itemID)) {
                return item;
            }
        }
        return null;
    }
    
    private void reduceInventoryQuantity(InventoryItem item, int quantitySold) {
        int newQuantity = item.getQuantity() - quantitySold;
        item.setQuantity(newQuantity);
    }
}
