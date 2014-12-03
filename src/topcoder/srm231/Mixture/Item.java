package topcoder.srm231.Mixture;

public class Item implements Comparable<Item>{
    public int id = -1;
    public double quantity = -1;
    public double price = -1;
    public int availMixRow = -1;
    public double pricePerUnit = -1;
    public Item(int id, double quantity, double price, int availMixRow){
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.availMixRow = availMixRow;
        this.pricePerUnit = price/quantity;
    }
    @Override
    public int compareTo(Item other) {
        return new Double(this.pricePerUnit).compareTo(new Double(other.pricePerUnit));
    }
    
    public double quantityForPrice(double p){
        return (1/this.pricePerUnit)*p;
    }
    
    public double priceForQuantity(double quant){
        return quant*this.pricePerUnit;
    }
}
