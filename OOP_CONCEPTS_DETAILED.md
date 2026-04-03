# StyleHub Project - 4 OOP Concepts Detailed Breakdown

---

## 📋 OVERALL STRUCTURE

```
User System                 Item System              Payment System
├── User (Base)            ├── ClothingItem (Abstract)   ├── PaymentMethod (Interface)
├── Customer (Child)       ├── Shirt (Child)             └── BakongPayment (Implementation)
└── Seller (Child)         ├── Pants (Child)          
                          └── Jacket (Child)
                          
Interfaces/Contracts        Utilities
├── Discountable           └── StoreManager
├── PaymentMethod          └── Transaction
```

---

## 1️⃣ **ENCAPSULATION** - Hide Data, Control Access

### **What it is:**
Bundling data (variables) and methods together, hiding internal details from outside with private/public access.

### **Why use it:**
- Protects data from invalid modifications
- Allows validation before setting values
- Can change internal implementation without affecting external code

---

### **File: User.java** ✓ ENCAPSULATION

```java
public class User {
    private String name;           // ← HIDDEN (private)
    
    public User(String name) {
        this.name = name;
    }
    
    public String getName() {      // ← CONTROLLED ACCESS (getter)
        return name;
    }
    
    public void setName(String name) {  // ← CONTROLLED MODIFICATION (setter)
        this.name = name;
    }
}
```

**Why Encapsulation here:**
- `name` is PRIVATE → can't access directly from outside
- Must use `getName()` and `setName()` → controlled access
- Future: Can add validation like `if (name.isEmpty()) return false`

---

### **File: ClothingItem.java** ✓ ENCAPSULATION (with Validation)

```java
public abstract class ClothingItem implements Discountable {
    private String name;      // ← HIDDEN
    private double price;     // ← HIDDEN
    private int stock;        // ← HIDDEN
    
    // Constructor - initialize through setter for validation
    public ClothingItem(String name, double price, String size, int stock) {
        this.name = name;
        setPrice(price);      // ← Uses setter for validation
        this.size = size;
        setStock(stock);      // ← Uses setter for validation
    }
    
    // Price Setter with VALIDATION
    public void setPrice(double price) {
        if (price < 0) {      // ← LOGIC to prevent invalid data
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        this.price = price;
    }
    
    // Stock Setter with VALIDATION
    public void setStock(int stock) {
        if (stock < 0) {      // ← LOGIC to prevent invalid data
            throw new IllegalArgumentException("Stock cannot be negative.");
        }
        this.stock = stock;
    }
    
    public double getPrice() { return price; }
    public int getStock() { return stock; }
}
```

**Why Encapsulation here:**
- All fields are PRIVATE → can't be changed directly
- `setPrice()` validates → prevents negative prices ✓
- `setStock()` validates → prevents negative stock ✓
- Keep data in CONSISTENT state

---

### **File: BakongPayment.java** ✓ ENCAPSULATION

```java
public class BakongPayment implements PaymentMethod {
    private String phoneNumber;   // ← HIDDEN
    
    public String getPhoneNumber() {      // ← GETTER
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {  // ← SETTER
        this.phoneNumber = phoneNumber;
    }
}
```

**Why Encapsulation here:**
- `phoneNumber` is PRIVATE
- Must use getter/setter methods
- Can add validation in future

---

### **File: StoreManager.java** ✓ ENCAPSULATION

```java
public class StoreManager {
    private ArrayList<ClothingItem> items = new ArrayList<>();  // ← HIDDEN
    
    public void addItem(ClothingItem item) {
        items.add(item);  // ← CONTROLLED ACCESS
    }
    
    public List<ClothingItem> getItems() {
        return items;     // ← CONTROLLED ACCESS
    }
}
```

**Why Encapsulation here:**
- Items list is PRIVATE
- Can only add items through `addItem()` method
- Can add filtering/validation logic

---

## 2️⃣ **INHERITANCE** - Reuse Code, Create Hierarchy

### **What it is:**
Child class inherits properties and methods from parent class using `extends`.

### **Why use it:**
- Reuse common code (DRY principle)
- Create logical hierarchy
- Specialized classes override parent methods

---

### **File: User.java (Parent)** ✓ INHERITANCE

```java
public class User {          // ← PARENT CLASS
    private String name;
    
    public User(String name) {
        this.name = name;
    }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
```

---

### **File: Customer.java (Child)** ✓ INHERITANCE

