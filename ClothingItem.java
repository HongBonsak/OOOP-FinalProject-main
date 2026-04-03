public abstract class ClothingItem implements Discountable {
    private String name;
    private double price;
    private String size;
    private int stock; //Encapsulating stock variable
 
    
    public ClothingItem(String name, double price, String size, int stock) {
        this.name = name;
        setPrice(price);
        this.size = size;
        setStock(stock);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }
 //validation for price and stock to prevent negative values
    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("Stock cannot be negative.");
        }
        this.stock = stock;
    }

    public abstract String getCategory() ;
    
    public String getDetails() {
    return "Basic Clothing Item";
}

    @Override
    public double applyDiscount(double percent) {
        return price - (price * percent / 100);
    }
  
    @Override
    public String toString() {
        return "Name: " + name +
               ", Category: " + getCategory() +
               ", Price: $" + price +
               ", Size: " + size +
               ", Stock: " + stock;
    }
}