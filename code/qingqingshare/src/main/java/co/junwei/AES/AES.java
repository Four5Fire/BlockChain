package co.junwei.AES;

import co.junwei.cpabe.Common;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
public class Aes {

    /*
    * seed为生成秘钥的种子
    */
    private static String getRawKey(String seed) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        sr.setSeed(seed.getBytes());
        kgen.init(128, sr); // 长度为128byte
        SecretKey skey = kgen.generateKey();
        byte[] raw = skey.getEncoded();
        // return raw;
        //return Base64Utils.encode(skey.getEncoded());
        return Base64.getEncoder().encodeToString(raw);
    }
    /**
     * 加密
     * @param key
     * @param pubfilePath
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(String key,String pubfilePath )
            throws Exception {
        byte[] plaintext = Common.suckFile(pubfilePath);
        byte[] raw = Base64.getDecoder().decode(key);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(plaintext);
        return encrypted;
    }

    /**
     * 解密
     * @param key
     * @param encfilePath
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(String key,String encfilePath )
            throws Exception {
        byte[] ciphertext = Common.suckFile(encfilePath);
        byte[] raw = Base64.getDecoder().decode(key);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] decrypted = cipher.doFinal(ciphertext);
        return decrypted;
    }


    /**
     * 文件加密
     *
     * @param key
     * @param sourceFilePath
     * @param destFilePath
     * @throws Exception
     */
    public static void encryptFile(String key, String sourceFilePath, String destFilePath) throws Exception {
        File sourceFile = new File(sourceFilePath);
        File destFile = new File(destFilePath);
        if (sourceFile.exists() && sourceFile.isFile()) {
            if (!destFile.getParentFile().exists()) {
                destFile.getParentFile().mkdirs();
            }
            destFile.createNewFile();
            InputStream in = new FileInputStream(sourceFile);
            OutputStream out = new FileOutputStream(destFile);
            byte[] raw = Base64.getDecoder().decode(key);
            SecretKeySpec secretKeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            CipherInputStream cin = new CipherInputStream(in, cipher);
            byte[] cache = new byte[128];
            int nRead = 0;
            while ((nRead = cin.read(cache)) != -1) {
                out.write(cache, 0, nRead);
                out.flush();
            }
            out.close();
            cin.close();
            in.close();
        }
    }


    /**
     * 文件解密
     * @param key
     * @param sourceFilePath
     * @param destFilePath
     * @throws Exception
     */
    public static void decryptFile(String key, String sourceFilePath, String destFilePath) throws Exception {
        File sourceFile = new File(sourceFilePath);
        File destFile = new File(destFilePath);
        if (sourceFile.exists() && sourceFile.isFile()) {
            if (!destFile.getParentFile().exists()) {
                destFile.getParentFile().mkdirs();
            }
            destFile.createNewFile();
            FileInputStream in = new FileInputStream(sourceFile);
            FileOutputStream out = new FileOutputStream(destFile);
            byte[] raw = Base64.getDecoder().decode(key);
            SecretKeySpec secretKeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            CipherOutputStream cout = new CipherOutputStream(out, cipher);
            byte[] cache = new byte[128];
            int nRead = 0;
            while ((nRead = in.read(cache)) != -1) {
                cout.write(cache, 0, nRead);
                cout.flush();
            }
            cout.close();
            out.close();
            in.close();
        }
    }



    public static void main(String[] args) throws Exception {
        String key = getRawKey("hello");
        System.out.println(key);
        encryptFile(key,"D:\\materials\\2019小学期\\BlockChain\\code\\qingqingshare\\src\\main\\java\\co\\junwei\\123.txt","D:\\materials\\2019小学期\\BlockChain\\code\\qingqingshare\\src\\main\\java\\co\\junwei\\456.txt");
        decryptFile(key,"D:\\materials\\2019小学期\\BlockChain\\code\\qingqingshare\\src\\main\\java\\co\\junwei\\456.txt","D:\\materials\\2019小学期\\BlockChain\\code\\qingqingshare\\src\\main\\java\\co\\junwei\\789.txt");

    }
}