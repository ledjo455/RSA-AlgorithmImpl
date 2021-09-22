import java.math.BigInteger;
import java.security.SecureRandom;

public class Factorization {
    //   Factor N using the Pollard-Rho method.
    // used for performing the attack simulation by Charlie

    private final static SecureRandom random = new SecureRandom();

    public static BigInteger Pollard(BigInteger N) {
        BigInteger d; //divisor
        //randomly generated Integer r and a
        BigInteger r = new BigInteger(N.bitLength(), random);
        BigInteger a = new BigInteger(N.bitLength(), random);
        BigInteger v = a;

        //perform test divisibility
        if (dTwo(N)) {
            return BigInteger.TWO;
        }

        do {
            a = a.multiply(a).mod(N).add(r).mod(N);
            v = v.multiply(v).mod(N).add(r).mod(N);
            v = v.multiply(v).mod(N).add(r).mod(N);
            d = a.subtract(v).gcd(N);
        } while ((d.compareTo(BigInteger.ONE)) == 0);

        return d;
    }
    //Check divisiblity by TWO
    public static boolean dTwo(BigInteger S) {
        boolean b = S.mod(BigInteger.TWO).compareTo(BigInteger.ZERO) == 0;
        return b;
    }

}
