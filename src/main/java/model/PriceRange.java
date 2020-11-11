package model;

public enum PriceRange {
    BUYING_PRICE(55),TRADING_PRICE(70);

    private long price;

    private PriceRange(long price){
        this.price = price;
    }

    public long getPrice(){
        return price;
    }


}
