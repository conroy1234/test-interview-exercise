package com.acme.mytrader.strategy;

import com.acme.mytrader.data.StockData;
import com.acme.mytrader.execution.ExecutionServiceImpl;
import model.Stock;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class TradingStrategyTest {

    ExecutionServiceImpl execute = new ExecutionServiceImpl();

    List<Stock> stocks = StockData.stocks();

    // test for all items sold

    @Test
    public void when_sell_stock() {
        List<Stock> sell = stocks.stream().filter(s -> s.getPrice() >= 100).collect(Collectors.toList());
        sell.stream().filter(s -> s.getPrice() >= 100).forEach(stock -> {
            execute.sell(stock.getSecurity(), stock.getPrice(), 1);
            assertNotNull(execute);
            assertThat(sell.size(), is(1));
        });

    }

    // test for all items bought
    @Test
    public void when_buy_stock() {
        List<Stock> buy = stocks.stream().filter(s -> s.getPrice() == 55).collect(Collectors.toList());
        buy.stream().filter(s -> s.getPrice() == 55).forEach(stock -> {
            execute.buy(stock.getSecurity(), stock.getPrice(), 1);
            assertNotNull(execute);
            assertThat(buy.size(), is(1));
        });

    }

    // test for all items waiting to buy
    @Test
    public void when_item_waiting_to_buy() {
        List<Stock> waiting = stocks.stream().filter(s -> s.getPrice() >= 55 && s.getPrice() < 100).collect(Collectors.toList());
        waiting.forEach(stock -> {
            System.out.println(stock);
            execute.sell(stock.getSecurity(), stock.getPrice(), 1);
            assertNotNull(execute);
            assertThat(waiting.size(), is(4));

        });

    }

    // test for all items waiting to sell
    @Test
    public void when_item_waiting_to_sell() {

        List<Stock> waiting = stocks.stream().filter(s -> s.getPrice() > 55 && s.getPrice() < 100).collect(Collectors.toList());
        waiting.forEach(stock -> {
            System.out.println(stock);
            execute.sell(stock.getSecurity(), stock.getPrice(), 1);
            assertNotNull(execute);
            assertThat(waiting.size(), is(3));

        });

    }
}
