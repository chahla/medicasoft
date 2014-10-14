/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rsoft.medicasoft.server.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.openssl.PEMReader;

/**
 * 
 * @author Robelkend
 */
public class KeystoreManager {

	public static final String FILE_SEPARATOR = System
			.getProperty("file.separator");
	private static final Pattern P_CN = Pattern.compile("(.*CN=)([^,]*)(.*)");
	private static final Pattern P_OU = Pattern.compile("(.*OU=)([^,]*)(.*)");
	private static final Pattern P_O = Pattern.compile("(.*O=)([^,]*)(.*)");
	private static final Pattern P_L = Pattern.compile("(.*L=)([^,]*)(.*)");
	private static final Pattern P_ST = Pattern.compile("(.*ST=)([^,]*)(.*)");
	private static final Pattern P_C = Pattern.compile("(.*C=)([^,]*)(.*)");
	private static final Pattern P_EMAIL = Pattern
			.compile("(.*EMAIL[^=]*=)([^,]*)(.*)");

	public static KeyStore loadKeyStore(String fileName, String password)
			throws IOException, FileNotFoundException, KeyStoreException,
			NoSuchAlgorithmException, CertificateException {
		// Declaration d'un objet vide de type keystore
		KeyStore keystore = null;
		// Declaration du objet de type fichier se pointant sur le keystore en
		// question
		File fichier = new File(fileName);
		// Chargement du contenu dans l'objet keysore initialement declar?
		if (fichier != null && fichier.exists()) {
			keystore = KeyStore.getInstance(KeyStore.getDefaultType());
			keystore.load(new FileInputStream(fichier), password.toCharArray());
		} else {
			throw new FileNotFoundException("Keystore does not exist");
		}
		return keystore;
	}

	public static X509Certificate signCertificate(KeyStore trustStore,
			String issuerAlias, char[] password, X509Certificate inCert)
			throws KeyStoreException, CertificateEncodingException,
			CertificateParsingException, CertificateException,
			NoSuchAlgorithmException, IOException, UnrecoverableKeyException,
			InvalidKeyException, NoSuchProviderException, SignatureException {
		PrivateKey issuerPrivateKey = (PrivateKey) trustStore.getKey(
				issuerAlias, password);

		if (issuerPrivateKey == null) {
			throw new java.security.InvalidKeyException(
					"Unable to load private key. Check password or certificate alias for the truststore.");
		}
		X509Certificate issuerCert = (X509Certificate) trustStore
				.getCertificate(issuerAlias);
		if (issuerCert == null) {
			throw new java.security.InvalidKeyException(
					"Unable to load issuer certificate authority. Check password or certificate alias for the truststore.");
		}
		// byte[] inCertBytes = inCert.getTBSCertificate();
		// Principal issuer = issuerCert.getSubjectDN();
		// String issuerSigAlg = issuerCert.getSigAlgName();
		// X509CertInfo info = new X509CertInfo(inCertBytes);
		// info.set(X509CertInfo.ISSUER, new CertificateIssuerName((X500Name)
		// issuer));
		// X509CertImpl outCert = new X509CertImpl(info);
		// outCert.sign(issuerPrivateKey, issuerSigAlg);
		// return outCert;
		return null;
	}

	@SuppressWarnings("unused")
	private static BigInteger generateX509SerialNumber() {
		return new BigInteger(Long.toString(System.currentTimeMillis()));
	}

	//

