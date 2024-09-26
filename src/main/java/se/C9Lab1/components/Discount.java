package se.C9Lab1.components;

import se.C9Lab1.entities.Product;
import se.C9Lab1.entities.ShoppingCart;

import java.util.List;

public interface Discount {

  List<Double> apply(ShoppingCart shoppingCart);
  List<String> getDescription(ShoppingCart shoppingCart);
}
