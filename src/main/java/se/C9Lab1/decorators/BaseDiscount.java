package se.C9Lab1.decorators;

import se.C9Lab1.entities.ShoppingCart;
import se.C9Lab1.components.Discount;
import se.C9Lab1.entities.Product;

//BaseDecorator wrapper
abstract class BaseDiscount implements Discount {
  protected Discount nextDiscount;

  BaseDiscount(Discount nextDiscount) {
    this.nextDiscount = nextDiscount;
  }

  @Override
  public double apply(Product product, ShoppingCart shoppingCart) {
    double discount = 0;
      if(isApplicable(product, shoppingCart)){
        discount = calculateDiscount(product, shoppingCart);
      }
      discount += nextDiscount.apply(product, shoppingCart);
      return discount;
  }

  @Override
  public String getDescription(Product product, ShoppingCart shoppingCart) {
    String appliedDescription = "";
    if(isApplicable(product, shoppingCart)){
      appliedDescription += getOwnDescription();
    }
    String nextDescription = nextDiscount.getDescription(product, shoppingCart);

    if (!appliedDescription.isEmpty() && !nextDescription.isEmpty()) {
      appliedDescription += System.lineSeparator() + nextDescription;
    }else if(!nextDescription.isEmpty()){
      appliedDescription = nextDescription;
    }
    return appliedDescription;
  }

  protected abstract boolean isApplicable(Product product, ShoppingCart shoppingCart);
  protected abstract double calculateDiscount(Product product, ShoppingCart shoppingCart);
  protected abstract String getOwnDescription();
}