	public static X509Certificate generateCert(KeyStore caks, String caksAlias,
			char[] caksPSWD, String sCommonName, String sOrganisationUnit,
			String sOrganisation, String sLocality, String sState,
			String sCountryCode, String sEmailAddress, int daysValidity,
			PublicKey publicKey, String signatureType, String provider)
			throws CryptoException, KeyStoreException,
			CertificateParsingException, InvalidKeyException,
			NoSuchProviderException, UnrecoverableKeyException,
			NoSuchAlgorithmException {
		if (caks == null) {
			throw new CryptoException(
					"Truststore not found. Please contact your administrator.");
		}
		if (caksAlias == null) {
			MessageFormat form = new MessageFormat(
					"Certificate authority <caksAlias> not found in trustore.");
			Object[] source = { caksAlias };
			throw new CryptoException(form.format(source));
		}
		X509Certificate caksCert = (X509Certificate) caks
				.getCertificate(caksAlias);
		if (caksCert == null) {
			MessageFormat form = new MessageFormat(
					"Certificate authority <caksAlias> not found in trustore.");
			Object[] source = { caksAlias };
			throw new CryptoException(form.format(source));
		}
		PrivateKey issuerPrivateKey = (PrivateKey) caks.getKey(caksAlias,
				caksPSWD);
		if (issuerPrivateKey == null) {
			throw new java.security.InvalidKeyException(
					"Unable to load private key. Invalid trustore  password or certificate. Please call your administrator.");
		}
		// Hashtable attrs = new Hashtable();
		// Vector vOrder = new Vector();
		// if (sCommonName != null) {
		// attrs.put(X509Principal.CN, sCommonName);
		// vOrder.add(0, X509Principal.CN);
		// }
		// if (sOrganisationUnit != null) {
		// attrs.put(X509Principal.OU, sOrganisationUnit);
		// vOrder.add(0, X509Principal.OU);
		// }
		// if (sOrganisation != null) {
		// attrs.put(X509Principal.O, sOrganisation);
		// vOrder.add(0, X509Principal.O);
		// }
		// if (sLocality != null) {
		// attrs.put(X509Principal.L, sLocality);
		// vOrder.add(0, X509Principal.L);
		// }
		// if (sState != null) {
		// attrs.put(X509Principal.ST, sState);
		// vOrder.add(0, X509Principal.ST);
		// }
		// if (sCountryCode != null) {
		// attrs.put(X509Principal.C, sCountryCode);
		// vOrder.add(0, X509Principal.C);
		// }
		// if (sEmailAddress != null) {
		// attrs.put(X509Principal.E, sEmailAddress);
		// vOrder.add(0, X509Principal.E);
		// }
		// X509V3CertificateGenerator certGen = new
		// X509V3CertificateGenerator();
		// certGen.setIssuerDN(new X509Principal(vOrder, attrs));
		// certGen.setNotBefore(new Date(System.currentTimeMillis()));
		// certGen.setNotAfter(new Date(System.currentTimeMillis() + ((long)
		// daysValidity * 24 * 60 * 60 * 1000)));
		// certGen.setSubjectDN(new X509Principal(vOrder, attrs));
		// certGen.setPublicKey(publicKey);
		// certGen.setSignatureAlgorithm(signatureType.toString());
		// certGen.setSerialNumber(generateX509SerialNumber());
		// //CertificateParsingException
		// certGen.addExtension(X509Extensions.AuthorityKeyIdentifier, false,
		// new AuthorityKeyIdentifierStructure(caksCert));
		// certGen.addExtension(X509Extensions.SubjectKeyIdentifier, false, new
		// SubjectKeyIdentifierStructure(publicKey));
		// certGen.addExtension(X509Extensions.BasicConstraints, true, new
		// BasicConstraints(false));
		// certGen.addExtension(X509Extensions.KeyUsage, true, new
		// KeyUsage(KeyUsage.digitalSignature | KeyUsage.keyEncipherment));
		// try {
		// X509Certificate cert = certGen.generate(issuerPrivateKey, provider);
		// return cert;
		// //GeneralSecurityException
		// } catch (Exception ex) {
		// //throw new
		// CryptoException("Une erreur est survenu lors de la cr?ation du certificat");
		// throw new CryptoException(ex.toString());
		// }
		return null;
	}

	private static boolean buildChain(X509Certificate certToVerify,
			Vector<java.security.cert.Certificate> chain,
			Hashtable<Principal, Vector<java.security.cert.Certificate>> certs) {
		Principal subject = certToVerify.getSubjectDN();
		Principal issuer = certToVerify.getIssuerDN();
		if (subject.equals(issuer)) {
			// reached self-signed root cert;
			// no verification needed because it's trusted.
			chain.addElement(certToVerify);
			return true;
		}
		// Get the issuer's certificate(s)
		Vector<java.security.cert.Certificate> vec = certs.get(issuer);
		if (vec == null) {
			return false;
		}
		// Try out each certificate in the vector, until we find one
		// whose public key verifies the signature of the certificate
		// in question.
		for (Enumeration<java.security.cert.Certificate> issuerCerts = vec
				.elements(); issuerCerts.hasMoreElements();) {
			X509Certificate issuerCert = (X509Certificate) issuerCerts
					.nextElement();
			PublicKey issuerPubKey = issuerCert.getPublicKey();
			try {
				certToVerify.verify(issuerPubKey);
			} catch (Exception e) {
				continue;
			}
			if (buildChain(issuerCert, chain, certs)) {
				chain.addElement(certToVerify);
				return true;
			}
		}
		return false;
	}

