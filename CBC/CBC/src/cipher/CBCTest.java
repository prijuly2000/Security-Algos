package cipher;

import junit.framework.Assert;

import org.junit.Test;

public class CBCTest
{
	@Test
	public void testCBC() throws Exception
	{
		System.out.println("Running test :");
		byte []iv=new byte[]{0x07, 0x06, 0x05, 0x04, 0x03, 0x02, 0x01, 0x00};
		
		byte []plaintext=new byte[]{(byte)0x01,(byte)0x23,(byte)0x45,(byte)0x67,(byte)0x89,(byte)0xab,(byte)0xcd,(byte)0xef,
									(byte)0xff,(byte)0x01,(byte)0x23,(byte)0x45,(byte)0x67,(byte)0x89,(byte)0xab,(byte)0xcd,
									(byte)0xef,(byte)0xff,(byte)0x01,(byte)0x23,(byte)0x45,(byte)0x67,(byte)0x89,(byte)0xab,
									(byte)0xcd,(byte)0xef,(byte)0xff,(byte)0x01,(byte)0x23,(byte)0x45,(byte)0x67,(byte)0x89,
									(byte)0xab,(byte)0xcd,(byte)0xef,(byte)0xff};
		byte []key=new byte[]{(byte)0x6a,(byte)0x92,(byte)0x6b,(byte)0xfe,(byte)0x2a,(byte)0x29,(byte)0xfa,(byte)0xc3};
		DES des=new DES("sboxes_default");
		CBC cbc=new CBC(des);
		cbc.setIV(iv);
		//cbc.randomIV();
		byte [] ciphertext=cbc.encrypt(key, plaintext);
		
		byte [] decryptedPlaintext=cbc.decrypt(key, ciphertext);
		for (int i = 0; i < plaintext.length; i++)
		{
			Assert.assertEquals(plaintext[i], decryptedPlaintext[i]);
		}
		
	}

}
