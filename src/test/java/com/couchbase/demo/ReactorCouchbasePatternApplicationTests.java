package com.couchbase.demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.security.KeyStore;
import java.security.SecureRandom;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.couchbase.client.encryption.KeyStoreKeyring;
import com.couchbase.client.java.Cluster;

@SpringBootTest
class ReactorCouchbasePatternApplicationTests {

	@Autowired
	Cluster cluster;

	@Test
	void contextLoads() throws Exception {
		EasyRandom easyRandom = new EasyRandom();
		Patient p = easyRandom.nextObject(Patient.class);
		assertNotNull(p);
	}

	@Test
	void keyGen() throws Exception {

		KeyStore keyStore = KeyStore.getInstance("JCEKS");
		keyStore.load(null); // initialize new empty key store

		// Generate 64 random bytes
		SecureRandom random = new SecureRandom();
		byte[] keyBytes = new byte[64];
		random.nextBytes(keyBytes);

		// Add a new key called "my-key" to the key store
		KeyStoreKeyring.setSecretKey(keyStore, "my-key", keyBytes,
				"protection-password".toCharArray());

		// Write the key store to disk
		try (OutputStream os = new FileOutputStream("MyKeystoreFile.jceks")) {
			keyStore.store(os, "integrity-password".toCharArray());
		}

	}

}
