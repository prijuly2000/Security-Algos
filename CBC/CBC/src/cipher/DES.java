package cipher;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * DES class extends DESSupport class. DESSupport class provides all the
 * functionality required to encrypt the data. DES class implements encrypt and
 * decrypt functions using the functions provided by DESSupport.
 * 
 * @author Pritesh Gandhi
 * @since 2015-02-19
 * 
 */
public class DES extends BlockCipher64
{
	long key;
	long data;
	long left;
	long right;
	long cd;
	int[][] sBoxes = new int[8][64];

	/**
	 * Getters and setters for all the data members of the class
	 */
	public long getLeft()
	{
		return left;
	}

	public void setLeft(long left)
	{
		this.left = left;
	}

	public long getRight()
	{
		return right;
	}

	public void setRight(long right)
	{
		this.right = right;
	}

	public long getKey()
	{
		return key;
	}

	public void setKey(long key)
	{
		this.key = key;
	}

	public long getData()
	{
		return data;
	}

	public void setData(long data)
	{
		this.data = data;
	}

	/**
	 * Constructor to load the substitutions into the boxes. All the
	 * substitutions comes from the binary file. In the binary file, Each
	 * substitution value is specified using 4 bits. Substitution values are
	 * given in consecutive 4-bit blocks. Each S-box is followed immediately by
	 * the next S-box in the file
	 * 
	 * @param sBoxFileName
	 */
	public DES(String sBoxFileName)
	{
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		DataInputStream dis = null;
		try
		{
			fis = new FileInputStream(sBoxFileName);
			bis = new BufferedInputStream(fis);
			dis = new DataInputStream(bis);
			byte substitution;
			int k = 0, j = 0;
			while (dis.available() > 0)
			{				
				
				if (k == 64)
				{
					k = 0;
					j++;					
				}

				// Reading a byte, means reading 2 substitutions, which is then divided to get two numbers
				substitution = dis.readByte();
				int sub1 = (int) (substitution >> 4);
				if (sub1 < 0)
					sub1 = 16 + sub1;

				int sub2 = (int) (substitution & 15);
				if (sub2 < 0)
					sub2 = 16 + sub2;

				sBoxes[j][k++] = sub1;
				sBoxes[j][k++] = sub2;
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				if (fis != null)
					fis.close();
				if (bis != null)
					bis.close();
				if (dis != null)
					dis.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}

	}

	/**
	 * generateKn function generates the 48 bit key required for each iteration.
	 * The key is generated using data member CD. Both C & D undergoes circular
	 * left shift. The shift is specified by shift variable which is stored in
	 * shift array. After the shift, CD undergoes permutation choice 2 (PC2) to
	 * generate Kn
	 * 
	 * @param shift
	 * @return
	 */
	protected long generateKn(int shift)
	{
		// Circular Shift C
		long tempC = cd >> 28;
		tempC=tempC & 0xfffffffL;
		tempC = (tempC << shift) | (tempC >>> (28 - shift));
		tempC = tempC & 0xfffffffL;

		// Circular Shift D
		long tempD = cd & 0xfffffffL;
		tempD = (tempD << shift) | (tempD >>> (28 - shift));
		tempD = tempD & 0xfffffffL;
		cd = tempC << 28;
		cd = cd ^ tempD;

		int pc2[] = { 
				14, 17, 11, 24, 1, 5, 
				3,  28, 15, 6, 21, 10, 
				23, 19, 12,	4, 26, 8, 
				16, 7, 27, 20, 13, 2, 
				41, 52, 31, 37, 47, 55,
				30, 40,	51, 45, 33, 48,
				44, 49, 39, 56, 34, 53, 
				46, 42, 50, 36, 29, 32 
				};

		long kn = 0;
		long mask = 0;
		for (int i = pc2.length - 1; i >= 0; i--)
		{
			mask = cd >> (56 - pc2[i]);
			mask = (mask & 1L) << (47 - i);
			kn = kn ^ mask;
		}
		return kn;
	}

	/**
	 * generateCAndD function is at the beginning to perform permutation (PC 1).
	 * This generated CD which is required to generate the 48 bit keys. Here the
	 * 64 bit input key is converted to 56 bit key. *
	 * 
	 */
	protected void generateCAndD()
	{
		int pc1[] = { 
				57, 49, 41, 33, 25, 17, 9, 1, 
				58, 50, 42, 34, 26, 18, 10, 2,
				59, 51, 43, 35, 27, 19, 11, 3,
				60, 52, 44, 36, 63, 55, 47,	39,
				31, 23, 15, 7, 62, 54, 46, 38, 
				30, 22, 14, 6, 61, 53, 45, 37,
				29, 21, 13, 5, 28, 20, 12, 4 
				};

		long orgKey = getKey(), mask = 0, tempCD = 0;
		for (int i = pc1.length - 1; i >= 0; i--)
		{
			mask = orgKey >> (64 - pc1[i]);
			mask = (mask & 1L) << (55 - i);
			tempCD = tempCD ^ mask;
		}
		cd = tempCD;
	}

	/**
	 * expandRight function is used to convert the 32 bit right block of the
	 * data to 48 bit block. The permutation required is stored in the array "e"
	 * 
	 * @return
	 */
	protected long expandRight()
	{
		int e[] = { 
				32, 1, 2, 3, 4, 5, 
				4, 5, 6, 7, 8, 9, 
				8, 9, 10, 11, 12, 13,
				12, 13, 14, 15, 16,	17,
				16, 17, 18, 19, 20,	21,
				20, 21, 22, 23,	24,	25,
				24, 25, 26, 27, 28,	29,
				28, 29, 30, 31, 32, 1 
				};

		long orgRight = getRight();
		long expRight = 0;
		long mask = 0;
		for (int i = e.length - 1; i >= 0; i--)
		{
			mask = orgRight >> (e[i] - 1);
			mask = (mask & 1L) << i;
			expRight = expRight ^ mask;
		}
		return expRight;

	}

	/**
	 * divideToLeftRight function is called after performing inverse permutation
	 * (IP). After IP, this function divides the data to Left block & Right
	 * block
	 * 
	 */
	protected void divideToLeftRight()
	{
		left = getData() >> 32;
		left=left & 0xffffffffL;
		right = getData() ^ (left << 32);
	}

	/**
	 * doIP function performs inverse permutation on the given 64 bit data
	 * block. This is the first function to be called in the program. The
	 * permutation is given in "ip" array
	 * 
	 */
	protected void doIP()
	{
		int[] ip = { 
				58, 50, 42, 34, 26, 18, 10, 2, 
				60, 52, 44, 36, 28, 20, 12,	4,
				62, 54, 46, 38, 30, 22, 14, 6,
				64, 56, 48, 40, 32, 24, 16,	8,
				57, 49, 41, 33, 25, 17, 9, 1, 
				59, 51, 43, 35, 27, 19, 11, 3,
				61, 53, 45, 37, 29, 21, 13, 5,
				63, 55, 47, 39, 31, 23, 15, 7 
				};
		
		setData(applyPermutation(getData(),ip));
	}

	/**
	 * doInverseIP function is final function in DES. This function performs
	 * final permutation giving encrypted output or ciphered text.
	 * 
	 */
	protected void doInverseIP()
	{
		int[] ip = { 
				40, 8, 48, 16, 56, 24, 64, 32,
				39, 7, 47, 15, 55, 23, 63, 31,
				38, 6, 46, 14, 54, 22, 62, 30, 
				37, 5, 45, 13, 53, 21, 61, 29,
				36, 4, 44, 12, 52, 20, 60, 28, 
				35, 3, 43, 11, 51, 19, 59, 27,
				34, 2, 42, 10, 50, 18, 58, 26, 
				33, 1, 41, 9, 49, 17, 57, 25 
				};

		
		setData(applyPermutation(getData(), ip));
	}

	/**
	 * functionRK takes sBoxInput which contains XORed 48 bit right block and 48
	 * bit key. Each 6 bit of 48 bit input is given to each S box to produce 32
	 * bit block. This 32 bit block undergoes a permutation generating 32 bit
	 * output.
	 * 
	 * @param sBoxInput
	 * @return
	 */
	protected long functionRK(long sBoxInput)
	{
		long sBoxOutput = 0;
		long sInput = 0;
		long row, col;
		for (int i = sBoxes.length - 1; i >= 0; i--)
		{

			sInput = (sBoxInput >> i * 6) & 0x3fL;
			row = (sInput & 1L) ^ ((sInput >> 4) & 2L);
			col = (sInput >> 1) & 0xfL;
			sBoxOutput = sBoxOutput << 4;
			sBoxOutput = sBoxOutput ^ sBoxes[7 - i][(int) (row * 16 + col)];
		}

		int p[] = { 
				16, 7, 20, 21, 
				29, 12, 28, 17, 
				1, 15, 23, 26, 
				5, 18, 31,10,
				2, 8, 24, 14,
				32, 27, 3, 9,
				19, 13, 30, 6, 
				22, 11, 4, 25 
				};
		
		sBoxOutput = applyPermutation(sBoxOutput, p);
		return sBoxOutput;
	}
	/**
	 * applyPermutation function takes in the input data that is to undergo permutation and the permute array.
	 * Permute array decides how the permutation should take place.
	 *  
	 * @param input
	 * @param permute
	 * @return
	 */
	private long applyPermutation(long input,int permute[])
	{
		long output = 0, mask = 0;
		int length=permute.length;
		for (int i = length - 1; i >= 0; i--)
		{
			mask = input >> (length - permute[i]);
			mask = (mask & 1L) << (length-1- i);
			output = output ^ mask;
		}
		return output;
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
		System.out.println("----------DES------------");
		System.out.println("Encryption Key :" + Long.toHexString(getKey()));
		System.out.println("Plaintext data :" + Long.toHexString(getData()));
		System.out.println("--------------------------");
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

	
}
