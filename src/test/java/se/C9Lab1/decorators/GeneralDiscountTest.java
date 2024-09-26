package se.C9Lab1.decorators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import se.C9Lab1.components.Discount;
import se.C9Lab1.entities.Product;
import se.C9Lab1.entities.ShoppingCart;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeneralDiscountTest {
  private final ShoppingCart shoppingCart = new ShoppingCart("TEST_SHOPPINGCART", LocalDate.now());
  private final Product product = new Product("TEST_PRODUCT", 100, 1);
  private final IsApplicable CONDITION_TRUE = (ShoppingCart shoppingCart) -> true;
  private final IsApplicable CONDITION_FALSE = (ShoppingCart shoppingCart) -> false;
  private final CalculateDiscount DISCOUNT_CALC_ONE = (ShoppingCart shoppingCart) -> 10;
  private final CalculateDiscount DISCOUNT_CALC_TWO = (ShoppingCart shoppingCart) -> 20;
  private final MockedConcreteComponent mockedConcreteComponent = new MockedConcreteComponent();
  private final String DESCRIPTION_ONE = "10% off!";
  private final String DESCRIPTION_TWO = "20% off!";

  @BeforeEach
  void setUp() {
    shoppingCart.addProduct(product);
  }

  @Nested
  class GetDescription {
    @Test
    @DisplayName("No decorators isApplicable returns true should return empty string")
    void noDecoratorsIsApplicableReturnsShouldTrueReturnEmptyString() {
      Discount generalDiscount2 =
          new GeneralDiscount(DESCRIPTION_TWO, CONDITION_FALSE, DISCOUNT_CALC_TWO, mockedConcreteComponent);
      Discount generalDiscount1 =
          new GeneralDiscount(DESCRIPTION_ONE, CONDITION_FALSE, DISCOUNT_CALC_ONE, generalDiscount2);

      List<String> description = generalDiscount1.getDescription(shoppingCart);

      assertEquals(List.of(), description);
    }

    @Test
    @DisplayName("One decorators isApplicable is true should return only one description string")
    void oneDecoratorsIsApplicableIsTrueShouldReturnOnlyOnDescriptionString() {
      Discount generalDiscount2 =
          new GeneralDiscount(DESCRIPTION_TWO, CONDITION_TRUE, DISCOUNT_CALC_TWO, mockedConcreteComponent);
      Discount generalDiscount1 =
          new GeneralDiscount(DESCRIPTION_ONE, CONDITION_FALSE, DISCOUNT_CALC_ONE, generalDiscount2);

      List<String> description = generalDiscount1.getDescription(shoppingCart);

      assertEquals(List.of(DESCRIPTION_TWO), description);
    }

    @Test
    @DisplayName("Both decorators isApplicable is true should return combined description string")
    void BothDecoratorsIsApplicableIsTrueShouldReturnCombinedDescriptionString() {
      Discount generalDiscount2 =
          new GeneralDiscount(DESCRIPTION_TWO, CONDITION_TRUE, DISCOUNT_CALC_TWO, mockedConcreteComponent);
      Discount generalDiscount1 =
          new GeneralDiscount(DESCRIPTION_ONE, CONDITION_TRUE, DISCOUNT_CALC_ONE, generalDiscount2);

      List<String> description = generalDiscount1.getDescription(shoppingCart);

      assertEquals(List.of(DESCRIPTION_ONE, DESCRIPTION_TWO), description);
    }
  }

  @Nested
  class Apply {
    @Test
    @DisplayName("No decorators isApplicable is true should return zero")
    void noDecoratorsIsApplicableIsTrueShouldReturnZero() {
      Discount generalDiscount2 =
          new GeneralDiscount(DESCRIPTION_TWO, CONDITION_FALSE, DISCOUNT_CALC_TWO, mockedConcreteComponent);
      Discount generalDiscount1 =
          new GeneralDiscount(DESCRIPTION_ONE, CONDITION_FALSE, DISCOUNT_CALC_ONE, generalDiscount2);

      List<Double> discount = generalDiscount1.apply(shoppingCart);

      assertEquals(List.of(), discount);
    }

    @Test
    @DisplayName("One decorators isApplicable is true should return that decorators calculateDiscount return value")
    void oneDecoratorsIsApplicableIsTrueShouldReturnThatDecoratorsCalculateDiscountReturnValue() {
      Discount generalDiscount2 =
          new GeneralDiscount(DESCRIPTION_TWO, CONDITION_TRUE, DISCOUNT_CALC_TWO, mockedConcreteComponent);
      Discount generalDiscount1 =
          new GeneralDiscount(DESCRIPTION_ONE, CONDITION_FALSE, DISCOUNT_CALC_ONE, generalDiscount2);

      List<Double> discount = generalDiscount1.apply(shoppingCart);

      assertEquals(List.of(20.0), discount);
    }

    @Test
    @DisplayName("Both decorators isApplicable is true should return sum of both decorators calculateDiscount return value")
    void bothDecoratorsIsApplicableIsTrueShouldReturnSumOfBothDecoratorsCalculateDiscountReturnValue() {
      Discount generalDiscount2 =
          new GeneralDiscount(DESCRIPTION_TWO, CONDITION_TRUE, DISCOUNT_CALC_TWO, mockedConcreteComponent);
      Discount generalDiscount1 =
          new GeneralDiscount(DESCRIPTION_ONE, CONDITION_TRUE, DISCOUNT_CALC_ONE, generalDiscount2);

      List<Double> discount = generalDiscount1.apply(shoppingCart);

      assertEquals(List.of(10.0, 20.0), discount);
    }
  }
}
