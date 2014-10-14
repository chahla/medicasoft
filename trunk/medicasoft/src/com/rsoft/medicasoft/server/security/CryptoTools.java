/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rsoft.medicasoft.server.security;

/**
 *
 * @author Robelkend
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;

/**
 * Cette classe propose des m�thodes permettant de crypter et d�crypter des
 * messages avec l'algorithme RSA. Le message doit cependant �tre plus petit
 * que KEY_SIZE.
 */
public final class CryptoTools {

    public final static int KEY_SIZE = 2048;  // [512..2048]
    public final static int BLOCK_SIZE = 128;  // [512..2048]
    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;

    public CryptoTools() {
    }

    public CryptoTools(String keyUrl, String alias, String password) throws Exception {
        generateKeyPair(keyUrl, alias, password);
    }

    public RSAPublicKey getPublicKey() {
        return publicKey;
    }

    public byte[] getPublicKeyInBytes() {
        return publicKey.getEncoded();
    }

    public RSAPrivateKey getPrivateKey() {
        return privateKey;
    }

    public byte[] getPrivateKeyInBytes() {
        return privateKey.getEncoded();
    }

    public void setPublicKey(RSAPublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public void setPublicKey(byte[] publicKeyData) throws Exception {
        try {
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyData);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = (RSAPublicKey) keyFactory.generatePublic(publicKeySpec);
        } catch (Exception e) {
            throw e;
        }
    }

    public void setPrivateKey(RSAPrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public void setPrivateKey(byte[] privateKeyData) throws Exception {
        try {
            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyData);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            privateKey = (RSAPrivateKey) keyFactory.generatePrivate(privateKeySpec);

            System.out.println(privateKey);
        } catch (Exception e) {
            throw e;
        }
    }

