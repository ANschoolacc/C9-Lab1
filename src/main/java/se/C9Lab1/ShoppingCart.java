package se.C9Lab1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
  private final List<Product> products = new ArrayList<>();
  private String customerName;
  private int quantity;
  private LocalDate dateOfPurchase;

  public ShoppingCart(String customerName, LocalDate date) {
    setCustomerName(customerName);
    setDateOfPurchase(date);
  }

  public void addProduct(Product products) {
    this.products.add(products);
    quantity++;
  }

  private void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  private void setDateOfPurchase(LocalDate date) {
    dateOfPurchase = date;
  }

  public String getCustomerName() {
    return customerName;
  }
  public int getQuantity() {
    return quantity;
  }
  public LocalDate getDateOfPurchase() {
    return dateOfPurchase;
  }
  public List<Product> getProducts() {
    return products;
  }
}
