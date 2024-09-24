package se.C9Lab1.decorators;

import se.C9Lab1.components.Discount;
import se.C9Lab1.Product;

//BaseDecorator wrapper
abstract class BaseDiscount implements Discount {
  protected Discount nextDiscount;

  BaseDiscount(Discount nextDiscount) {
    this.nextDiscount = nextDiscount;
  }

  @Override
  public double apply(Product product) {
    double discount = 0;
    if(isApplicable(product)){
      discount = calculateDiscount(product);
    }
      discount += nextDiscount.apply(product);
      return discount;
  }

  @Override
  public String getDescription(Product product) {
    return nextDiscount.getDescription(product);
  }

  protected abstract boolean isApplicable(Product product);
  protected abstract double calculateDiscount(Product product);
}
