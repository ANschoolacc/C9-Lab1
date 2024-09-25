package se.C9Lab1.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
  private final List<Product> products = new ArrayList<>();
  private String customerName;
  private int quantity = 0;
  private LocalDate dateOfPurchase;
  private double total = 0;

  public ShoppingCart(String customerName, LocalDate date) {
    setCustomerName(customerName);
    setDateOfPurchase(date);
  }

  public void addProduct(Product product) {
    this.products.add(product);
    this.quantity += product.quantity();
    this.total += product.price();
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

  public LocalDate getDateOfPurchase() {
    return dateOfPurchase;
  }

  public List<Product> getProducts() {
    return products;
  }

  public double getTotal() {
    return total;
  }
}
