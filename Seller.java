public class Seller extends User {

    public Seller(String name) {
        super(name);
    }

    public String addItem(StoreManager store, ClothingItem item) {
        store.addItem(item);
        return getName() + " added: " + item.getName();
    }
}