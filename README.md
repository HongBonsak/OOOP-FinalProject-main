# StyleHub - Clothing Management System

## Project Overview
StyleHub is a complete OOP-based clothing e-commerce system built with Java, featuring a GUI, payment integration, and inventory management.

## Features

### 1. **Clothing Management**
- **Shirt**: T-shirts and formal shirts with sleeve types (Short, Long)
- **Pants**: Jeans and casual pants with fit types (Slim, Loose, etc.)
- **Jacket**: Winter jackets with insulation types
- All extend `ClothingItem` abstract class with:
  - Name, Price, Size, Stock management
  - Price and stock validation
  - Category and details display

### 2. **User System**
- **Customer**: Can buy items using Bakong payment
- **Seller**: Can add new items to the store
- Both extend `User` base class

### 3. **Store Management**
- `StoreManager` manages all inventory
- Features:
  - Add items (overloaded for Shirt, Pants, Jacket)
  - Show all items with details
  - Filter available items (stock > 0)
  - Sort by price
  - Find items by name

### 4. **Payment System (Bakong)**
- **PaymentMethod Interface**: Contract for payment processors
- **BakongPayment**: Cambodia's national payment system
  - Takes phone number as identifier
  - Validates payment amount
  - Returns payment details

### 5. **Discount Functionality**
- `Discountable` interface for applying discounts
- Uses polymorphism - all clothing items implement Discountable
- `applyDiscount(percent)` calculates discounted price

### 6. **GUI (StyleHubGUI)**
Buttons:
- **Show Products**: Display all items in inventory
- **Buy Product**: Purchase with Bakong payment
  - Enter product name
  - Provide phone number for Bakong
  - Payment processed and inventory updated
- **Sell Product**: Add new Shirt or Pants to store
- **Sort by Price**: View items sorted by price
- **Available Only**: Show only items in stock
- **Show Details**: Display item details with categories

### 7. **Transaction Tracking**
- `Transaction` class records:
  - Customer name and timestamp
  - Item purchased and amount paid
  - Payment method used
  - Success/failure status
  - Generates receipt

## How to Run

### GUI Mode (Default)
```
java Main
```
- Opens StyleHub customer interface
- Click buttons to browse, buy, or sell items

### Demo Mode
Uncomment in `Main.java`:
```java
// demoPaymentAndDiscount();
```
Shows:
- Bakong payment examples
- Discount calculations with polymorphism

## OOP Concepts Demonstrated

1. **Inheritance**
   - ClothingItem (abstract) ← Shirt, Pants, Jacket
   - User (abstract) ← Customer, Seller

2. **Polymorphism**
   - Discountable interface (multiple implementations)
   - getCategory() and getDetails() (abstract methods)

3. **Encapsulation**
   - Private fields with getters/setters
   - Stock and price validation

4. **Interfaces**
   - PaymentMethod interface for extensibility
   - Discountable interface for discounts

5. **Composition**
   - Customer has-a PaymentMethod
   - StoreManager has-a List<ClothingItem>

## Sample Data (Pre-loaded)
Items come pre-loaded in the GUI:
- Oversize T-Shirt ($15, M, 5 units)
- Wide Leg Jeans ($25, L, 3 units)
- Boxy Shirt ($30, M, 5 units)
- And more...

## Payment Example
```java
Customer customer = new Customer("Rathana");
PaymentMethod bakong = new BakongPayment("085123456");
String result = customer.buyItemWithPayment(shirt, bakong);
// Output: Rathana successfully bought: Premium Polo for $35.00
//         Payment: Bakong
//         Phone: 085123456
//         Status: ✓ SUCCESS
```

## Future Enhancements
- Additional payment methods (Wing, ABA)
- User authentication
- Order history
- Digital receipts via email
- Inventory reports
- Seasonal discounts

## Project Structure
```
Main.java                 (Entry point)
StyleHubGUI.java         (GUI interface)
User.java                (Base user class)
├── Customer.java        (Buyer)
└── Seller.java          (Seller)
ClothingItem.java        (Abstract base)
├── Shirt.java
├── Pants.java
└── Jacket.java
StoreManager.java        (Inventory management)
Discountable.java        (Discount interface)
PaymentMethod.java       (Payment interface)
├── BakongPayment.java
Transaction.java         (Receipt tracking)
```

## Author
Created as a comprehensive OOP project for learning Java concepts.

---
**StyleHub** - Making online shopping simple and secure!
