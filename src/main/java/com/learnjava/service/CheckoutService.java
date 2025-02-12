package com.learnjava.service;

import com.learnjava.domain.checkout.Cart;
import com.learnjava.domain.checkout.CartItem;
import com.learnjava.domain.checkout.CheckoutResponse;
import com.learnjava.domain.checkout.CheckoutStatus;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;

import java.util.List;

public class CheckoutService {

    private final PriceValidatorService priceValidatorService;
    public CheckoutService(PriceValidatorService priceValidatorService) {
        this.priceValidatorService = priceValidatorService;
    }

    public CheckoutResponse checkout(Cart cart) {
        startTimer();
       List<CartItem> priceValidationList = cart.getCartItemList()
//                .stream()
               .parallelStream()
                .peek(cartItem -> {
                    boolean isPriceInvalid = this.priceValidatorService.isCartItemInvalid(cartItem);
                    cartItem.setExpired(isPriceInvalid);
                })
                .filter(CartItem::isExpired)
               .toList();

       if(!priceValidationList.isEmpty()) {
           timeTaken();
           return new CheckoutResponse(CheckoutStatus.FAILURE, priceValidationList);
       }
       timeTaken();
        return new CheckoutResponse(CheckoutStatus.SUCCESS);
    }

}
