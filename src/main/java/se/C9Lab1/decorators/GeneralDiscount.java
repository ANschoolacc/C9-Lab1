package se.C9Lab1.decorators;

import se.C9Lab1.components.Discount;
import se.C9Lab1.entities.Product;
import se.C9Lab1.entities.ShoppingCart;

public class GeneralDiscount implements Discount {
  Discount nextDiscount;
  String description;
  IsApplicable isApplicable;
  CalculateDiscount calculation;

  public GeneralDiscount(String description , IsApplicable isApplicable, CalculateDiscount calculateDiscount, Discount nextDiscount) {
    this.nextDiscount = nextDiscount;
    this.description = description;
    this.isApplicable = isApplicable;
    this.calculation = calculateDiscount;
  }

  @Override
  public double apply(Product product, ShoppingCart shoppingCart) {
    double discount = 0;
    if(isApplicable.test(product, shoppingCart)){
      discount = calculation.calculateDiscount(product, shoppingCart);
    }
    discount += nextDiscount.apply(product, shoppingCart);
    return discount;
  }

  @Override
  public String getDescription(Product product, ShoppingCart shoppingCart) {
    String appliedDescription = "";
    if(isApplicable.test(product, shoppingCart)){
      appliedDescription = description;
    }
    String nextDescription = nextDiscount.getDescription(product, shoppingCart);

    if (!appliedDescription.isEmpty() && !nextDescription.isEmpty()) {
      appliedDescription += System.lineSeparator() + nextDescription;
    }else if(!nextDescription.isEmpty()){
      appliedDescription = nextDescription;
    }
    return appliedDescription;
  }
}