	private static void keystorecerts2Hashtable(KeyStore ks,
			Hashtable<Principal, Vector<java.security.cert.Certificate>> hash)
			throws Exception {
		for (Enumeration<String> aliases = ks.aliases(); aliases
				.hasMoreElements();) {
			String alias = aliases.nextElement();
			java.security.cert.Certificate cert = ks.getCertificate(alias);
			if (cert != null) {
				Principal subjectDN = ((X509Certificate) cert).getSubjectDN();
				Vector<java.security.cert.Certificate> vec = hash
						.get(subjectDN);
				if (vec == null) {
					vec = new Vector<java.security.cert.Certificate>();
					vec.addElement(cert);
				} else {
					if (!vec.contains(cert)) {
						vec.addElement(cert);
					}
				}
				hash.put(subjectDN, vec);
			}
		}
	}

	@SuppressWarnings("unused")
	private static java.security.cert.Certificate[] establishCertChain(
			KeyStore caks, KeyStore keyStore,
			java.security.cert.Certificate userCert,
			java.security.cert.Certificate certToVerify, boolean trustcacerts)
			throws Exception {
		if (userCert != null) {
			// Make sure that the public key of the certificate reply matches
			// the original public key in the keystore
			PublicKey origPubKey = userCert.getPublicKey();
			PublicKey replyPubKey = certToVerify.getPublicKey();
			if (!origPubKey.equals(replyPubKey)) {
				throw new Exception(
						"Public keys in reply and keystore don't match");
			}
			// If the two certs are identical, we're done: no need to import
			// anything
			if (certToVerify.equals(userCert)) {
				throw new Exception(
						"Certificate reply and certificate in keystore are identical");
			}
		}
		// Build a hash table of all certificates in the keystore.
		// Use the subject distinguished name as the key into the hash table.
		// All certificates associated with the same subject distinguished
		// name are stored in the same hash table entry as a vector.
		Hashtable<Principal, Vector<java.security.cert.Certificate>> certs = null;
		if (keyStore.size() > 0) {
			certs = new Hashtable<Principal, Vector<java.security.cert.Certificate>>(
					11);
			keystorecerts2Hashtable(keyStore, certs);
		}
		if (trustcacerts) {
			if (caks != null && caks.size() > 0) {
				if (certs == null) {
					certs = new Hashtable<Principal, Vector<java.security.cert.Certificate>>(
							11);
				}
				keystorecerts2Hashtable(caks, certs);
			}
		}
		// start building chain
		Vector<java.security.cert.Certificate> chain = new Vector<java.security.cert.Certificate>(
				2);
		if (buildChain((X509Certificate) certToVerify, chain, certs)) {
			java.security.cert.Certificate[] newChain = new java.security.cert.Certificate[chain
					.size()];
			// buildChain() returns chain with self-signed root-cert first and
			// user-cert last, so we need to invert the chain before we store
			// it
			int j = 0;
			for (int i = chain.size() - 1; i >= 0; i--) {
				newChain[j] = chain.elementAt(i);
				j++;
			}
			return newChain;
		} else {
			throw new Exception("Failed to establish chain from reply");
		}
	}

