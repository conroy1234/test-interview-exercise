package com.acme.mytrader.service;

import com.acme.mytrader.price.PriceListener;
import com.acme.mytrader.strategy.TradingStrategy;
import model.BuyStock;

public class PriceListenerService implements PriceListener {

    @Override
    public BuyStock priceUpdate(String security, double price) {
        TradingStrategy tradingStrategy = new TradingStrategy();
        return tradingStrategy.monitorBuyingPrice(price);
    }
}
