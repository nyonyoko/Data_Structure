public class PrimeCalculator {
    public void primesTo(int n) {
        try{
            if (n < 2) {
                // print error message
                throw new Exception("Error: Input must be a number greater than or equal to 2.");
            }
            // Initialize a queue called numbers filled with all integers from 2 to n.
            Queue<Integer> numbers = new ArrayQueue<Integer>();
            for (int i = 2; i <= n; i++) {
                numbers.enqueue(i);
            }
            // Initiliaze another empty queue called primes.
            Queue<Integer> primes = new ArrayQueue<Integer>();
            // While numbers is not empty, do the following:
            // Remove the smallest (first) element in numbers, call it p, and add it to the end of primes.
            // Remove all elements of numbers that are divisible by p
            // by repeatedly testing whether elements in the front are divisible by p, 
            // removing them if they are or adding them to the end of numbers if they are not.
            while (!numbers.isEmpty()) {
                int p = numbers.dequeue();
                primes.enqueue(p);
                int x = numbers.size();
                for (int i = 0; i < x; i++) {
                    int test = numbers.dequeue();
                    if (test % p != 0) {
                        numbers.enqueue(test);
                    }
                }
            }
            // Print the elements in primes.
            System.out.println("Printing primes up to " + n + ": ");
            while (!primes.isEmpty()) {
                System.out.print(primes.dequeue());
                if (!primes.isEmpty()){
                    System.out.print(", ");
                }
            }
            System.out.println(".");
            System.out.println();        
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }

    public static void main(String[] args) {
        new PrimeCalculator().primesTo(20);
        new PrimeCalculator().primesTo(2);
        new PrimeCalculator().primesTo(0);
    }
}
