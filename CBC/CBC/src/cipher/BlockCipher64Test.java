package cipher;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Benjamin Carle
 */
public class BlockCipher64Test
{
    @Test
    public void testEncrypt() throws Exception {
        BlockCipher64 des = new DES("sboxes_default");
        byte[] key = { (byte)0x6a, (byte)0x92, (byte)0x6b, (byte)0xfe, (byte)0x2a, (byte)0x29, (byte)0xfa, (byte)0xc3 };
        byte[] plaintext = { (byte)0x01, (byte)0x23, (byte)0x45, (byte)0x67, (byte)0x89, (byte)0xab, (byte)0xcd, (byte)0xef };
        byte[] ciphertext = { (byte)0x8a, (byte)0xb3, (byte)0x2b, (byte)0x42, (byte)0x18, (byte)0x61, (byte)0x8a, (byte)0xf7 };
        byte[] result = des.encrypt(key, plaintext);
        assertArrayEquals("Test Encrypt", ciphertext, result);
    }

    @Test
    public void testDecrypt() throws Exception {
        BlockCipher64 des = new DES("sboxes_default");
        byte[] key = { (byte)0x6a, (byte)0x92, (byte)0x6b, (byte)0xfe, (byte)0x2a, (byte)0x29, (byte)0xfa, (byte)0xc3 };
        byte[] plaintext = { (byte)0x01, (byte)0x23, (byte)0x45, (byte)0x67, (byte)0x89, (byte)0xab, (byte)0xcd, (byte)0xef };
        byte[] ciphertext = { (byte)0x8a, (byte)0xb3, (byte)0x2b, (byte)0x42, (byte)0x18, (byte)0x61, (byte)0x8a, (byte)0xf7 };
        byte[] result = des.decrypt(key, ciphertext);
        assertArrayEquals("Test Decrypt", plaintext, result);
    }
}