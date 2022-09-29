package com.couchbase.demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

import com.couchbase.client.core.encryption.CryptoManager;
import com.couchbase.client.core.error.CouchbaseException;
import com.couchbase.client.encryption.AeadAes256CbcHmacSha512Provider;
import com.couchbase.client.encryption.DefaultCryptoManager;
import com.couchbase.client.encryption.KeyStoreKeyring;
import com.couchbase.client.encryption.Keyring;
import com.couchbase.client.java.codec.JacksonJsonSerializer;
import com.couchbase.client.java.encryption.databind.jackson.EncryptionModule;
import com.couchbase.client.java.env.ClusterEnvironment;
import com.couchbase.client.java.json.JsonValueModule;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableCouchbaseRepositories(basePackages = { "com.couchbase.demo" })
public class MyCouchbaseConfig extends AbstractCouchbaseConfiguration {

    @Override
    public String getConnectionString() {
        return "couchbase://127.0.0.1";
    }

    @Override
    public String getUserName() {
        return "Administrator";
    }

    @Override
    public String getPassword() {
        return "Administrator";
    }

    @Override
    public String getBucketName() {
        return "default";
    }

    protected boolean autoIndexCreation() {
        return true;
    }

    @Bean(destroyMethod = "shutdown")
    public ClusterEnvironment couchbaseClusterEnvironment() {
        Keyring keyring = null;
        KeyStore javaKeyStore;
        CryptoManager cryptoManager;
        try {
            javaKeyStore = KeyStore.getInstance("JCEKS");
            FileInputStream fis = new java.io.FileInputStream(
                    "/workspace/couchbase-spring-gitpod-playground/MyKeystoreFile.jceks");
            javaKeyStore.load(fis, "integrity-password".toCharArray());
            keyring = new KeyStoreKeyring(javaKeyStore, keyName -> "protection-password");
            // AES-256 authenticated with HMAC SHA-512. Requires a 64-byte key.
            AeadAes256CbcHmacSha512Provider provider = AeadAes256CbcHmacSha512Provider.builder().keyring(keyring)
                    .build();
            cryptoManager = DefaultCryptoManager.builder().decrypter(provider.decrypter())
                    .defaultEncrypter(provider.encrypterForKey("my-key")).build();
        } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
            throw new RuntimeException(e);
        }

        ClusterEnvironment.Builder builder = ClusterEnvironment.builder();
        builder.cryptoManager(cryptoManager);
        if (!nonShadowedJacksonPresent()) {
            throw new CouchbaseException("non-shadowed Jackson not present");
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new EncryptionModule(cryptoManager));
        mapper.registerModule(new JsonValueModule());
        builder.jsonSerializer(JacksonJsonSerializer.create(mapper));
        configureEnvironment(builder);
        return builder.build();
    }

    private boolean nonShadowedJacksonPresent() {
        try {
            JacksonJsonSerializer.preflightCheck();
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

}