package se.C9Lab1.decorators;

import se.C9Lab1.components.Discount;
import se.C9Lab1.entities.Product;

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
    String appliedDescription = "";
    if(isApplicable(product)){
      appliedDescription += getOwnDescription();
    }

    String nextDescription = nextDiscount.getDescription(product);

    if (!appliedDescription.isEmpty() && !nextDescription.isEmpty()) {
      appliedDescription += System.lineSeparator() + nextDescription;
    }else if(!nextDescription.isEmpty()){
      appliedDescription = nextDescription;
    }
    return appliedDescription;
  }

  protected abstract boolean isApplicable(Product product);
  protected abstract double calculateDiscount(Product product);
  protected abstract String getOwnDescription();
}
