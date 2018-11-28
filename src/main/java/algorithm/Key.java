package algorithm;

import values.StaticStuff;

import java.math.BigInteger;
import java.util.BitSet;

public class Key {


    public long[] conductA(long W,long[] superArray, long modul) {
        long temp[] = new long[32];
        System.out.println("\n A: ");
        long temporary;
        for (int i = 0; i < 32; i++) {
            temporary = W*(superArray[StaticStuff.Permutation[i]]);
            BigInteger bigInteger = new BigInteger(String.valueOf(temporary));
            temp[i] = bigInteger.mod(BigInteger.valueOf(modul)).longValue();
            System.out.print(""+ temp[i] +",");
        }
        return temp;
    }

    public long[] generateSuperArray() {
        long tab[] = new long[32];
        for (int i = 0; i < 32; i++) {
            System.out.print((tab[i] = pow(2,i)) +",");
        }
        return tab;
    }

    public long generateModul() {
        long temp = (BigInteger.valueOf(pow(2,32)).nextProbablePrime()).longValue();

        return temp;//43 randomowo modul musi byc wiekszy od sumy wygenerowanego ciągu superrosnącego
    }

    public long generateW(long modul) //nwd(W,M)=1 gdzie M to modul
    {

        for (int i=10; i<modul;i++)
        {
            BigInteger bigInteger = new BigInteger(""+i);
            if ((bigInteger.gcd(BigInteger.valueOf(modul))).longValue()==1L) {
                return i;
            }
        }
        return 1L;
    }

    public byte[] generatePIpermutation() {
        return StaticStuff.Permutation;
    }
    public static long pow(long x,long z)
    {
        return (long)Math.pow(x,z);
    }
}
