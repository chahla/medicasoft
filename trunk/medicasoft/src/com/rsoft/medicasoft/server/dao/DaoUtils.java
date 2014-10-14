package com.rsoft.medicasoft.server.dao;

import com.rsoft.medicasoft.server.security.CryptoTools;
import java.math.BigInteger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DaoUtils {
    public static String securiteKeystore;
    public static String securiteKeystoreAlias;
    public static String securiteKeystorePassword;
    private static CryptoTools rsa;

    
    private static EntityManagerFactory emf;

    private DaoUtils() {
    }
    
    public static CryptoTools getCryptoTools() throws Exception {
        if (rsa == null) {
            rsa = new CryptoTools(securiteKeystore, securiteKeystoreAlias,
                    securiteKeystorePassword);
        }
        return rsa;
    }

    
    public static EntityManagerFactory getEntityManagerFactory(boolean reload) {
        if (reload && emf != null) {
            emf.close();
            emf = null;
        }
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("eventual-reads-short-deadlines");
        }
        return emf;
    }

    
    
    public static String encryptData(String data) {
        if (data == null) {
            return null;
        }
        byte[] cipherPin = rsa.crypt(data);
        return Long.toHexString(new BigInteger(cipherPin).longValue())
                .toUpperCase();
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return getEntityManagerFactory(false);
    }

    public static String buildRowid(String entite) throws Exception {
        long hashCode = 0;
        StringBuilder rowId = new StringBuilder();
        int rand1 = (int) (Math.random() * 1000000);
        int rand2 = (int) (Math.random() * 1000000);
        int rand3 = (int) (Math.random() * 1000000);
        if (entite != null) {
            rowId.append(entite.length() > 10 ? entite.substring(0, 10)
                    : entite);
        }
        rowId.append(Integer.toString(rand1));
        rowId.append(Long.toString(new java.util.Date().getTime()));
        rowId.append(Integer.toString(rand2));
        rowId.append(Long.toString(hashCode));
        rowId.append(Integer.toString(rand3));
        return rowId.toString();
    }
}
