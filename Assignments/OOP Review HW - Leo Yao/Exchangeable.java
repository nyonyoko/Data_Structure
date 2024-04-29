public interface Exchangeable {
    // setting up the exchange rates
    // 1.00 EarthDollar (ED) = 1.30 MarsMoney (MM) = 0.87 SaturnSilver (SS) = 2.00 Neptune Nuggets (NN)
    double ed_mm = 1.30;
    double ed_ss = 0.87;
    double ed_nn = 2.00;
    // define the exchange method
    public void exchange(Currency other, double amount);
}
