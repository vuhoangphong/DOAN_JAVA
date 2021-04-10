package web.badminton.service;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.Properties;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import web.badminton.dto.RegisterUserDTO;
import web.badminton.enums.RoleEnum;
import web.badminton.modal.keyEncryptDecrypt;
import web.badminton.repository.RoleRepository;
import web.badminton.repository.UserRepository;

@Service
public class RegisterUserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    public int checkAccountExist(RegisterUserDTO register) {
        return userRepository.checkAccountExist(register);
    }

    public int insertUser(RegisterUserDTO register) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encrytedPassword =encoder.encode(register.getPassword());
        register.setPassword(encrytedPassword);
        return userRepository.insertUser(register);
    }

    // send email
    public static void sendEmailVerify(String account, String email) throws AddressException, MessagingException,
            InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException,
            BadPaddingException {
        // encrypt id
        String accountEncrypt = Encrypt(account);
        Properties mailServerProperties;
        Session getMailSession;
        MimeMessage mailMessage;

        // setup simple mail transfer protocol
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");

        // get mail session
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        mailMessage = new MimeMessage(getMailSession);

        // mail received
        mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

        // mail content
        mailMessage.setSubject("BADMINTON");
        mailMessage.setText("Nhấp vào link để kích hoạt tài khoản http://localhost:8080/verify?id=" + accountEncrypt);

        // send mail
        Transport transport = getMailSession.getTransport("smtp");
        transport.connect("smtp.gmail.com", "hoangdaidp3@gmail.com", "0978089549");
        transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
        transport.close();
    }

    static keyEncryptDecrypt keyEncryptDecrypt = new keyEncryptDecrypt();

    static byte[] byteEncrypted;
    static Cipher cipher;
    static SecretKeySpec skeySpec;

    // check verify
    public static String checkVerify(String id) throws InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException {
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] byteDecrypted = cipher.doFinal(byteEncrypted);
        String decrypted = new String(byteDecrypted);
        return decrypted;
    }

    // encrypt id
    public static String Encrypt(String id) throws NoSuchAlgorithmException, NoSuchPaddingException,
        InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        String SECRET_KEY = "dohoangdai010799";
       skeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");

        cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byteEncrypted = cipher.doFinal(id.getBytes());
        String encrypted =  Base64.getEncoder().encodeToString(byteEncrypted);
        return encrypted;
    }

    public boolean verify(String account) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        try {
        String decrypt = checkVerify(account);
        if(userRepository.verifyUser("1",decrypt)==1){     
            int idUser  = userRepository.getIdUser(decrypt);
            roleRepository.insertRole(idUser,RoleEnum.ROLE_MEMBER.get());
            return true;
        }
        return false;
        } catch (Exception e) {
            return false;
        }
    }

    }	
    
