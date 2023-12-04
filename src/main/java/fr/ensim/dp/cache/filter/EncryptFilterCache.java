package fr.ensim.dp.cache.filter;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class EncryptFilterCache implements IFilterCache{
    private IFilterCache next;
    private final static int GCM_IV_LENGTH = 12;
    SecretKey secretKey = new SecretKeySpec("test key".getBytes(), "AES");
    @Override
    public byte[] doAdd(String key, byte[] buf) {
        final Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec parameterSpec = new GCMParameterSpec(128, iv); //128 bit auth tag length
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, parameterSpec);
        if (buf != null) {
            cipher.updateAAD(buf);
        }
        byte[] cipherText = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));

        ByteBuffer byteBuffer = ByteBuffer.allocate(iv.length + cipherText.length);
        byteBuffer.put(iv);
        byteBuffer.put(cipherText);

        return byteBuffer;
    }
    @Override
    public byte[] doRetreive(String key, byte[] buf) {
        return new byte[0];
    }
    @Override
    public IFilterCache setNext(IFilterCache next) {
        this.next=next;
        return this.next;
    }
}
