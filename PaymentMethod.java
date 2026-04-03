/**
 * Interface for payment methods in StyleHub
 */
public interface PaymentMethod {
    boolean processPayment(double amount);

    String getPaymentName();

    String getPaymentDetails();
}
