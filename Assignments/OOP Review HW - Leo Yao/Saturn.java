// currency class that derives from the abstract parent class Currency and implements the interface Exchangeable
public class Saturn extends Currency implements Exchangeable {
    // constructor
    public Saturn(double totalFunds) {
        super(totalFunds);
        setCurrencyName( "SaturnSilver");
    }

    public double toEarthDollars(double amount) {
        return amount /= ed_ss;
    }

    public double fromEarthDollars(double EarthDollars) {
        return EarthDollars *= ed_ss;
    }
    
    public String planetName() {
        return "Saturn";
    }

    public double exchangeFee(double amount) {
        return 0;
    }
}