```java
public class Customer extends User {         // ← INHERITS FROM User
    
    public Customer(String name) {
        super(name);                          // ← CALLS PARENT CONSTRUCTOR
    }
    
    public String buyItem(ClothingItem item) {         // ← NEW METHOD
        if (item.getStock() > 0) {
            item.setStock(item.getStock() - 1);
            return getName() + " bought: " + item.getName();  // ← USES PARENT METHOD
        }
    }
}
```

**Why Inheritance here:**
- `Customer` is a special type of `User`
- Reuses `getName()` from parent
- Adds new method `buyItem()` specific to customer
- `super(name)` passes name to parent constructor

---

### **File: Seller.java (Child)** ✓ INHERITANCE

```java
public class Seller extends User {           // ← INHERITS FROM User
    
    public Seller(String name) {
        super(name);                         // ← CALLS PARENT CONSTRUCTOR
    }
    
    public String addItem(StoreManager store, ClothingItem item) {
        store.addItem(item);
        return getName() + " added: " + item.getName();  // ← USES PARENT METHOD
    }
}
```

**Why Inheritance here:**
- `Seller` is a special type of `User`
- Reuses `getName()` from parent
- Adds new method `addItem()` specific to seller

---

### **File: ClothingItem.java (Parent)** ✓ INHERITANCE

```java
public abstract class ClothingItem implements Discountable {  // ← PARENT CLASS
    private String name;
    private double price;
    private String size;
    private int stock;
    
    public ClothingItem(String name, double price, String size, int stock) {
        this.name = name;
        setPrice(price);
        this.size = size;
        setStock(stock);
    }
    
    // Common methods
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    
    // Abstract method - must be implemented by children
    public abstract String getCategory();
    
    // Common implementation
    @Override
    public double applyDiscount(double percent) {
        return price - (price * percent / 100);
    }
}
```

---

### **File: Shirt.java (Child)** ✓ INHERITANCE

```java
public class Shirt extends ClothingItem {              // ← INHERITS FROM ClothingItem
    private String sleeveType;                         // ← NEW PROPERTY
    
    public Shirt(String name, double price, String size, int stock, String sleeveType) {
        super(name, price, size, stock);               // ← CALLS PARENT CONSTRUCTOR
        this.sleeveType = sleeveType;
    }
    
    public String getSleeveType() { return sleeveType; }
    
    // INHERITED METHODS (from ClothingItem):
    // - getName(), getPrice(), getStock()
    // - applyDiscount() - works on price
    
    // OVERRIDE abstract method
    @Override
    public String getCategory() {
        return "Shirt";
    }
    
    // OVERRIDE to add more details
    @Override
    public String toString() {
        return super.toString() + ", Sleeve Type: " + sleeveType;
    }
}
```

**Why Inheritance here:**
- `Shirt` IS-A `ClothingItem`
- Reuses: name, price, size, stock management
- Reuses: `applyDiscount()` method
- Adds: sleeveType property
- Overrides: `getCategory()`, `toString()`

---

### **File: Pants.java (Child)** ✓ INHERITANCE

```java
public class Pants extends ClothingItem {              // ← INHERITS FROM ClothingItem
    private String fitType;                            // ← NEW PROPERTY
    
    public Pants(String name, double price, String size, int stock, String fitType) {
        super(name, price, size, stock);               // ← CALLS PARENT CONSTRUCTOR
        this.fitType = fitType;
    }
    
    @Override
    public String getCategory() {
        return "Pants";                                // ← DIFFERENT FROM Shirt
    }
    
    @Override
    public String getDetails() {
        return "Pants - Fit Type: " + fitType;
    }
}
```

**Why Inheritance here:**
- `Pants` IS-A `ClothingItem`
- Reuses all ClothingItem functionality
- Adds: fitType property
- Each child has different `getCategory()` return value

---

### **File: Jacket.java (Child)** ✓ INHERITANCE

```java
public class Jacket extends ClothingItem {             // ← INHERITS FROM ClothingItem
    private String insulationType;                     // ← NEW PROPERTY
    
    public Jacket(String name, double price, String size, int stock, String insulationType) {
        super(name, price, size, stock);               // ← CALLS PARENT CONSTRUCTOR
        this.insulationType = insulationType;
    }
    
    @Override
    public String getCategory() {
        return "Jackets";                              // ← DIFFERENT FROM Shirt/Pants
    }
}
```

