package com.acme.mytrader.price;

import model.BuyStock;

public interface PriceListener {
    BuyStock priceUpdate(String security, double price);
}
