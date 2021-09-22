import java.math.BigInteger;

public class CharlieAttack {

    public static void performAttack() {
        Algorithms algo = new Algorithms();
        Communication c = new Communication();
        System.out.println("Alice decryption key: " + RSA.d_Alice);
        BigInteger a = Factorization.Pollard(RSA.N_Alice);
        BigInteger aa = RSA.N_Alice.divide(a);
        BigInteger x = (a.subtract(BigInteger.ONE)).multiply(aa.subtract(BigInteger.ONE));
        System.out.println("Charlie's decryption key: " + algo.modInverse(RSA.e_Alice, x));

        System.out.println("Bob decryption key: " + RSA.d_Bob);
        BigInteger b = Factorization.Pollard(RSA.N_Bob);
        BigInteger bb = RSA.N_Bob.divide(b);
        BigInteger y = (b.subtract(BigInteger.ONE)).multiply(bb.subtract(BigInteger.ONE));
        System.out.println("Charlie's decryption key: " + algo.modInverse(RSA.e_Bob, y));
    }
}
