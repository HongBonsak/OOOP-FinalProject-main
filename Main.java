import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Launch the GUI
        new StyleHubGUI().setVisible(true);

        // Uncomment below to run demo instead
        // demoPaymentAndDiscount();
    }

    /**
     * Demo method showing Bakong payment and discount functionality
     */
    public static void demoPaymentAndDiscount() {
        System.out.println("====== StyleHub - Payment & Discount Demo ======\n");

        // Create sample items
        Shirt shirt = new Shirt("Premium Polo", 35.0, "M", 5, "Short Sleeve");
        Pants pants = new Pants("Casual Chinos", 40.0, "L", 10, "Slim Fit");
        Jacket jacket = new Jacket("Winter Jacket", 50.0, "XL", 3, "Insulated");

        // Create customer
        Customer customer = new Customer("Rathana");

        // Demo 1: Bakong Payment
        System.out.println("=== Demo 1: Bakong Payment ===");
        PaymentMethod bakongPayment = new BakongPayment("085123456");
        String paymentResult = customer.buyItemWithPayment(shirt, bakongPayment);
        System.out.println(paymentResult + "\n");

        // Demo 2: Another Bakong Payment
        System.out.println("=== Demo 2: Bakong Payment (Pants) ===");
        PaymentMethod bakongPayment2 = new BakongPayment("086999888");
        String paymentResult2 = customer.buyItemWithPayment(pants, bakongPayment2);
        System.out.println(paymentResult2 + "\n");

        // Demo 3: Apply Discounts using Polymorphism
        System.out.println("=== Demo 3: Discount Functionality ===");
        Discountable[] discountables = { shirt, pants, jacket };
        double[] discountPercentages = { 10.0, 15.0, 20.0 };
        String[] itemNames = { "Shirt", "Pants", "Jacket" };

        for (int i = 0; i < discountables.length; i++) {
            double discountedPrice = discountables[i].applyDiscount(discountPercentages[i]);
            System.out.println(itemNames[i] + " - Original: $" + discountables[i].applyDiscount(0) +
                    ", Discount: " + discountPercentages[i] + "%" +
                    ", Final: $" + String.format("%.2f", discountedPrice));
        }
    }
}