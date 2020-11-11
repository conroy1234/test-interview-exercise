package com.acme.mytrader.service;

import model.PriceRange;
import model.Stock;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class PriceListenerServiceTest {

    @Test
    public void priceUpdate_readyToBuy() {
        Stock baseStock = stocks().stream().
                filter(p -> p.getPrice() <= PriceRange.BUYING_PRICE.getPrice()).
                findFirst().get();
        Stock buyStock = new Stock().setPrice(baseStock.getPrice()).setSecurity(baseStock.getSecurity());
        assertThat(buyStock.getSecurity()).isEqualTo("Lloyds");
        assertThat(buyStock.getPrice()).isEqualTo(55);
        PriceListenerService priceListenerService = new PriceListenerService();
        assertThat(priceListenerService.priceUpdate(baseStock.getSecurity(), baseStock.getPrice())).isEqualToComparingFieldByField(buyStock);
    }

    @Test
    public void priceUpdate_waitingToBuy() {
        Stock baseStock = stocks().stream().
                filter(p -> p.getPrice() > PriceRange.BUYING_PRICE.getPrice()).
                findFirst().get();
        Stock buyStock = new Stock().setPrice(baseStock.getPrice()).setSecurity(baseStock.getSecurity());
        assertThat(buyStock.getPrice()).isEqualTo(70);
        assertThat(buyStock.getSecurity()).isEqualTo("Tesla");
        PriceListenerService priceListenerService = new PriceListenerService();
        assertThat(priceListenerService.priceUpdate(buyStock.getSecurity(), buyStock.getPrice())).isNotEqualTo(buyStock);
    }

    private List<Stock> stocks() {
        List<Stock> products = new CopyOnWriteArrayList<>();
        products.add(new Stock().setPrice(55).setSecurity("Lloyds"));
        products.add(new Stock().setPrice(70).setSecurity("Tesla"));
        products.add(new Stock().setPrice(80).setSecurity("Barcleys"));
        products.add(new Stock().setPrice(79).setSecurity("Apple"));
        products.add(new Stock().setPrice(200).setSecurity("Amazon"));
        return products;
    }
}