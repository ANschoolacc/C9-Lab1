package se.C9Lab1.components;

import se.C9Lab1.entities.Product;
import se.C9Lab1.entities.ShoppingCart;

public interface Discount {

  double apply(Product product, ShoppingCart shoppingCart);
  String getDescription(Product product, ShoppingCart shoppingCart);
}
