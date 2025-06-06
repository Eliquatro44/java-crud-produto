package src;

public class ImportedProduct extends Product {
    private double customsFee;

    public ImportedProduct(String name, double price, double customsFee) {
        super(name, price);
        this.customsFee = customsFee;
    }

    @Override
    public String priceTag() {
        double total = price + customsFee;
        return name + " $ " + String.format("%.2f", total) + " (Customs fee: $ " + customsFee + ")";
    }
}
