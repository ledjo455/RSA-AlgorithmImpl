import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

public class Communication {

    private static RSA RSA = new RSA();


    //method for performing RSA encry. & decry. given Type( "A" for AliceToBob or *any* for BobToAlice)
    public static boolean Channel(String sender, String recipient, String type) throws IOException {
        DataInputStream in = new DataInputStream(System.in);
        System.out.println("\nRSA chatting. *Not Secured*");
        System.out.println("\nContinue chatting, or press Z to perform the attack");
        System.out.print(sender+ " says: ");
        String input = in.readLine();

        //attack simulation
        if (input.equals("Z")) {
            CharlieAttack.performAttack();
            return true;
        }
        //encrypt sender's input
        System.out.println(sender + " says: " + input);
        ArrayList<BigInteger> encryptText = new ArrayList<>();
        for (char c : input.toCharArray()) {
            int a = c;
            BigInteger eM = RSA.encrypt(BigInteger.valueOf(a), type);
            encryptText.add(eM);
        }

        String eMessage = "";
        for (BigInteger b : encryptText) {
            String num = b.toString();
            //add leading Zero's
            num = String.format("%1$" + RSA.getKeySize() / 2 + "s", num).replace(' ', '0');
           //concatination
            eMessage += num;

        }

        System.out.println("\n(Charlie reads(encoded text): " + new String(new BigInteger(eMessage).toByteArray()));
        System.out.print(recipient + " reads (after decoding): ");
        //decrypt message
        String decryptMsg = "";
        for (String m : eMessage.split("(?<=\\G.{32})")) {
            BigInteger numMsg = new BigInteger(m);
            BigInteger decryptedText = RSA.decrypt((numMsg),type);
            decryptMsg += new String(decryptedText.toByteArray());
        }
        System.out.println(decryptMsg);

        return false;
    }


}
