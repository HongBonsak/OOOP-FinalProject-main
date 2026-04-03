public class Customer extends User {

    public Customer(String name) {
        super(name);
    }

    public String buyItem(ClothingItem item) {
        if (item.getStock() > 0) {
            item.setStock(item.getStock() - 1);
            return getName() + " bought: " + item.getName();
        } else {
            return item.getName() + " is out of stock.";
        }
    }

    public String buyItemWithPayment(ClothingItem item, PaymentMethod paymentMethod) {
        if (item.getStock() <= 0) {
            return item.getName() + " is out of stock.";
        }

        if (paymentMethod.processPayment(item.getPrice())) {
            item.setStock(item.getStock() - 1);
            return getName() + " successfully bought: " + item.getName() +
                    " for $" + String.format("%.2f", item.getPrice()) +
                    "\nPayment: " + paymentMethod.getPaymentName() +
                    "\n" + paymentMethod.getPaymentDetails() +
                    "\nStatus: ✓ SUCCESS";
        } else {
            return "Payment failed! Purchase cancelled.";
        }
    }
}