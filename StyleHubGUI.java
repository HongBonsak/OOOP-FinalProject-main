import java.awt.*;
import java.util.List;
import javax.swing.*;

public class StyleHubGUI extends JFrame {
    private StoreManager store;
    private JTextArea displayArea;

    public StyleHubGUI() {
        store = new StoreManager();

        // sample data

        store.addItem(new Shirt("Oversize T-Shirt", 15.0, "M", 5, "Short Sleeve"));
        store.addItem(new Pants("Wide Leg Jeans", 25.0, "L", 3, "Loose Fit"));

        store.addItem(new Shirt("Boxy", 30.0, "M", 5, "Short Sleeve"));
        store.addItem(new Pants("Baggy Jeans", 25.0, "L", 3, "Loose Fit"));

        store.addItem(new Shirt("Suit", 15.0, "M", 5, "Short Sleeve"));
        store.addItem(new Pants("Jackets", 25.0, "L", 3, "Loose Fit"));

        setTitle("StyleHub - Buy and Sell Clothes System");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton showBtn = new JButton("Show Products");
        JButton buyBtn = new JButton("Buy Product");
        JButton sellBtn = new JButton("Sell Product");
        JButton sortBtn = new JButton("Sort by Price");
        JButton availableBtn = new JButton("Available Only");

        displayArea = new JTextArea(20, 55);
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(displayArea);

        panel.add(showBtn);
        panel.add(buyBtn);
        panel.add(sellBtn);
        panel.add(sortBtn);
        panel.add(availableBtn);

        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        showBtn.addActionListener(e -> displayArea.setText(store.showAllItems()));

        buyBtn.addActionListener(e -> {
            String productName = JOptionPane.showInputDialog(this, "Enter product name to buy:");
            if (productName != null && !productName.trim().isEmpty()) {
                Customer customer = new Customer("Customer");
                ClothingItem item = store.findItemByName(productName);

                if (item != null) {
                    String phoneNumber = JOptionPane.showInputDialog(this,
                            "Enter your phone number for Bakong payment:");

                    if (phoneNumber != null && !phoneNumber.trim().isEmpty()) {
                        PaymentMethod payment = new BakongPayment(phoneNumber);
                        String result = customer.buyItemWithPayment(item, payment);
                        displayArea.setText(result + "\n\n--- Updated Inventory ---\n" + store.showAllItems());
                    }
                } else {
                    displayArea.setText("❌ Product not found.");
                }
            }
        });

        sellBtn.addActionListener(e -> {
            try {
                String type = JOptionPane.showInputDialog(this, "Enter type (Shirt/Pants):");
                String name = JOptionPane.showInputDialog(this, "Enter product name:");
                double price = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter price:"));
                String size = JOptionPane.showInputDialog(this, "Enter size:");
                int stock = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter stock:"));

                Seller seller = new Seller("Student Seller");

                if (type.equalsIgnoreCase("Shirt")) {
                    String sleeveType = JOptionPane.showInputDialog(this, "Enter sleeve type:");
                    ClothingItem shirt = new Shirt(name, price, size, stock, sleeveType);
                    displayArea.setText(seller.addItem(store, shirt) + "\n\n" + store.showAllItems());
                } else if (type.equalsIgnoreCase("Pants")) {
                    String fitType = JOptionPane.showInputDialog(this, "Enter fit type:");
                    ClothingItem pants = new Pants(name, price, size, stock, fitType);
                    displayArea.setText(seller.addItem(store, pants) + "\n\n" + store.showAllItems());
                } else {
                    displayArea.setText("Invalid product type.");
                }

            } catch (NumberFormatException ex) {
                displayArea.setText("Invalid number input.");
            } catch (IllegalArgumentException ex) {
                displayArea.setText(ex.getMessage());
            }
        });

        sortBtn.addActionListener(e -> {
            List<ClothingItem> sortedItems = store.sortByPrice();
            StringBuilder sb = new StringBuilder("Products Sorted by Price:\n");
            for (ClothingItem item : sortedItems) {
                sb.append(item).append("\n");
            }
            displayArea.setText(sb.toString());
        });

        availableBtn.addActionListener(e -> {
            List<ClothingItem> availableItems = store.getAvailableItems();
            StringBuilder sb = new StringBuilder("Available Products:\n");
            for (ClothingItem item : availableItems) {
                sb.append(item).append("\n");
            }
            displayArea.setText(sb.toString());
        });

        JButton detailBtn = new JButton("Show Details");
        panel.add(detailBtn);

        detailBtn.addActionListener(e -> {
            displayArea.setText(store.showItemDetails());
        });
    }
}