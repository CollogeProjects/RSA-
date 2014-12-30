package domains;

/**
 * Created by Hugh on 2014/12/29 0029.
 */
public class ShoppingListItem extends Item{
    private int amount;
    private double subTotal;
    public ShoppingListItem(Item item) {
        this.barCode = item.barCode;
        this.name = item.name;
        this.unit = item.unit;
        this.price = item.price;
        this.amount = 1;
        this.subTotal = item.price;
    }

    public int getAmount() {
        return amount;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
