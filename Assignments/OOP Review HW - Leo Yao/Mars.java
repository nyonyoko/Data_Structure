// currency class that derives from the abstract parent class Currency and implements the interface Exchangeable
public class Mars extends Currency implements Exchangeable{
    // constructor
    public Mars(double totalFunds) {
        super(totalFunds);
        setCurrencyName( "MarsMoney");
    }
    
    public double toEarthDollars(double amount) {
        return amount /= ed_mm;
    }

    public double fromEarthDollars(double EarthDollars) {
        return EarthDollars *= ed_mm;
    }

    public String planetName() {
        return "Mars";
    }

    public double exchangeFee(double amount) {
        return amount * 0.10;
    }
}
