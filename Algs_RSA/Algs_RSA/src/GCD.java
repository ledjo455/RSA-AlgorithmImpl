import java.math.BigInteger;

public class GCD {
    private BigInteger g;
    private BigInteger x;
    private BigInteger y;

    //constructor
    public GCD(BigInteger x, BigInteger y, BigInteger d){
        this.x = x;
        this.y = y;
        this.g = d;


    }

    //getters and setters
    public BigInteger getG() {
        return g;
    }

    public void setG(BigInteger g) {
        this.g = g;
    }

    public BigInteger getX() {
        return x;
    }

    public void setX(BigInteger x) {
        this.x = x;
    }

    public BigInteger getY() {
        return y;
    }

    public void setY(BigInteger y) {
        this.y = y;
    }
}