**Why Inheritance here:**
- `Jacket` IS-A `ClothingItem`
- Reuses all ClothingItem functionality
- Adds: insulationType property

---

## 3️⃣ **POLYMORPHISM** - Same Name, Different Behavior

### **What it is:**
One interface, multiple implementations. Methods with same name behave differently based on object type.

### **Why use it:**
- Write code once, works with different types
- Easy to extend with new types
- Cleaner, more flexible code

---

### **Type 1: METHOD OVERRIDING (Runtime Polymorphism)**

### **File: ClothingItem.java (Abstract Method)** ✓ POLYMORPHISM

```java
public abstract class ClothingItem implements Discountable {
    
    public abstract String getCategory();     // ← ABSTRACT (no implementation)
    
    @Override
    public double applyDiscount(double percent) {
        return price - (price * percent / 100);  // ← CONCRETE (has implementation)
    }
}
```

---

### **File: Shirt.java** ✓ POLYMORPHISM

```java
public class Shirt extends ClothingItem {
    
    @Override
    public String getCategory() {
        return "Shirt";                        // ← DIFFERENT implementation
    }
    
    @Override
    public String getDetails() {
        return "Shirt - Sleeve Type: " + sleeveType;
    }
}
```

---

### **File: Pants.java** ✓ POLYMORPHISM

```java
public class Pants extends ClothingItem {
    
    @Override
    public String getCategory() {
        return "Pants";                        // ← DIFFERENT implementation
    }
    
    @Override
    public String getDetails() {
        return "Pants - Fit Type: " + fitType;
    }
}
```

---

### **File: Jacket.java** ✓ POLYMORPHISM

```java
public class Jacket extends ClothingItem {
    
    @Override
    public String getCategory() {
        return "Jackets";                      // ← DIFFERENT implementation
    }
}
```

**Why Polymorphism here:**
- Same method name `getCategory()` 
- Different return values for each class
- Runtime system calls correct method based on object type

```
Shirt shirt = new Shirt(...);
Pants pants = new Pants(...);
Jacket jacket = new Jacket(...);

shirt.getCategory();    → "Shirt"
pants.getCategory();    → "Pants"
jacket.getCategory();   → "Jackets"    ← Same call, different results!
```

---

### **Type 2: INTERFACE-BASED POLYMORPHISM**

### **File: Discountable.java (Interface)** ✓ POLYMORPHISM

```java
public interface Discountable {
    double applyDiscount(double percent);     // ← CONTRACT (must implement)
}
```

---

### **File: ClothingItem.java (Implements Interface)** ✓ POLYMORPHISM

```java
public abstract class ClothingItem implements Discountable {
    
    @Override
    public double applyDiscount(double percent) {
        return price - (price * percent / 100);
    }
}
```

**Why Polymorphism here:**
- All clothing items follow contract `Discountable`
- They all have `applyDiscount()` method
- Can treat them as `Discountable` objects

```java
Discountable[] items = {
    new Shirt(...),      // ← Treats as Discountable
    new Pants(...),      // ← Treats as Discountable
    new Jacket(...)      // ← Treats as Discountable
};

for (Discountable item : items) {
    item.applyDiscount(10);  // ← Works for all!
}
```

---

### **Type 3: PAYMENT METHOD POLYMORPHISM**

### **File: PaymentMethod.java (Interface)** ✓ POLYMORPHISM

```java
public interface PaymentMethod {
    boolean processPayment(double amount);
    String getPaymentName();
    String getPaymentDetails();
}
```

---

### **File: BakongPayment.java** ✓ POLYMORPHISM

```java
public class BakongPayment implements PaymentMethod {
    
    @Override
    public boolean processPayment(double amount) {
        return amount > 0;                     // ← BAKONG WAY
    }
    
    @Override
    public String getPaymentName() {
        return "Bakong";                       // ← BAKONG NAME
    }
}
```

**Why Polymorphism here:**
- Can add more payment methods (Wing, ABA) later
- Each implements PaymentMethod differently
- Customer code doesn't change:

```java
public String buyItemWithPayment(ClothingItem item, PaymentMethod paymentMethod) {
    if (paymentMethod.processPayment(item.getPrice())) {  // ← Works with ANY PaymentMethod!
        // ...success
    }
}
```

---

### **File: StoreManager.java** ✓ POLYMORPHISM

