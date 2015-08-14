package cipher;

import java.util.Random;



public class CBC implements BlockCipherMode
{

	byte []iv=new byte[8];
	BlockCipher des;
	
	
	public CBC(BlockCipher des)
	{
		this.des=des;
	}
	
	@Override
	public void setIV(byte[] iv)
	{
		this.iv=iv;		
	}

	@Override
	public void randomIV()
	{		
		Random rand=new Random();
		for(int i=0;i<8;i++)
		{
			iv[i]=(byte)rand.nextInt(255);
		}
		
	}

	@Override
	public byte[] getIV()
	{		
		return iv;
	}

	@Override
	public byte[] encrypt(byte[] key, byte[] plaintext)
	{
		System.out.println("---------Encrypt--------");
		byte []ciphertext=null;
		byte []paddedPlaintext=null;
		int outputLength;
		int padBytesNo,padIterator;
		
		// Check input is multiple of 8 bytes
		// If yes then add one block else add the required bytes.
		if(plaintext.length%8==0)
		{
			padBytesNo=8;
			outputLength= plaintext.length+8+padBytesNo;
		}
		else
		{
			padBytesNo=8-plaintext.length%8;
			outputLength=plaintext.length+8+padBytesNo;			
		}
		System.out.println("Pad bytes number:"+padBytesNo);
		System.out.println("After Pad length : "+(plaintext.length+padBytesNo));
		paddedPlaintext=new byte[plaintext.length+padBytesNo];
		ciphertext=new byte[outputLength];
		
		// Copy the input as it is.
		for (padIterator = 0; padIterator < plaintext.length; padIterator++)
		{
			paddedPlaintext[padIterator]=plaintext[padIterator];
		}
		
		// Add one byte of 128 value.
		paddedPlaintext[padIterator++]=(byte)0x80;
		
		// Add 0 value bytes for remaining bytes. 
		while(padIterator<paddedPlaintext.length)
		{
			paddedPlaintext[padIterator++]=(byte)0x00;
		}
		
		// Copy IV to cipher as it is.
		for (int i = 0; i < iv.length; i++)
		{
			ciphertext[i]=iv[i];
		}	
		
		
		byte []currentPlainBlock=new byte[8];
		byte []currentCipherBlock=iv;
		int totalBlocks=paddedPlaintext.length/8;
		int k;
		System.out.println("Total blocks: "+totalBlocks);
		for (int i = 0; i < totalBlocks; i++)
		{
			System.out.println("Encryption iteration:"+i);
			for (int j = i*8,m=0; j < i*8+8; j++)
			{
				currentPlainBlock[m]=(byte)(paddedPlaintext[j] ^ currentCipherBlock[m]);
				m++;
			}
			currentCipherBlock=des.encrypt(key, currentPlainBlock);
			k=i+1;
			
			for (int j = k*8,m=0; j < k*8+8; j++)
			{
				ciphertext[j]=currentCipherBlock[m++];
			}
			
		}
		
		return ciphertext;
	}

	@Override
	public byte[] decrypt(byte[] key, byte[] ciphertext)
	{
		System.out.println("---------Decrypt---------");
		
		// Set IV 
		for (int i = 0; i < iv.length; i++)
		{
			iv[i]= ciphertext[i];
		}
		
		
		byte paddedPlaintext[]=new byte[ciphertext.length-8];
		byte []currentPlainBlock=new byte[8];
		byte []prevCipherBLock=iv;
		byte []currentCipherBlock=new byte[8];
		int totalBlocks=paddedPlaintext.length/8;
		///
		byte []temp=new byte[8];
		//
		int k=8,m=0;
		for (int i = 0; i < totalBlocks; i++)
		{
			System.out.println("\nDecryption iteration"+i);
			for (int j = 0; j < currentCipherBlock.length; j++)
			{
				currentCipherBlock[j]=ciphertext[k++];
			}
			currentPlainBlock=des.decrypt(key, currentCipherBlock);
			for(int j=0;j<currentCipherBlock.length;j++)
			{
				temp[j]=(byte)(currentPlainBlock[j]^prevCipherBLock[j]);
				paddedPlaintext[m++]=(byte)(currentPlainBlock[j]^prevCipherBLock[j]);
			}
			prevCipherBLock=currentCipherBlock;
			//currentCipherBlock=new byte[8];
			//System.out.println("Temp");
			byteArrayToLong(temp);
			
		}
		byteArrayToLong(paddedPlaintext);
		int i;
		for (i = paddedPlaintext.length-1; (paddedPlaintext[i] & 0xff)!=128 ; i--);
		
		System.out.println("\nUnpadded length:"+i);
		byte []plaintext=new byte[i];
		for (int j = 0; j < i; j++)
		{
			plaintext[j]=paddedPlaintext[j];
		}
		System.out.println("This is the plaintext");
		byteArrayToLong(plaintext);
		return plaintext;
	}
	///////////////////////////////////////////
	public byte[] longToByteArray(long lock) {
        byte[] block = new byte[8];
        for(int i = 7; i >= 0; i--) {
            block[i] = (byte)(lock & 0xFFL);
            lock = lock >> 8;
        }
        return block;
    }
	public long byteArrayToLong(byte[] block) {
        long lock = 0L;
        System.out.println("Length:"+(block.length));
        for (int i = 0; i < block.length; i++)
		{
        	
			System.out.print(" "+(block[i] & 0xff));
		}
        System.out.println();
        
        //System.out.println(Long.toHexString(lock));
        return lock;
    }
	/////////////////////////////////////////
	
	public static void main(String[] args)
	{
		DES des=new DES("sboxes_default");
		CBC cbc=new CBC(des);
		//cbc.randomIV();
		byte []iv=new byte[]{0x07, 0x06, 0x05, 0x04, 0x03, 0x02, 0x01, 0x00};
		byte []plaintext=new byte[]{(byte)0x01,(byte)0x23,(byte)0x45,(byte)0x67,(byte)0x89,(byte)0xab,(byte)0xcd,(byte)0xef,(byte)0xff,(byte)0x01,(byte)0x23,(byte)0x45,(byte)0x67,(byte)0x89,(byte)0xab,(byte)0xcd,(byte)0xef,(byte)0xff};
		byte []key=new byte[]{(byte)0x6a,(byte)0x92,(byte)0x6b,(byte)0xfe,(byte)0x2a,(byte)0x29,(byte)0xfa,(byte)0xc3};
		cbc.setIV(iv);
		
		
		
		System.out.println("-----------CBC-----------");
		
		byte []ciphered = cbc.encrypt(key,plaintext);
		
		
		cbc.decrypt(key, ciphered);
		
		
		
	}
	

}
