package model;

public class BuyStock {
    private String security;
    private double price;

    public String getSecurity() {
        return security;
    }

    public BuyStock setSecurity(String security) {
        this.security = security;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public BuyStock setPrice(double price) {
        this.price = price;
        return this;
    }

    @Override
    public String toString() {
        return "BuyStock{" +
                "security='" + security + '\'' +
                ", price=" + price +
                '}';
    }
}