	private static boolean addTrustedCert(KeyStore caks, String trustalias,
			char[] trustpassword, KeyStore keyStore, String alias,
			X509Certificate cert, boolean trustcacerts) throws Exception {
		if (alias == null) {
			throw new Exception("Must specify alias");
		}
		if (keyStore.containsAlias(alias)) {
			MessageFormat form = new MessageFormat(
					"Certificate not imported, alias <alias> already exists");
			Object[] source = { alias };
			throw new Exception(form.format(source));
		}
		// if (keyStore == null) {
		// throw new Exception("Invalid keystore. Null object found.");
		// }
		if (cert == null) {
			throw new Exception("Invalid Certificate. Null object found.");
		}
		// Try to establish trust chain
		X509Certificate certsigned = null;
		try {
			certsigned = signCertificate(caks, trustalias, trustpassword, cert);
			// if (cert == null) {
			// throw new Exception("Error while signing certificate (" + alias +
			// "). Null object found.");
			// }
		} catch (Exception e) {
			throw e;
		}
		try {
			keyStore.setCertificateEntry(alias, certsigned);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	public static KeyPair generateKeyPair(SunProvider prov)
			throws NoSuchAlgorithmException, NoSuchProviderException,
			InvalidParameterException {
		try {
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(
					prov.getSignatureAlgorithm(), prov.getType());
			SecureRandom rand = SecureRandom
					.getInstance(prov.getSecureRandom());
			keyPairGen.initialize(prov.getSize(), rand);
			KeyPair keyPair = keyPairGen.generateKeyPair();
			return keyPair;
		} catch (NoSuchAlgorithmException ex) {
			throw new NoSuchAlgorithmException(
					"Problem when creating the key pair: " + ex.getMessage());
		} catch (InvalidParameterException ex) {
			throw new InvalidParameterException(
					"Invalid parameter submitting for creating key pair: "
							+ ex.getMessage());
		} catch (Exception ex) {
			throw new InvalidParameterException(
					"Unexpected error creating key pair: " + ex.getMessage());
		}
	}

	@SuppressWarnings("unused")
	private static KeyPair readKeyPair(File privateKey, char[] keyPassword)
			throws IOException {
		FileReader fileReader = new FileReader(privateKey);
		// PEMReader r = new PEMReader(fileReader, new
		// DefaultPasswordFinder(keyPassword));
		PEMReader r = new PEMReader(fileReader);
		try {
			return (KeyPair) r.readObject();
		} catch (IOException ex) {
			throw new IOException("The private key could not be decrypted", ex);
		} finally {
			r.close();
			fileReader.close();
		}
	}

	public static String calculateCertFingerPrint(X509Certificate cert,
			SunProvider prov) throws CertificateEncodingException,
			NoSuchAlgorithmException {
		byte[] signature;
		StringBuilder fingerprint = new StringBuilder(256);
		try {
			signature = cert.getEncoded();
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance(prov != null ? prov.getMessageDigest()
							: "SHA1");
			byte[] sha1Fing = md.digest(signature);
			for (int i = 0; i < sha1Fing.length; i++) {
				fingerprint.append(Integer.toString(
						(sha1Fing[i] & 0xff) + 0x100, 16).substring(1));
			}
		} catch (java.security.cert.CertificateEncodingException ex) {
			throw ex;
		} catch (java.security.NoSuchAlgorithmException ex) {
			throw ex;
		}
		return fingerprint.toString().toUpperCase();
	}

	public static String calculateCertFingerPrint(X509Certificate cert)
			throws CertificateEncodingException, NoSuchAlgorithmException {
		return calculateCertFingerPrint(cert, null);
	}

	public static SecureInfo generateLicence(String storeUrlName,
			LicenceInfo licenceInfo, String trustStoreName, String trustAlias,
			String trustStorePassword, String certAlias)
			throws KeyStoreException, IOException, NoSuchAlgorithmException,
			CertificateException, NoSuchProviderException, CryptoException,
			CertificateParsingException, InvalidKeyException,
			UnrecoverableKeyException, Exception {
		SecureInfo secureInfo = null;
		if (licenceInfo != null) {
			// FileOutputStream fos = null;
			FileInputStream r = null;
			try {
				SunProvider prov = SunProvider.getInstance("RSA");
				KeyStore trustedKeystore = KeystoreManager.loadKeyStore(
						trustStoreName, trustStorePassword);
				if (trustedKeystore != null && trustedKeystore.size() > 0) {
					if (trustedKeystore != null) {
						prov.setSignature(((X509Certificate) trustedKeystore
								.getCertificate(trustAlias)).getSigAlgName());
					}
					KeyStore currentKeyStore = KeyStore.getInstance(KeyStore
							.getDefaultType());
					currentKeyStore.load(null,
							licenceInfo.getKeyPassword() != null ? licenceInfo
									.getKeyPassword().toCharArray() : null);
					KeyPair keyPair = generateKeyPair(prov);
					X509Certificate licenceCert = generateCert(trustedKeystore,
							trustAlias, trustStorePassword.toCharArray(),
							licenceInfo.getContactName(),
							Integer.toString(licenceInfo.getNbLicence()),
							licenceInfo.getCompanyName(),
							licenceInfo.getLicenceType(),
							licenceInfo.getPhoneNumbers(),
							licenceInfo.getCountryCode(),
							licenceInfo.getEmail(), licenceInfo.getValidity(),
							keyPair.getPublic(), prov.getSignature(),
							prov.getType());
					addTrustedCert(trustedKeystore, trustAlias,
							trustStorePassword.toCharArray(), currentKeyStore,
							certAlias, licenceCert, true);
					String fingerprint = calculateCertFingerPrint(licenceCert,
							prov);
					Date creationDate = currentKeyStore
							.getCreationDate(certAlias);
					secureInfo = new SecureInfo(fingerprint, creationDate);
					// fos = new FileOutputStream(storeUrlName);
					// currentKeyStore.store(fos, licenceInfo.getKeyPassword()
					// != null ? licenceInfo.getKeyPassword().toCharArray() :
					// null);
				} else {
					throw new Exception(
							"Impossible de charge la cle de securite.");
				}
			} catch (KeyStoreException ex) {
				throw ex;
			} catch (IOException ex) {
				throw ex;
			} catch (NoSuchAlgorithmException ex) {
				throw ex;
			} catch (NoSuchProviderException ex) {
				throw ex;
			} catch (CryptoException ex) {
				throw ex;
			} catch (CertificateParsingException ex) {
				throw ex;
			} catch (UnrecoverableKeyException ex) {
				throw ex;
			} catch (Exception ex) {
				throw ex;
			} finally {
				if (r != null) {
					try {
						r.close();
					} catch (Exception ex) {
					}
				}
				// if (fos != null) {
				// try {
				// fos.close();
				// } catch (Exception ex) {
				// }
				// }
			}
		} else {
			throw new Exception(
					"Informations relatives a la compagnie introuvables");
		}
		return secureInfo;
	}

	public static String getCertificateField(X509Certificate cert, String type)
			throws IllegalArgumentException {
		if (cert != null) {
			// load token attributes
			String subject = cert.getSubjectDN().getName();
			Matcher m = null;
			if ("CN".equalsIgnoreCase(type)) {
				m = P_CN.matcher(subject);
				if (m.matches()) {
					if (m.group(2) != null) {
						return m.group(2).toUpperCase();
					}
				}
			} else if ("OU".equalsIgnoreCase(type)) {
				m = P_OU.matcher(subject);
				if (m.matches()) {
					if (m.group(2) != null) {
						return m.group(2).toUpperCase();
					}
				}
			} else if ("O".equalsIgnoreCase(type)) {
				m = P_O.matcher(subject);
				if (m.matches()) {
					if (m.group(2) != null) {
						return m.group(2).toUpperCase();
					}
				}
			} else if ("L".equalsIgnoreCase(type)) {
				m = P_L.matcher(subject);
				if (m.matches()) {
					if (m.group(2) != null) {
						return m.group(2).toUpperCase();
					}
				}
			} else if ("ST".equalsIgnoreCase(type)) {
				m = P_ST.matcher(subject);
				if (m.matches()) {
					if (m.group(2) != null) {
						return m.group(2).toUpperCase();
					}
				}
			} else if ("C".equalsIgnoreCase(type)) {
				m = P_C.matcher(subject);
				if (m.matches()) {
					if (m.group(2) != null) {
						return m.group(2).toUpperCase();
					}
				}
			} else if ("EMAIL".equalsIgnoreCase(type)) {
				m = P_EMAIL.matcher(subject);
				if (m.matches()) {
					if (m.group(2) != null) {
						return m.group(2).toLowerCase();
					}
				}
			} else {// invalid denomination value
				throw new IllegalArgumentException(
						"Invalid distinguished name denomination field. Field :"
								+ type);
			}
		} else { // cert is null
			throw new IllegalArgumentException("Certificate is null.");
		}
		return null;
	}
}