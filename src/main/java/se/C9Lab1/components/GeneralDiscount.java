package se.C9Lab1.components;

import se.C9Lab1.Product;
import se.C9Lab1.CalculateDiscount;
import se.C9Lab1.IsApplicable;

public class GeneralDiscount<T> implements Discount {
  String description;
  T condition;
  IsApplicable<T> isApplicable;
  CalculateDiscount calculation;

  public GeneralDiscount(String description, T condition , IsApplicable<T> isApplicable, CalculateDiscount calculateDiscount) {
    this.description = description;
    this.condition = condition;
    this.isApplicable = isApplicable;
    this.calculation = calculateDiscount;
  }

  @Override
  public double apply(Product product) {
    if(isApplicable.test(condition)){
        return calculation.calculateDiscount(product);
    }
    return 0;
  }

  @Override
  public String getDescription(Product product) {
    if(isApplicable.test(condition)){
      return description;
    }
    return "";
  }
}
