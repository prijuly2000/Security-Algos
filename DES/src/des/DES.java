package des;

/**
 * DES class extends DESSupport class. DESSupport class provides all the
 * functionality required to encrypt the data. DES class implements encrypt and
 * decrypt functions using the functions provided by DESSupport.
 * 
 * @author Pritesh Gandhi
 * @since 2015-02-19
 * 
 */
public class DES extends DESSupport
{

	DES(String sBoxFileName)
	{
		super(sBoxFileName);
	}

	/**
	 * encrypt functions takes key and block (plain text) as input. It operated
	 * on the given data using different functions provided by DESSupport. The
	 * final output is the encrypted text or cipher text. *
	 * 
	 * @param key
	 * @param block
	 * @return
	 */
	public long encrypt(long key, long block)
	{
		setKey(key);
		setData(block);
		System.out.println("Encryption Key :" + Long.toHexString(getKey()));
		System.out.println("Plaintext data :" + Long.toHexString(getData()));
		doIP();
		divideToLeftRight();
		generateCAndD();

		int shifts[] = { 1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1 };
		long kn = 0;
		long sBoxInput = 0, sBoxOutput = 0, oldLeft, expRight = 0;

		for (int i = 0; i < shifts.length; i++)
		{
			expRight = expandRight();
			kn = generateKn(shifts[i]);
			sBoxInput = expRight ^ kn;
			sBoxOutput = functionRK(sBoxInput);
			oldLeft = getLeft();
			setLeft(getRight());
			setRight(sBoxOutput ^ oldLeft);
		}
		setData((getRight() << 32) ^ getLeft());
		doInverseIP();
		return getData();
	}

	/**
	 * decrypt function takes key & block (encrypted or ciphered text) as input.
	 * This functions operates in the same way as encrypt except the key
	 * scheduling takes place in reverse order i.e. last 48 bit key (K16) is
	 * applied first and first 48 bit key (K1) is applied at the end. The final
	 * output of the function is the plain text.
	 * 
	 * @param key
	 * @param block
	 * @return
	 */
	public long decrypt(long key, long block)
	{
		setKey(key);
		setData(block);
		doIP();
		divideToLeftRight();
		generateCAndD();

		int shifts[] = { 1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1 };
		long kn[] = new long[16];
		long sBoxInput = 0, sBoxOutput = 0, oldLeft, expRight = 0;
		for (int i = 0; i < shifts.length; i++)
		{
			kn[i] = generateKn(shifts[i]);
		}

		for (int i = kn.length - 1; i >= 0; i--)
		{
			expRight = expandRight();
			sBoxInput = expRight ^ kn[i];
			sBoxOutput = functionRK(sBoxInput);
			oldLeft = getLeft();
			setLeft(getRight());
			setRight(sBoxOutput ^ oldLeft);
		}
		setData((getRight() << 32) ^ getLeft());
		doInverseIP();
		return getData();
	}

	public static void main(String[] args)
	{
		DES des = new DES(args[0]);
		long encrypted = des.encrypt(0x85bc7c34eb750ec5L, 0x0ed9e287fd025012L);
		System.out.println("Encrypted data :" + Long.toHexString(encrypted));

		long decrypted = des.decrypt(0x85bc7c34eb750ec5L, encrypted);
		System.out.println("Decrypted data :" + Long.toHexString(decrypted));

	}

}
