/**
 * Transaction Receipt for purchases in StyleHub
 * Tracks purchase details including item, payment method, and timestamp
 */
public class Transaction {
    private String customerName;
    private ClothingItem item;
    private double amountPaid;
    private PaymentMethod paymentMethod;
    private String timestamp;
    private boolean success;

    public Transaction(String customerName, ClothingItem item, double amountPaid,
            PaymentMethod paymentMethod, boolean success) {
        this.customerName = customerName;
        this.item = item;
        this.amountPaid = amountPaid;
        this.paymentMethod = paymentMethod;
        this.success = success;
        this.timestamp = java.time.LocalDateTime.now().toString();
    }

    public String getReceipt() {
        StringBuilder receipt = new StringBuilder();
        receipt.append("\n========== STYLEHUB RECEIPT ==========\n");
        receipt.append("Customer: ").append(customerName).append("\n");
        receipt.append("Time: ").append(timestamp).append("\n");
        receipt.append("Item: ").append(item.getName()).append("\n");
        receipt.append("Price: $").append(String.format("%.2f", amountPaid)).append("\n");
        receipt.append("Payment: ").append(paymentMethod.getPaymentName()).append("\n");
        receipt.append("Status: ").append(success ? "✓ COMPLETED" : "✗ FAILED").append("\n");
        receipt.append("=====================================\n");
        return receipt.toString();
    }

    // Getters
    public String getCustomerName() {
        return customerName;
    }

    public ClothingItem getItem() {
        return item;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public boolean isSuccess() {
        return success;
    }
}
