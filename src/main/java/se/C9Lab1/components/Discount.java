package se.C9Lab1.components;

import se.C9Lab1.Product;

public interface Discount {

  double apply(Product product);
  String getDescription(Product product);
}
