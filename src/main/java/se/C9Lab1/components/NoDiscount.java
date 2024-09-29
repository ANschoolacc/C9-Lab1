package se.C9Lab1.components;

import se.C9Lab1.entities.Product;
import se.C9Lab1.entities.ShoppingCart;

import java.util.List;

public class NoDiscount implements Discount{

  @Override
  public List<Double> apply(ShoppingCart shoppingCart) {
    return List.of();
  }

  @Override
  public List<String> getDescription(ShoppingCart shoppingCart) {
    return List.of();
  }
}