```java
public String showAllItems() {
    StringBuilder sb = new StringBuilder();
    for (ClothingItem item : items) {                    // ← Polymorphic loop
        sb.append(item).append("\n");                    // ← item could be Shirt, Pants, or Jacket
    }
    return sb.toString();
}
```

**Why Polymorphism here:**
- `items` is `ArrayList<ClothingItem>`
- Can contain: Shirt, Pants, Jacket objects
- When we call `item.toString()` or `item.getCategory()`:
  - If item is Shirt → Shirt's version runs
  - If item is Pants → Pants's version runs
  - If item is Jacket → Jacket's version runs

---

### **File: Main.java** ✓ POLYMORPHISM

```java
// Demo 3: Apply Discounts using Polymorphism
Discountable[] discountables = { shirt, pants, jacket };

for (int i = 0; i < discountables.length; i++) {
    double discountedPrice = discountables[i].applyDiscount(discountPercentages[i]);
    // ↑ ALL implement Discountable
    // ↑ All have applyDiscount()
    // ↑ Same calculation applies to all!
}
```

---

## 4️⃣ **ABSTRACTION** - Show Only Essentials, Hide Complexity

### **What it is:**
Simplifying complex reality by hiding unnecessary details, showing only relevant features.

### **Why use it:**
- Reduces complexity
- Users don't need to know HOW it works
- Focus on WHAT it does

---

### **File: ClothingItem.java** ✓ ABSTRACTION

```java
public abstract class ClothingItem implements Discountable {  // ← ABSTRACT CLASS
    
    // Hide implementation details (private)
    private String name;
    private double price;
    private String size;
    private int stock;
    
    // Force subclasses to implement this
    public abstract String getCategory();    // ← ABSTRACT METHOD
    
    // Provide concrete implementation
    public String getDetails() {
        return "Basic Clothing Item";
    }
    
    @Override
    public double applyDiscount(double percent) {
        return price - (price * percent / 100);
    }
}
```

**Why Abstraction here:**
- User doesn't see `price`, `stock` directly ✓
- User doesn't care HOW discount is calculated ✓
- Just calls `applyDiscount()` and gets result ✓
- Child classes must implement `getCategory()` ✓

**Example:**
```java
Shirt shirt = new Shirt("Polo", 35.0, "M", 5, "Short");

// User sees simple interface:
shirt.getPrice();              // → Easy to use
shirt.applyDiscount(10);       // → Simple method call
shirt.getCategory();           // → Gets category

// User doesn't need to know:
// - HOW price validation works
// - HOW discount calculation happens
// - HOW stock changes internally
```

---

### **File: PaymentMethod.java (Interface)** ✓ ABSTRACTION

```java
public interface PaymentMethod {
    boolean processPayment(double amount);      // ← WHAT IT DOES
    String getPaymentName();
    String getPaymentDetails();
    // ↑ Hide HOW each payment works
}
```

**Why Abstraction here:**
- Interface HIDES implementation details
- Bakong works differently than Wing
- But interface lets us TREAT them the same
- User just calls `processPayment()` - doesn't care HOW

---

### **File: BakongPayment.java** ✓ ABSTRACTION

```java
public class BakongPayment implements PaymentMethod {
    private String phoneNumber;    // ← HIDDEN
    
    @Override
    public boolean processPayment(double amount) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            return false;
        }
        return amount > 0;         // ← HOW it validates is HIDDEN
    }
    
    @Override
    public String getPaymentName() {
        return "Bakong";
    }
}
```

**Why Abstraction here:**
- `phoneNumber` is PRIVATE ✓
- `processPayment()` logic is HIDDEN ✓
- Users just call it - don't see implementation ✓

---

### **File: StoreManager.java** ✓ ABSTRACTION

```java
public class StoreManager {
    private ArrayList<ClothingItem> items = new ArrayList<>();  // ← HIDDEN
    
    // Simple interface:
    public void addItem(ClothingItem item) {
        items.add(item);
    }
    
    public String showAllItems() {
        // Complex filtering and formatting hidden
        // ...
    }
}
```

**Why Abstraction here:**
- `ArrayList` implementation detail is HIDDEN ✓
- User just calls `addItem()` ✓
- User doesn't care if we use ArrayList, LinkedList, etc. ✓

---

### **File: Customer.java** ✓ ABSTRACTION

```java
public String buyItemWithPayment(ClothingItem item, PaymentMethod paymentMethod) {
    if (item.getStock() <= 0) {
        return item.getName() + " is out of stock.";
    }
    
    if (paymentMethod.processPayment(item.getPrice())) {  // ← Doesn't care HOW payment works
        // ...
    }
}
```

