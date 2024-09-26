package se.C9Lab1.decorators;

import se.C9Lab1.components.Discount;
import se.C9Lab1.entities.Product;

public class MockedConcreteComponent implements Discount {

  @Override
  public double apply(Product product) {
    return 0;
  }

  @Override
  public String getDescription(Product product) {
    return "";
  }
}