    public void generateKeyPair() throws Exception {
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
            keyPairGen.initialize(KEY_SIZE, new SecureRandom());
            KeyPair kp = keyPairGen.generateKeyPair();
            publicKey = (RSAPublicKey) kp.getPublic();
            privateKey = (RSAPrivateKey) kp.getPrivate();
        } catch (Exception e) {
            throw e;
        }
    }

    public void generateKeyPair(String keyUrl, String alias, String password) throws Exception {
        try {
            KeyStore keystore = KeystoreManager.loadKeyStore(keyUrl, password);
            Key key = keystore.getKey(alias, password.toCharArray());
            X509Certificate certificate = (X509Certificate) keystore.getCertificate(alias);
            if (key != null) {
                KeyPair kp = new KeyPair(certificate.getPublicKey(), (PrivateKey) key);
                privateKey = (RSAPrivateKey) kp.getPrivate();
                publicKey = (RSAPublicKey) certificate.getPublicKey();
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public byte[] crypt(byte[] plaintext) {
        return crypt(new BigInteger(addOneByte(plaintext))).toByteArray();
    }

    public byte[] crypt(String plaintext) {
        return crypt(plaintext.getBytes());
    }

    public ArrayList<byte[]> encrypt(ArrayList<String> plaintexts) {
        ArrayList<byte[]> encrypted = new ArrayList<byte[]>();
        if (plaintexts != null) {
            for (String plaintext : plaintexts) {
                encrypted.add(crypt(plaintext));
            }
        }
        return encrypted;
    }

    public ArrayList<byte[]> encrypt(String plaintext) {
        ArrayList<byte[]> encrypted = new ArrayList<byte[]>();
        if (plaintext != null && plaintext.length() > 0) {
            ArrayList<String> sequences = new ArrayList<String>();
            int index = 0;
            int lastIndex = 0;
            do {
                index += 127;
                if (plaintext.length() + 1 < index) {
                    index = plaintext.length();
                }
                String v = plaintext.substring(lastIndex, index);
                sequences.add(v);
                lastIndex = index;
            } while (index < plaintext.length());
            for (String text : sequences) {
                encrypted.add(crypt(text));
            }
            encrypted = encrypt(sequences);
        }
        return encrypted;
    }

    public byte[] decryptInBytes(byte[] ciphertext) {
        return removeOneByte(decrypt(new BigInteger(ciphertext)).toByteArray());
    }

    public String decryptInString(byte[] ciphertext) {
        return new String(decryptInBytes(ciphertext));
    }

    private BigInteger crypt(BigInteger plaintext) {
        return plaintext.modPow(publicKey.getPublicExponent(), publicKey.getModulus());
    }

    private BigInteger decrypt(BigInteger ciphertext) {
        return ciphertext.modPow(privateKey.getPrivateExponent(), privateKey.getModulus());
    }

    /**
     * Ajoute un byte de valeur 1 au d�but du message afin d'�viter que ce dernier
     * ne corresponde pas � un nombre n�gatif lorsqu'il sera transform� en
     * BigInteger.
     */
    private static byte[] addOneByte(byte[] input) {
        byte[] result = new byte[input.length + 1];
        result[0] = 1;
        System.arraycopy(input, 0, result, 1, input.length);
        return result;
    }

    /**
     * Retire le byte ajout� par la m�thode addOneByte.
     */
    private static byte[] removeOneByte(byte[] input) {
        byte[] result = new byte[input.length - 1];
        for (int i = 0; i < result.length; i++) {
            result[i] = input[i + 1];
        }
        return result;
    }

    public void persist(ArrayList<byte[]> ciphers, String fileName) throws FileNotFoundException, IOException {
//        if (ciphers != null && ciphers.size() > 0) {
//            FileOutputStream fos = null;
//            try {
//                fos = new FileOutputStream(fileName);
//                for (byte[] cipher : ciphers) {
//                    fos.write(cipher);
//                }
//            } catch (FileNotFoundException ex) {
//                throw ex;
//            } catch (IOException ex) {
//                throw ex;
//            } finally {
//                if (fos != null) {
//                    try {
//                        fos.close();
//                    } catch (Exception ex) {
//                    }
//                }
//            }
//        }
    }

    private ArrayList<byte[]> splitBytes(byte[] ciphertext) {
        ArrayList<byte[]> data = null;
        if(ciphertext != null && BLOCK_SIZE > 0) {
            double cipherSize = (double)ciphertext.length;
            double nbFloat = cipherSize / (double)BLOCK_SIZE;
            BigDecimal b = new BigDecimal(nbFloat);
            int nbInt = b.intValue();
            int position = 0;
            data = new ArrayList<byte[]> (nbInt);
            for(int i = 0; i < nbInt; i++) {
                byte[] dataBlock = new byte[BLOCK_SIZE];
                int k = 0;
                for(int j = position; j < position + BLOCK_SIZE; j++) {
                    dataBlock[k++] = ciphertext[j];
                }
                data.add(dataBlock);
                position += BLOCK_SIZE;
            }
            double qt = b.doubleValue() - (double)nbInt;
            if(qt > 0) {
                int reste = (int)(qt * BLOCK_SIZE);
                byte[] dataBlock = new byte[reste];
                int k = 0;
                for(int j = position; j < position + reste; j++) {
                    dataBlock[k++] = ciphertext[j];
                }
                data.add(dataBlock);
            }
        }
        return data;
    }

    public String retrieveFromFile(File file) throws FileNotFoundException, IOException {
        StringBuilder data = null;
        if (file != null && file.exists()) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                int fileSize = fis.available();
                byte[] ciphertext = new byte[fileSize];
                fis.read(ciphertext);
                ArrayList<byte[]> splitedBytes = splitBytes(ciphertext);
                if(splitedBytes != null && splitedBytes.size() > 0) {
                    data = new StringBuilder();
                    for(byte[] dataByte : splitedBytes) {
                        data.append(decryptInString(dataByte));
                    }
                }
            } catch (FileNotFoundException ex) {
                throw ex;
            } catch (IOException ex) {
                throw ex;
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (Exception ex) {
                    }
                }
            }
        }

        return data != null ? data.toString() : null;
    }
}