**Why Abstraction here:**
- HOW payment processing works? HIDDEN ✓
- Just call `processPayment()` ✓
- Could swap to different payment method - code stays the same ✓

---

### **File: Main.java** ✓ ABSTRACTION

```java
public static void main(String[] args) {
    new StyleHubGUI().setVisible(true);    // ← Just one line!
    
    // All the GUI complexity is HIDDEN in StyleHubGUI
    // - Event handling
    // - Button clicks
    // - Display management
    // All abstracted away!
}
```

**Why Abstraction here:**
- StyleHubGUI complexity is HIDDEN ✓
- Main just creates it and shows it ✓
- Main doesn't need to know GUI implementation ✓

---

## 📊 **SUMMARY TABLE**

| Concept | File | Use | Why |
|---------|------|-----|-----|
| **ENCAPSULATION** | User.java | private name + getter/setter | Control access |
| **ENCAPSULATION** | ClothingItem.java | private fields + validation | Prevent invalid data |
| **ENCAPSULATION** | BakongPayment.java | private phoneNumber | Hide payment details |
| **ENCAPSULATION** | StoreManager.java | private ArrayList | Control inventory access |
| **INHERITANCE** | Customer extends User | `extends User` + `super()` | Reuse user properties |
| **INHERITANCE** | Seller extends User | `extends User` + `super()` | Reuse user properties |
| **INHERITANCE** | Shirt extends ClothingItem | `extends ClothingItem` + `super()` | Reuse item properties |
| **INHERITANCE** | Pants extends ClothingItem | `extends ClothingItem` + `super()` | Reuse item properties |
| **INHERITANCE** | Jacket extends ClothingItem | `extends ClothingItem` + `super()` | Reuse item properties |
| **POLYMORPHISM** | Shirt.getCategory() override | returns "Shirt" | Different behavior per type |
| **POLYMORPHISM** | Pants.getCategory() override | returns "Pants" | Different behavior per type |
| **POLYMORPHISM** | Jacket.getCategory() override | returns "Jackets" | Different behavior per type |
| **POLYMORPHISM** | BakongPayment.processPayment() | Implements interface | Works with different payments |
| **POLYMORPHISM** | StoreManager.showAllItems() | Loops through ClothingItem[] | Works with any clothing type |
| **POLYMORPHISM** | Main.discountables array | Discountable[] | All items work in same loop |
| **ABSTRACTION** | ClothingItem abstract class | abstract getCategory() | Hide implementation |
| **ABSTRACTION** | PaymentMethod interface | Interface definition | Hide payment complexity |
| **ABSTRACTION** | BakongPayment logic | Hidden processPayment() | Hide how validation works |
| **ABSTRACTION** | StoreManager | ArrayList hidden | Hide storage implementation |

---

## 🎯 **REAL-WORLD EXAMPLE: Using All 4 Concepts**

```java
// ABSTRACTION: User doesn't see complexity
Customer customer = new Customer("Rathana");         // Simple creation

// INHERITANCE: Customer is a User, has all user properties
System.out.println(customer.getName());             // From User class

// ENCAPSULATION: Private fields, accessed through methods
Shirt shirt = new Shirt("Polo", 35.0, "M", 5, "Short");
shirt.setPrice(30.0);                               // Through setter (validates)
System.out.println(shirt.getPrice());               // Through getter

// POLYMORPHISM: Works with any ClothingItem
ClothingItem item = shirt;                          // Can be Shirt, Pants, Jacket
System.out.println(item.getCategory());             // Calls Shirt's version → "Shirt"

// POLYMORPHISM + ABSTRACTION: PaymentMethod interface
PaymentMethod payment = new BakongPayment("085123456");
customer.buyItemWithPayment(shirt, payment);        // Doesn't care it's Bakong

// All 4 concepts work together!
```

---

## ✅ **YOUR PROJECT DEMONSTRATES ALL 4 CONCEPTS PERFECTLY!**

Each concept solves a real problem:
1. **ENCAPSULATION** → Data integrity (price/stock validation)
2. **INHERITANCE** → Code reuse (User, ClothingItem hierarchies)
3. **POLYMORPHISM** → Flexibility (different getCategory() per type)
4. **ABSTRACTION** → Simplicity (users don't see complexity)

