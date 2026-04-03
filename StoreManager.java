import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StoreManager {
    //has a relationship with ClothingItem, as it manages a collection of clothing items in the store
    //ArrayList: because it allows us to easily add, remove, and manage a dynamic list of clothing items without worrying about the underlying array size
    private ArrayList<ClothingItem> items = new ArrayList<>();

    public void addItem(ClothingItem item) {
        items.add(item);
    }

    // Overloading
    public void addItem(String name, double price, String size, int stock, String sleeveType) {
        items.add(new Shirt(name, price, size, stock, sleeveType));
    }

    public void addItem(String name, double price, String size, int stock, String fitType, boolean isPants) {
        if (isPants) {
            items.add(new Pants(name, price, size, stock, fitType));
        }
    }

    public List<ClothingItem> getItems() {
        return items;
    }

    public String showAllItems() {
        if (items.isEmpty()) {
            return "No products available.";
        }

        StringBuilder sb = new StringBuilder();
        for (ClothingItem item : items) {
            sb.append(item).append("\n");
        }
        return sb.toString();
    }

    public List<ClothingItem> getAvailableItems() {
        return items.stream()
                .filter(item -> item.getStock() > 0)   // lambda
                .collect(Collectors.toList());
    }

    public List<ClothingItem> sortByPrice() {
        return items.stream()
                .sorted(Comparator.comparingDouble(ClothingItem::getPrice)) // lambda + method reference
                .collect(Collectors.toList());
    }

    public ClothingItem findItemByName(String name) {
        for (ClothingItem item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }
 //polymorphism to show details of each item based on its type
 // This method demonstrates polymorphism by calling the getDetails() method on each ClothingItem, which will return different details based on whether it
 // is a shirt or pant , without needing to know the type 
    public String showItemDetails() {
    StringBuilder sb = new StringBuilder();

    for (ClothingItem item : items) {
        // Polymorphism in action
        sb.append(item.getDetails()).append("\n");
    }
    return sb.toString();
}
}