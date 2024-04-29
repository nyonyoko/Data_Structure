// abstract parent class for all currency subclasses
public abstract class Currency {
    private String currencyName;
    private double totalFunds;
    // constructor
    public Currency(double totalFunds) {
        this.totalFunds = totalFunds;
    }
    // getters and setters
    public double getTotalFunds(){
        return totalFunds;
    }
    public String getCurrencyName(){
        return currencyName;
    }
    public void setTotalFunds(double totalFunds){
        this.totalFunds = totalFunds;
    }
    public void setCurrencyName(String currencyName){
        this.currencyName = currencyName;
    }

    public abstract double toEarthDollars(double amount);

    public abstract double fromEarthDollars(double EarthDollars);

    public abstract String planetName();

    public abstract double exchangeFee(double amount);

    public void exchange(Currency other, double amount){
        double totalCost = this.exchangeFee(amount) + amount;
        if (totalCost > this.getTotalFunds()){
            System.err.println("Uh oh - " + this.planetName() + " only has an available balance of " + String.format("%.2f", this.getTotalFunds()) + ", which is less than " + String.format("%.2f", totalCost) + "!");
        } else {
            System.out.println("Converting from " + this.getCurrencyName() + " to " + other.getCurrencyName() + " and initiating transfer...");
            double dollarAmount = this.toEarthDollars(amount);
            double outAmount = other.fromEarthDollars(dollarAmount);
            System.out.println(String.format("%.2f", amount) + " " + this.getCurrencyName() + " = " + String.format("%.2f", dollarAmount) + " EarthDollars = " + String.format("%.2f", outAmount) + " " + other.getCurrencyName());
            System.out.println(this.planetName() + " exchange fee is " + String.format("%.2f", this.exchangeFee(amount)) + " " + this.getCurrencyName());
            this.setTotalFunds(this.getTotalFunds() - totalCost);
            other.setTotalFunds(other.getTotalFunds() + outAmount);
            System.out.println(this.planetName() + " has a total of " + String.format("%.2f", this.getTotalFunds()) + " " + this.getCurrencyName());
            System.out.println(other.planetName() + " has a total of " + String.format("%.2f", other.getTotalFunds()) + " " + other.getCurrencyName());
        }
        System.out.println();
    }
}
