package com.acme.mytrader.strategy;

import com.acme.mytrader.data.StockData;
import model.Stock;
import model.BuyStock;
import model.PriceRange;

import java.util.List;

/**
 * <pre>
 * User Story: As a trader I want to be able to monitor stock prices such
 * that when they breach a trigger level orders can be executed automatically
 * </pre>
 */
public class TradingStrategy {

    private static final Object LOCK = new Object();
    private static final List<Stock> BASE_STOCKS = StockData.stocks();

    public BuyStock monitorBuyingPrice(double price) {

        if (price > 55) {
            Stock SellStock = BASE_STOCKS.stream().
                    filter(p -> p.getPrice() > PriceRange.TRADING_PRICE.getPrice()).
                    findFirst().get();
            Stock readyToSellStock = executeSell(SellStock, price);
            return new BuyStock().setPrice(readyToSellStock.getPrice()).setSecurity(readyToSellStock.getSecurity());
        } else {
            Stock baseStock = BASE_STOCKS.stream().
                    filter(p -> p.getPrice() <= PriceRange.BUYING_PRICE.getPrice()).
                    findFirst().get();
            Stock readyTobuBaught = executeBuy(baseStock, price);
            return new BuyStock().setPrice(readyTobuBaught.getPrice()).setSecurity(readyTobuBaught.getSecurity());
        }
    }

    public Stock executeBuy(Stock baseStock, double price) {
        Runnable run = () -> {
            synchronized (LOCK) {
                if (price > PriceRange.BUYING_PRICE.getPrice()) {
                    System.out.println(" waiting to buy ");
                    LOCK.notify();
                } else {
                    Stock newStack = new Stock();
                    newStack.setPrice(PriceRange.BUYING_PRICE.getPrice());
                    String security = BASE_STOCKS.stream().
                            filter(p -> p.getPrice() <= PriceRange.BUYING_PRICE.getPrice()).
                            findFirst().get().getSecurity();
                    newStack.setSecurity(security);
                    newStack.setSecurity(security);
                    System.out.println(" BUY NEW ITEM > : " + newStack);
                    BASE_STOCKS.remove(newStack);
                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    }
                }
            }
        };
        Thread buyingThread = new Thread(run);
        buyingThread.start();
        return baseStock;
    }

    public synchronized Stock executeSell(Stock baseStock, double price) {
        Runnable run = () -> {
            synchronized (LOCK) {
                if (price < PriceRange.TRADING_PRICE.getPrice()) {
                    System.out.println(" waiting to sell ");
                    LOCK.notify();
                } else {
                    Stock newStack = new Stock();
                    newStack.setPrice(PriceRange.TRADING_PRICE.getPrice());
                   String security = BASE_STOCKS.stream().
                            filter(p -> p.getPrice() <= PriceRange.TRADING_PRICE.getPrice()).
                            findFirst().get().getSecurity();
                    newStack.setSecurity(security);
                    System.out.println(" ITEM HAS BEEN SOLED > : " + newStack);
                    BASE_STOCKS.add(newStack);
                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    }
                }
            }
        };
        Thread buyingThread = new Thread(run);
        buyingThread.start();
        return baseStock;
    }
}
