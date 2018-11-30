package algorithm;

import values.StaticStuff;



public class Key {


    public long[] conductA(long W,long[] superArray, long modul) {
        long temp[] = new long[32];
        System.out.println("\n A: ");
        long temporary;
        for (int i = 0; i < 32; i++) {
            temporary = W*(superArray[StaticStuff.Permutation[i]]);

            temp[i] = temporary%modul;
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
        long temp = StaticStuff.nextPrime((long)Math.pow(2,32));

        return temp;//43 randomowo modul musi byc wiekszy od sumy wygenerowanego ciągu superrosnącego
    }

    public long generateW(long modul) //nwd(W,M)=1 gdzie M to modul
    {

        for (int i=10; i<modul;i++)
        {
            if(StaticStuff.gcd(modul,i)==1L)
            {
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
