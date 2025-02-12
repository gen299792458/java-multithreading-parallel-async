package com.learnjava.service;

import com.learnjava.domain.checkout.Cart;
import com.learnjava.domain.checkout.CheckoutResponse;
import com.learnjava.domain.checkout.CheckoutStatus;
import com.learnjava.util.DataSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutServiceTest {

    PriceValidatorService priceValidatorService = new PriceValidatorService();
    CheckoutService checkoutService = new CheckoutService(priceValidatorService);

@Test
    void checkout_6_items() {

//    give
    Cart cart = DataSet.createCart(60);

//    when
    CheckoutResponse checkoutResponse =  checkoutService.checkout(cart);

//    then
    assertNotNull(checkoutResponse);
    assertEquals(CheckoutStatus.SUCCESS, checkoutResponse.getCheckoutStatus());
}
}