package se.C9Lab1.decorators;

import se.C9Lab1.components.Discount;
import se.C9Lab1.entities.Product;

public class MockedConcreteDecorator extends BaseDiscount {
  private final boolean applicable;
  private final String ownDescription;
  private final double discount;

  public MockedConcreteDecorator(Discount nextDiscount, boolean applicable, String ownDescription, double discount) {
    super(nextDiscount);
    this.applicable = applicable;
    this.ownDescription = ownDescription;
    this.discount = discount;
  }
  @Override
  protected boolean isApplicable(Product product) {
    return applicable;
  }

  @Override
  protected double calculateDiscount(Product product) {
    return discount;
  }

  @Override
  protected String getOwnDescription() {
    return ownDescription;
  }
}
