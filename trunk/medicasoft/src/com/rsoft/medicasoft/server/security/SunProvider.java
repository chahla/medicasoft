/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rsoft.medicasoft.server.security;

/**
 *
 * @author Robelkend
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
public class SunProvider {

    private String algorithmParameterGenerator;
    private String algorithmParameters;
    private String certificateFactory;
    private String certPathBuilder;
    private String certPathValidator;
    private String certStore;
    private String configuration;
    private String keyFactory;
    private String keyPairGenerator;
    private String keyStore;
    private String messageDigest;
    private String policy;
    private String secureRandom;
    private String signature;
    private String type;
    private int size;
    private String signatureAlgorithm;

    /**
     * Get the value of signatureAlgorithm
     *
     * @return the value of signatureAlgorithm
     */
    public String getSignatureAlgorithm() {
        return signatureAlgorithm;
    }

    /**
     * Set the value of signatureAlgorithm
     *
     * @param signatureAlgorithm new value of signatureAlgorithm
     */
    public void setSignatureAlgorithm(String signatureAlgorithm) {
        this.signatureAlgorithm = signatureAlgorithm;
    }

    /**
     * Get the value of size
     *
     * @return the value of size
     */
    public int getSize() {
        return size;
    }

    /**
     * Set the value of size
     *
     * @param size new value of size
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Get the value of type
     *
     * @return the value of type
     */
    public String getType() {
        return type;
    }

    /**
     * Set the value of type
     *
     * @param type new value of type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     *
     * @return the value of algorithmParameterGenerator
     */
    public String getAlgorithmParameterGenerator() {
        return algorithmParameterGenerator;
    }

    /**
     *
     *
     * @return the value of algorithmParameters
     */
    public String getAlgorithmParameters() {
        return algorithmParameters;
    }

    /**
     *
     *
     * @return the value of certPathBuilder
     */
    public String getCertPathBuilder() {
        return certPathBuilder;
    }

    /**
     * readonly property
     *
     * @return the value of certPathValidator
     */
    public String getCertPathValidator() {
        return certPathValidator;
    }

    /**
     * readonly property
     *
     * @return the value of certStore
     */
    public String getCertStore() {
        return certStore;
    }

    /**
     * readonly property
     *
     * @return the value of certificateFactory
     */
    public String getCertificateFactory() {
        return certificateFactory;
    }

    /**
     * readonly property
     *
     * @return the value of configuration
     */
    public String getConfiguration() {
        return configuration;
    }

    /**
     * readonly property
     *
     * @return the value of keyFactory
     */
    public String getKeyFactory() {
        return keyFactory;
    }

    /**
     * readonly property
     *
     * @return the value of keyPairGenerator
     */
    public String getKeyPairGenerator() {
        return keyPairGenerator;
    }

    /**
     * readonly property
     *
     * @return the value of keyStore
     */
    public String getKeyStore() {
        return keyStore;
    }

    /**
     * readonly property
     *
     * @return the value of messageDigest
     */
    public String getMessageDigest() {
        return messageDigest;
    }

    /**
     * readonly property
     *
     * @return the value of policy
     */
    public String getPolicy() {
        return policy;
    }

    /**
     * readonly property
     *
     * @return the value of secureRandom
     */
    public String getSecureRandom() {
        return secureRandom;
    }

    /**
     * readonly property
     *
     * @return the value of signature
     */
    public String getSignature() {
        return signature;
    }

    /**
     * Get the an instance of RegistrySunProvider by passing the signature
     * algorithm(RSA or DSA)
     *
     * @return the value of RegistrySunProvider
     */
    public void setAlgorithmParameterGenerator(String algorithmParameterGenerator) {
        this.algorithmParameterGenerator = algorithmParameterGenerator;
    }

    public void setAlgorithmParameters(String algorithmParameters) {
        this.algorithmParameters = algorithmParameters;
    }

    public void setCertPathBuilder(String certPathBuilder) {
        this.certPathBuilder = certPathBuilder;
    }

    public void setCertPathValidator(String certPathValidator) {
        this.certPathValidator = certPathValidator;
    }

    public void setCertStore(String certStore) {
        this.certStore = certStore;
    }

    public void setCertificateFactory(String certificateFactory) {
        this.certificateFactory = certificateFactory;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public void setKeyFactory(String keyFactory) {
        this.keyFactory = keyFactory;
    }

    public void setKeyPairGenerator(String keyPairGenerator) {
        this.keyPairGenerator = keyPairGenerator;
    }

    public void setKeyStore(String keyStore) {
        this.keyStore = keyStore;
    }

    public void setMessageDigest(String messageDigest) {
        this.messageDigest = messageDigest;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public void setSecureRandom(String secureRandom) {
        this.secureRandom = secureRandom;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public static SunProvider getInstance(String sigAlg) throws IllegalArgumentException {
        SunProvider regProv = null;
//        if ("DSA".equalsIgnoreCase(sigAlg)) {
//            sun.security.provider.Sun provider = new sun.security.provider.Sun();
//            regProv = new SunProvider();
//            regProv.setSignatureAlgorithm(sigAlg);
//            regProv.setType("SUN");
//            regProv.setAlgorithmParameterGenerator(provider.getProperty("Alg.Alias.KeyPairGenerator.1.3.14.3.2.12"));
//            regProv.setAlgorithmParameters(provider.getProperty("Alg.Alias.AlgorithmParameters.1.3.14.3.2.12"));//Alg.Alias.AlgorithmParameters.1.3.14.3.2.12
//            regProv.setCertificateFactory(provider.getProperty("Alg.Alias.CertificateFactory.X509"));//Alg.Alias.CertificateFactory.X509
//            regProv.setCertPathBuilder("PRIX");
//            regProv.setCertPathValidator("PRIX");
//            regProv.setCertStore("Collection");
//            regProv.setConfiguration("JavaLoginConfig ");
//            regProv.setKeyFactory(provider.getProperty("Alg.Alias.KeyFactory.1.3.14.3.2.12"));//Alg.Alias.KeyFactory.1.3.14.3.2.12
//            regProv.setKeyPairGenerator(provider.getProperty("Alg.Alias.KeyPairGenerator.1.3.14.3.2.12"));//Alg.Alias.KeyPairGenerator.1.3.14.3.2.12
//            regProv.setKeyStore("JKS");//KeyStore.JKS
//            regProv.setMessageDigest("SHA1");
//            regProv.setPolicy("JavaPolicy");
//            regProv.setSecureRandom("SHA1PRNG");
//            regProv.setSize(1024);
//        } else if ("RSA".equalsIgnoreCase(sigAlg)) {
//            sun.security.rsa.SunRsaSign provider = new sun.security.rsa.SunRsaSign();
//            regProv = new SunProvider();
//            regProv.setType("SunRsaSign");
//            regProv.setSignatureAlgorithm(sigAlg);
//            regProv.setAlgorithmParameterGenerator(provider.getProperty("Alg.Alias.KeyPairGenerator.1.2.840.113549.1.1"));
//            regProv.setKeyFactory(provider.getProperty("Alg.Alias.KeyFactory.OID.1.2.840.113549.1.1"));//Alg.Alias.KeyFactory.1.3.14.3.2.12
//            regProv.setKeyPairGenerator(provider.getProperty("Alg.Alias.KeyPairGenerator.1.2.840.113549.1.1"));//Alg.Alias.KeyPairGenerator.1.3.14.3.2.12
//            regProv.setKeyStore("JKS");//KeyStore.JKS
//            regProv.setMessageDigest("SHA1");
//            regProv.setSecureRandom("SHA1PRNG");
//            regProv.setSize(2048);
//        } else {
//            throw new IllegalArgumentException("Invalid provider Algorithm Parameter Generator Name(" + sigAlg + ")");
//        }
//        return regProv;
		return regProv;
    }
}