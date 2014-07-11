
package test.javax.crypto.Cipher;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class TestCipherAES {
    private static final String KEY_AES = "AES";
    /*
     * from http://snowolf.iteye.com/blog/379860
     */

    private static final String KEY_MD5 = "MD5";
    private static final String KEY_SHA = "SHA";
    /*
     * [对称] AES加密算法即密码学中的高级加密标准（Advanced Encryption
     * Standard，AES），又称Rijndael加密法，是美国联邦政府采用的一种区块加密标准
     * 。这个标准用来替代原先的DES，已经被多方分析且广为全世界所使用。经过五年的甄选流程，高级加密标准由美国国家标准与技术研究院
     * （NIST）于2001年11月26日发布于FIPS PUB
     * 197，并在2002年5月26日成为有效的标准。2006年，高级加密标准已然成为对称密钥加密中最流行的算法之一。
     */
    Cipher mCipher;
    SecretKeySpec mSecretKeySpec;

    public TestCipherAES() throws Exception {
        createCiper();
    }

    public String toString() {
        return mCipher.toString() + ", key = " + new String(mSecretKeySpec.getEncoded());
    }

    private void createCiper() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_AES);
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] raw = secretKey.getEncoded();
        mSecretKeySpec = new SecretKeySpec(raw, KEY_AES);
        mCipher = Cipher.getInstance(KEY_AES);
    }

    private byte[] encryptAES(byte[] from) throws Exception {
        mCipher.init(Cipher.ENCRYPT_MODE, mSecretKeySpec);
        return mCipher.doFinal(from);
    }

    private byte[] decryptAES(byte[] from) throws Exception {
        mCipher.init(Cipher.DECRYPT_MODE, mSecretKeySpec);
        return mCipher.doFinal(from);
    }

    /**
     * BASE64解密
     * 
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    /**
     * BASE64加密
     * 
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    /**
     * MD5加密
     * 
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] encryptMD5(byte[] data) throws Exception {

        MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
        md5.update(data);

        return md5.digest();

    }

    /**
     * SHA加密
     * 
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] encryptSHA(byte[] data) throws Exception {

        MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
        sha.update(data);

        return sha.digest();

    }

    public static void main(String[] args) {
        String target = " 加密解密 test asdfasdfwe23r523dgfsdf";
        TestCipherAES t;
        try {
            t = new TestCipherAES();
            System.out.println(t.toString());

            byte[] origin = target.getBytes();
            System.out.println("origin  :" + target);
            System.out.println("encrypt AES:" + new String(t.encryptAES(origin)));
            System.out.println("decrypt AES:" + new String(t.decryptAES(t.encryptAES(origin))));

            System.out.println("encryptBASE64, " + encryptBASE64(target.getBytes()));
            System.out.println("decryptBASE64, "
                    + new String(decryptBASE64(encryptBASE64(target.getBytes()))));

            System.out.println("encryptMD5, " + new String(encryptMD5(target.getBytes())));

            System.out.println("encryptSHA, " + new String(encryptSHA(target.getBytes())));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
