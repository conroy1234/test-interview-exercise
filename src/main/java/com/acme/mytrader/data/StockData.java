package com.acme.mytrader.data;

import model.Stock;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class StockData<T> {
    public static List<Stock> stocks() {
        List<Stock> products = new CopyOnWriteArrayList<>();
        products.add(new Stock().setPrice(55).setSecurity("Lloyds"));
        products.add(new Stock().setPrice(70).setSecurity("Tesla"));
        products.add(new Stock().setPrice(80).setSecurity("Barcleys"));
        products.add(new Stock().setPrice(79).setSecurity("Apple"));
        products.add(new Stock().setPrice(200).setSecurity("Amazon"));
        return products;
    }
}
