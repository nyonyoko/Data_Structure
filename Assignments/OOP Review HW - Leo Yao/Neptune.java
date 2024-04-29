// currency class that derives from the abstract parent class Currency and implements the interface Exchangeable
public class Neptune extends Currency implements Exchangeable {
    // constructor
    public Neptune(double totalFunds) {
        super(totalFunds);
        setCurrencyName( "NeptuneNuggets");
    }

    public double toEarthDollars(double amount) {
        return amount /= ed_nn;
    }

    public double fromEarthDollars(double EarthDollars) {
        return EarthDollars *= ed_nn;
    }

    public String planetName() {
        return "Neptune";
    }

    public double exchangeFee(double amount) {
        return 5;
    }
}
