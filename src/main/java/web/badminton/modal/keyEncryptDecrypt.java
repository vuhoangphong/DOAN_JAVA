package web.badminton.modal;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

public class keyEncryptDecrypt {

    private static PrivateKey prkey;
	private static  PublicKey pbkey;
	static{
        KeyPairGenerator kpg = null;

        try {
            kpg = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    
        kpg.initialize(512);
        
        KeyPair kp = kpg.genKeyPair();
            pbkey = kp.getPublic();
            prkey = kp.getPrivate();
    }

    public PrivateKey getPrkey(){
        return prkey;
    }

    
    public PublicKey getPbkey(){
        return pbkey;
    }
}