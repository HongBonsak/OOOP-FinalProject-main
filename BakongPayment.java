/**
 * Bakong Payment - Cambodia's National Instant Payment System
 */
public class BakongPayment implements PaymentMethod {
    private String phoneNumber;

    public BakongPayment(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean processPayment(double amount) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            return false;
        }
        return amount > 0;
    }

    @Override
    public String getPaymentName() {
        return "Bakong";
    }

    @Override
    public String getPaymentDetails() {
        return "Phone: " + phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
