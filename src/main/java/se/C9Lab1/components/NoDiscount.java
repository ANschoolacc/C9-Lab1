package se.C9Lab1.components;

import se.C9Lab1.entities.Product;
import se.C9Lab1.entities.ShoppingCart;

public class NoDiscount implements Discount{

  @Override
  public double apply(Product product, ShoppingCart shoppingCart) {
    return 0;
  }

  @Override
  public String getDescription(Product product, ShoppingCart shoppingCart) {
    return "";
  }
}
