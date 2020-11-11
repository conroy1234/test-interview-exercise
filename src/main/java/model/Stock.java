package model;

public class Stock {
    private String security;
    private double price;

    public String getSecurity() {
        return security;
    }

    public Stock setSecurity(String security) {
        this.security = security;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Stock setPrice(double price) {
        this.price = price;
        return this;
    }

    @Override
    public String toString() {
        return "Priceing{" +
                "security='" + security + '\'' +
                ", price=" + price +
                '}';
    }
}
