public class Pants extends ClothingItem {
    private String fitType;

    public Pants(String name, double price, String size, int stock, String fitType) {
        super(name, price, size, stock);
        this.fitType = fitType;
    }

    public String getFitType() {
        return fitType;
    }

    public void setFitType(String fitType) {
        this.fitType = fitType;
    }

    @Override
    public String getCategory() {
        return "Pants";
    }

    @Override
    public String toString() {
        return super.toString() + ", Fit Type: " + fitType;
    }

    @Override
    public String getDetails() {
    return "Pants - Fit Type: " + fitType;
}
}
