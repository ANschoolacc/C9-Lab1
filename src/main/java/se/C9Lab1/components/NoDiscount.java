package se.C9Lab1.components;

import se.C9Lab1.entities.Product;

public class NoDiscount implements Discount{

  @Override
  public double apply(Product product) {
    return 0;
  }

  @Override
  public String getDescription(Product product) {
    return "";
  }
}
