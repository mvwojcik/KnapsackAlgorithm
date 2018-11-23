package algorithm;

import values.StaticStuff;

import java.math.BigInteger;

public class Key {


    public BigInteger[] conductA(BigInteger W,BigInteger[] superArray, BigInteger modul) {
        BigInteger temp[] = new BigInteger[64];
        for (int i = 0; i < 64; i++) {
            temp[i] = (W.multiply(superArray[StaticStuff.Permutation[i]])).mod(modul);
        }
        return temp;
    }

    public BigInteger[] generateSuperArray() {
        BigInteger tab[] = new BigInteger[64];
        BigInteger temp = new BigInteger("2");
        for (int i = 0; i < 64; i++) {
            System.out.println(tab[i] = temp.pow(i));
        }
        return tab;
    }

    public BigInteger generateModul() {
        BigInteger temp = new BigInteger("2");

        return temp.pow(65);//43 randomowo modul musi byc wiekszy od sumy wygenerowanego ciągu superrosnącego
    }

    public BigInteger generateW(BigInteger modul) //nwd(W,M)=1 gdzie M to modul
    {
        return new BigInteger(String.valueOf(modul)).nextProbablePrime();
    }

    public byte[] generatePIpermutation() {
        return StaticStuff.Permutation;
    }
}
