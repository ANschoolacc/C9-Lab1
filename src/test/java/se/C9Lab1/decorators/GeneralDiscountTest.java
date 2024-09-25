package se.C9Lab1.decorators;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import se.C9Lab1.components.Discount;
import se.C9Lab1.entities.Product;
import se.C9Lab1.entities.ShoppingCart;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class GeneralDiscountTest {
  private final ShoppingCart shoppingCart = new ShoppingCart("TEST_CART", LocalDate.now());
  private final Product product = new Product("TEST_PRODUCT", 100, 1);
  private final IsApplicable CONDITION_TRUE = (Product p, ShoppingCart cart) -> true;
  private final IsApplicable CONDITION_FALSE = (Product p, ShoppingCart cart) -> false;
  private final CalculateDiscount DISCOUNT_CALC_ONE = (Product p, ShoppingCart cart) -> 10;
  private final CalculateDiscount DISCOUNT_CALC_TWO = (Product p, ShoppingCart cart) -> 20;
  private final MockedConcreteComponent mockedConcreteComponent = new MockedConcreteComponent();
  private final String DESCRIPTION_ONE = "10% off!";
  private final String DESCRIPTION_TWO = "20% off!";

  @Nested
  class GetDescription {
    @Test
    @DisplayName("No decorators isApplicable returns true should return empty string")
    void noDecoratorsIsApplicableReturnsShouldTrueReturnEmptyString() {
      Discount generalDiscount2 =
          new GeneralDiscount(DESCRIPTION_TWO, CONDITION_FALSE, DISCOUNT_CALC_TWO, mockedConcreteComponent);
      Discount generalDiscount1 =
          new GeneralDiscount(DESCRIPTION_ONE, CONDITION_FALSE, DISCOUNT_CALC_ONE, generalDiscount2);

      String description = generalDiscount1.getDescription(product, shoppingCart);

      assertEquals("", description);
    }

    @Test
    @DisplayName("One decorators isApplicable is true should return only one description string")
    void oneDecoratorsIsApplicableIsTrueShouldReturnOnlyOnDescriptionString() {
      Discount generalDiscount2 =
          new GeneralDiscount(DESCRIPTION_TWO, CONDITION_TRUE, DISCOUNT_CALC_TWO, mockedConcreteComponent);
      Discount generalDiscount1 =
          new GeneralDiscount(DESCRIPTION_ONE, CONDITION_FALSE, DISCOUNT_CALC_ONE, generalDiscount2);

      String description = generalDiscount1.getDescription(product, shoppingCart);

      assertEquals(DESCRIPTION_TWO, description);
    }

    @Test
    @DisplayName("Both decorators isApplicable is true should return combined description string")
    void BothDecoratorsIsApplicableIsTrueShouldReturnCombinedDescriptionString() {
      Discount generalDiscount2 =
          new GeneralDiscount(DESCRIPTION_TWO, CONDITION_TRUE, DISCOUNT_CALC_TWO, mockedConcreteComponent);
      Discount generalDiscount1 =
          new GeneralDiscount(DESCRIPTION_ONE, CONDITION_TRUE, DISCOUNT_CALC_ONE, generalDiscount2);

      String description = generalDiscount1.getDescription(product, shoppingCart);

      assertEquals(DESCRIPTION_ONE + System.lineSeparator() + DESCRIPTION_TWO, description);
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

      double discount = generalDiscount1.apply(product, shoppingCart);

      assertEquals(0, discount);

    }

    @Test
    @DisplayName("One decorators isApplicable is true should return that decorators calculateDiscount return value")
    void oneDecoratorsIsApplicableIsTrueShouldReturnThatDecoratorsCalculateDiscountReturnValue() {
      Discount generalDiscount2 =
          new GeneralDiscount(DESCRIPTION_TWO, CONDITION_TRUE, DISCOUNT_CALC_TWO, mockedConcreteComponent);
      Discount generalDiscount1 =
          new GeneralDiscount(DESCRIPTION_ONE, CONDITION_FALSE, DISCOUNT_CALC_ONE, generalDiscount2);

      double discount = generalDiscount1.apply(product, shoppingCart);

      assertEquals(20, discount);
    }

    @Test
    @DisplayName("Both decorators isApplicable is true should return sum of both decorators calculateDiscount return value")
    void bothDecoratorsIsApplicableIsTrueShouldReturnSumOfBothDecoratorsCalculateDiscountReturnValue() {
      Discount generalDiscount2 =
          new GeneralDiscount(DESCRIPTION_TWO, CONDITION_TRUE, DISCOUNT_CALC_TWO, mockedConcreteComponent);
      Discount generalDiscount1 =
          new GeneralDiscount(DESCRIPTION_ONE, CONDITION_TRUE, DISCOUNT_CALC_ONE, generalDiscount2);

      double discount = generalDiscount1.apply(product, shoppingCart);

      assertEquals(30, discount);
    }
  }
}
