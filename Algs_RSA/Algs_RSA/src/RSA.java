import java.math.BigInteger;


public class RSA {
    private static final int KEY_SIZE = 64;
    private Algorithms algo = new Algorithms();
    private BigInteger p_Alice;
    private BigInteger p_Bob;
    private BigInteger q_Alice;
    private BigInteger q_Bob;
    private BigInteger h_Alice;
    private BigInteger h_Bob;
    public static BigInteger N_Alice;
    public static BigInteger N_Bob;
    public static BigInteger e_Alice;
    public static BigInteger e_Bob;
    public static BigInteger d_Alice;
    public static BigInteger d_Bob;


    public RSA() {
        //Calls genRand_pq() to get random p and q for both Alice and Bob
        genRand_pq();

        //condition for p and q for Alice and Bob
        if (q_Alice.compareTo(p_Alice) > 0 && q_Bob.compareTo(p_Bob) > 0 ) {
            BigInteger tempA = p_Alice;
            BigInteger tempB = p_Bob;
            p_Alice = q_Alice;
            p_Bob = q_Bob;
            q_Alice = tempA;
            q_Bob = tempB;
        }

        //calculate N for Alice and Bob given N = p * q
        N_Alice = p_Alice.multiply(q_Alice);
        N_Bob = p_Bob.multiply(q_Bob);

        // calculate h=(p-1)*(q-1)
        h_Alice = (p_Alice.subtract(BigInteger.ONE)).multiply((q_Alice.subtract(BigInteger.ONE)));
        h_Bob = (p_Bob.subtract(BigInteger.ONE)).multiply((q_Bob.subtract(BigInteger.ONE)));

        //generate relatively prime number e for Alice and Bob
        e_Alice = algo.genRandPrime(KEY_SIZE);
        e_Bob = algo.genRandPrime(KEY_SIZE);

        // Print inital statement for Alice and Bob (seperated)
        printAliceIntro();
        printBobIntro();

        while (gcdEuclids(h_Bob, e_Bob).compareTo(BigInteger.ONE) > 0 && e_Bob.compareTo(h_Bob) < 0) {
            e_Bob.add(BigInteger.ONE);
        }
    }

    //prints Alice's inital statement
    public void printAliceIntro(){
        System.out.println("(Alice says: Hello world, my public key is N=" + N_Alice + " and e=" + e_Alice+")");
        System.out.println(algo.checkPrime(p_Alice, 100));
        d_Alice = algo.modInverse(e_Alice, h_Alice);
        System.out.println("My description Key (d) is: " + d_Alice);
    }

    //prints Bob's initial statement
    public void printBobIntro(){
        System.out.println("(Bob says: Hello world, my public key is N=" + N_Bob + " and e=" + e_Bob+")");
        System.out.println(algo.checkPrime(p_Bob, 100));
        d_Bob = algo.modInverse(e_Bob, h_Bob);
        System.out.println("My description Key (d) is: " + d_Bob);
    }

    // generates random prime numbers for p and q of Alice and Bob
    public void genRand_pq(){
        p_Alice = algo.genRandPrime(32);
        q_Alice = algo.genRandPrime(32);
        p_Bob = algo.genRandPrime(32);
        q_Bob = algo.genRandPrime(32);
    }

    // performs Encryption ("A" for Alice statement) else(e.g "B") for Bob statement
    public BigInteger encrypt(BigInteger a, String type) {
        if (type == "A"){
        return algo.modularExponential(a, e_Alice, N_Alice); }
        else
            return algo.modularExponential(a, e_Bob, N_Bob);
    }


    // performs Decryption ("A" for Alice statement) else(e.g "B") for Bob statement
    public BigInteger decrypt(BigInteger a, String type) {
        if (type == "A"){
        return algo.modularExponential(a, d_Alice, N_Alice); }
        else
            return algo.modularExponential(a, d_Bob, N_Bob);
    }



    //get method for key size
    public int getKeySize() {

        return KEY_SIZE;
    }

    //finding greatest common divisor given two BigIntegers
    public BigInteger gcdEuclids(BigInteger x, BigInteger y) {

        if (y.equals(BigInteger.ZERO)) {
            return x;
        }
        return gcdEuclids(y, x.mod(y));
    }

}

