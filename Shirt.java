public class Shirt extends ClothingItem {
    private String sleeveType;
    

    public Shirt(String name, double price, String size, int stock, String sleeveType) {
        super(name, price, size, stock);
        this.sleeveType = sleeveType;
    }

    public String getSleeveType() {
        return sleeveType;
    }

    public void setSleeveType(String sleeveType) {
        this.sleeveType = sleeveType;
    }

    @Override
    public String getCategory() {
        return "Shirt";
    }

    @Override
    public String toString() {
        return super.toString() + ", Sleeve Type: " + sleeveType;
    }
    @Override
    public String getDetails() {
    return "Shirt - Sleeve Type: " + sleeveType;
}
}