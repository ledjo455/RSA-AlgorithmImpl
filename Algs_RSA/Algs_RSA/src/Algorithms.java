import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class Algorithms  {

    // algorithm for checking a prime number using Fermat's little theorem
    public boolean checkPrime(BigInteger n, int k)
    {
        for (int x = 0; x < k; x++) {
            //randomly generated BigInteger
            BigInteger a = new BigInteger(n.bitLength() - 1, new SecureRandom());
            //result for Fermat's test
            boolean result = modularExponential(a, n.subtract(BigInteger.ONE), n).equals(BigInteger.ONE);

            if (result) {
                return true;
            }
            else
                return false;
        }

        return true;
    }

    // algorithm for modular Exponential returns a * (x^2) mod N
    public BigInteger modularExponential(BigInteger a, BigInteger b, BigInteger N) {
        //base case
        if (b.equals(BigInteger.ZERO)) {
            return BigInteger.ONE;
        }

        BigInteger x = modularExponential(a, b.divide(BigInteger.TWO), N);
        //check if even
        if (b.getLowestSetBit() != 0) {
            return x.multiply(x).mod(N);
        }
        //a * (x^2) mod N
        return a.multiply(x.multiply(x)).mod(N);

    }

    // generate a Random Prime number
    public BigInteger genRandPrime(int sizeBits) {
        //secure random generator
        Random r = new SecureRandom();
        while (true) {
            //randomly generated BigInteger
            BigInteger x = new BigInteger(sizeBits, r);
            //Pick a positive integer x < N(120) at random
            if (checkPrime(x, 120)) {
                return x;
            }
        }
    }

    //Modular multiplicative inverse computed using the extended Euclid algorithm.
    public BigInteger modInverse(BigInteger a, BigInteger m) {
        //call extended Euclid (method) Algorithm
        GCD g = extendedGcd(a, m);
        //base case
        if(!(g.getG().equals(BigInteger.ONE))){
            return null;
        }else{
            //calculate the result
            BigInteger value = (g.getX().remainder(m).add(m)).remainder(m);
            return value;
        }
    }



    //Extended Euclidian Algorithm
    public GCD extendedGcd(BigInteger a, BigInteger b) {
        //this is the base case
        if (b.equals(BigInteger.ZERO)) {
            BigInteger x = BigInteger.ONE;
            BigInteger y = BigInteger.ZERO;
            return new GCD(x, y, a);
        }

        GCD gcd = extendedGcd(b, a.remainder(b));
        BigInteger x = gcd.getY(); // x = y1
        BigInteger d = gcd.getG();
        BigInteger y = gcd.getX().subtract((a.divide(b)).multiply(x)); //y= x1 - y1 * [a/b]

        return new GCD(x, y, d);
    }


}
