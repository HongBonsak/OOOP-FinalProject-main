public class Jacket extends ClothingItem {
    private String insulationType;

    public Jacket(String name, double price, String size, int stock, String insulationType) {
        super(name, price, size, stock);
        this.insulationType = insulationType;
    }

    public String getInsulationType() {
        return insulationType;
    }

    public void setInsulationType(String insulationType) {
        this.insulationType = insulationType;
    }

    @Override
    public String getCategory() {
        return "Jackets";
    }

    @Override
    public String toString() {
        return super.toString() + ", Insulation Type: " + insulationType;
    }

    @Override
    public String getDetails() {
    return "Jackets - Insulation Type: " + insulationType;
}
}
