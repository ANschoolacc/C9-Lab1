package se.C9Lab1.decorators;

import se.C9Lab1.components.Discount;
import se.C9Lab1.entities.Product;

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
  public double apply(Product product) {
    double discount = 0;
    if(isApplicable.test(product)){
      discount = calculation.calculateDiscount(product);
    }
    discount += nextDiscount.apply(product);
    return discount;
  }

  @Override
  public String getDescription(Product product) {
    String appliedDescription = "";
    if(isApplicable.test(product)){
      appliedDescription = description;
    }

    String nextDescription = nextDiscount.getDescription(product);

    if (!appliedDescription.isEmpty() && !nextDescription.isEmpty()) {
      appliedDescription += System.lineSeparator() + nextDescription;
    }else if(!nextDescription.isEmpty()){
      appliedDescription = nextDescription;
    }
    return appliedDescription;
  }
}
