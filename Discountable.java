
/**
 * Interface for items that can have discounts applied
 * Provides contract for discount calculation
 */
public interface Discountable {
    /**
     * Apply discount to an item
     * 
     * @param percent Discount percentage to apply
     * @return Discounted price
     */
    double applyDiscount(double percent);
}