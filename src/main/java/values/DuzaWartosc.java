package values;

import java.math.BigInteger;


public class DuzaWartosc  {


    public static long modInverse(long x, long y)
    {
        BigInteger bigInteger = new BigInteger(String.valueOf(x));
        BigInteger bigInteger1 = new BigInteger(String.valueOf(y));

        return  bigInteger.modInverse(bigInteger1).longValue();
    }

}
