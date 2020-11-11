package com.acme.mytrader.service;

import com.acme.mytrader.data.StockData;
import com.acme.mytrader.price.PriceListener;
import com.acme.mytrader.price.PriceSource;
import com.acme.mytrader.strategy.TradingStrategy;
import model.Stock;
import model.BuyStock;
import model.PriceRange;

import java.util.List;

public class PriceSourceService implements PriceSource {

    private static final List<Stock> BASE_STOCKS = StockData.stocks();

    @Override
    public void addPriceListener(PriceListener listener) {
        TradingStrategy tradingStrategy = new TradingStrategy();
        Stock SellStock = BASE_STOCKS.stream().
                filter(p -> p.getPrice() > PriceRange.TRADING_PRICE.getPrice()).
                findFirst().get();
        Stock readyToSellStock = tradingStrategy.executeSell(SellStock, SellStock.getPrice());
        BuyStock selling = listener.priceUpdate(readyToSellStock.getSecurity(), readyToSellStock.getPrice());
        tradingStrategy.monitorBuyingPrice(selling.getPrice());
    }

    @Override
    public void removePriceListener(PriceListener listener) {
        TradingStrategy tradingStrategy = new TradingStrategy();
        Stock BuyingStock = BASE_STOCKS.stream().
                filter(p -> p.getPrice() > PriceRange.TRADING_PRICE.getPrice()).
                findFirst().get();
        Stock readyToSellStock = tradingStrategy.executeBuy(BuyingStock, BuyingStock.getPrice());
        BuyStock buying = listener.priceUpdate(readyToSellStock.getSecurity(), readyToSellStock.getPrice());
        tradingStrategy.monitorBuyingPrice(buying.getPrice());

    }
}
