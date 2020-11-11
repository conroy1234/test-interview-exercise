package com.acme.mytrader.execution;

import com.acme.mytrader.data.StockData;
import com.acme.mytrader.strategy.TradingStrategy;
import model.Stock;

public class ExecutionServiceImpl implements ExecutionService {
    @Override
    public void buy(String security, double price, int volume) {
        TradingStrategy threadOne = new TradingStrategy();
        Stock buy = threadOne.executeBuy(new Stock(), price);
        threadOne.executeBuy(buy, price);
    }

    // monitor item ready to be sold
    @Override
    public void sell(String security, double price, int volume) {
        TradingStrategy threadOne = new TradingStrategy();
        Stock buy = threadOne.executeBuy(new Stock(), price);
        threadOne.executeSell(buy, price);
    }

    public static void main(String[] args) {
        ExecutionServiceImpl execute = new ExecutionServiceImpl();
        StockData.stocks().forEach(stock -> {
            execute.buy(stock.getSecurity(), stock.getPrice(), 1);
            execute.sell(stock.getSecurity(), stock.getPrice(), 1);
        });

    }
}
