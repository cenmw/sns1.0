package com.cenmw.util;

// 32位不可逆加密算法
public class Encryption32 {
	public static String kljm(String dm, String kl)

	{
		String mw, cmm;

		int k, i, a, hf, lf, bytes = 16;

		int mm[], l, rand_seed;

		rand_seed = 12345;

		int len = kl.length();

		if (len >= 12)
			len = 12;

		mw = kl.substring(0, len) + dm.trim();

		l = mw.length();

		if (bytes > 100)
			bytes = 100;

		if (l < bytes)

		{

			for (l = l; l <= bytes; l++)

			{

				rand_seed = (31527 * rand_seed + 3) % 32768;

				a = rand_seed % 256;

				if (a < 32 || a > 127)
					a = ('a');

				mw = mw + (char) (a);

			}

		}

		mm = new int[100];

		for (l = 0; l < 100; l++)

			mm[l] = 0;

		for (l = 0; l < bytes; l++)

		{

			a = mw.charAt(l);

			for (i = 1; i <= 8; i++)

			{

				if (a >= 128)

				{

					a -= 128;

					for (k = 0; k < bytes; k++)

					{

						rand_seed = (31527 * rand_seed + 3) % 32768;

						mm[k] += rand_seed % 256;

					}

				}

				else

				{

					for (k = 1; k <= bytes; k++)

						rand_seed = (31527 * rand_seed + 3) % 32768;

				}

				a *= 2;

			}

		}

		for (k = bytes - 1; k >= 0; k--)

		{

			if (k >= 1)
				mm[k - 1] += mm[k] / 256;

			mm[k] = mm[k] % 256;

		}

		cmm = "";

		for (k = 0; k < bytes; k++)

		{

			hf = mm[k] / 16;

			if (hf < 10)

			{

				cmm = cmm + (char) (hf + (short) ('0'));

			}

			else {

				cmm = cmm + (char) (hf + (short) ('A') - 10);

			}

			lf = mm[k] % 16;

			if (lf < 10)

			{

				cmm = cmm + (char) (lf + (short) ('0'));

			}

			else

			{

				cmm = cmm + (char) (lf + (short) ('A') - 10);

			}

		}

		return cmm;

	}
}
