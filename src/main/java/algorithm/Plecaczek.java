package algorithm;

import values.StaticStuff;

import java.math.BigInteger;
import java.util.BitSet;

public class Plecaczek {
    private Key key;

    private long superArray[]; //Klucz PRYWATNY
    private long modul;
    private long W;
    ///////////////////////////////////////////////
    private long[] A; // KLUCZ PUBLICZNY

    private byte[] msg;
    private byte[][] msg2D;

    public int size ;
    private long[] msgg;

    public Plecaczek() {
        key = new Key();
        prepareKey();
        System.out.println();
    }

    public byte[][] getMsg2D() {
        return msg2D;
    }

    public long[] getMsgg() {
        return msgg;
    }
    public void setMsgg(String[] tab)
    {
        msgg = new long[tab.length];
        for (int i=0; i<tab.length;i++)
       this.msgg[i]= Long.valueOf(tab[i]);
    }
    public byte [] getMsg()
    {
        return this.msg;
    }

    private void prepareKey() {
        superArray = key.generateSuperArray();
        modul = key.generateModul();
        System.out.println("Modul");
        System.out.println(modul);
        W = key.generateW(modul);
        System.out.println(" liczba pierwsza");
        System.out.println(W);
        key.generatePIpermutation();   //o ch chodzi http://zon8.physd.amu.edu.pl/~miran/lectures/crypto/pkc.pdf
        A = key.conductA(this.W, this.superArray, this.modul);
        }

    public void setMsg(byte[] msg) {
        this.msg = msg;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }

    public void encrypt() {
        this.msg2D = StaticStuff.saveAs2DBytesArray(msg, size);
        StaticStuff.showArrayAsText(msg2D,4,this.size);
        long[] results = new long[size];
        for (int i = 0; i < size; i++) {
            results[i] = conductMsg(msg2D[i], A);
        }
        msgg = results;
        for (long x:msgg
             ) {
            System.out.println(x);
        }
    }

    public void decrypt() {
        System.out.println("DECRYPTING\n");

        byte[][] temp = new byte[size][4];

        for (int i = 0; i < msgg.length; i++) {

            BitSet bitSet = new BitSet(32);
            bitSet.clear();

            long d ;
            d = getD(msgg[i]);
            System.out.println("**********");
            System.out.println(d);
            resolvingKnapsack(d, bitSet);
            temp[i] = bitSet.toByteArray();
            System.out.println("ALE JAZDA: " );
            for (byte xd:temp[i]
                 ) {

                System.out.print(" "+xd);
            }
            temp[i] = PiPermutation(temp, i, bitSet);
            System.out.println("\nALE POJAZDA ");
            for (byte xd:temp[i]
            ) {
                System.out.print(" "+xd);
            }
        }
        StaticStuff.showArrayAsText(temp,4,size);
        this.msg2D=temp;
       this.msg = StaticStuff.saveAs1DBytesArray(this.msg2D,size);
    }


    private long getD(long temp) {
        long temp1;
        System.out.println();

          System.out.println("Getting D:"+ (temp1 = (((BigInteger.valueOf(temp).multiply((BigInteger.valueOf(W).modInverse(BigInteger.valueOf(this.modul))))).mod(BigInteger.valueOf(this.modul))).longValue())));
      //   System.out.println("Getting D:"+ (temp1 = temp*StaticStuff.modInverse(W,this.modul)));

        return temp1;


    }

    private byte[] PiPermutation(byte[][] temp, int i, BitSet bitSet) {
        BitSet bitSet1 = new BitSet(32);
        boolean temp12;
        for (int j=0; j<32;j++)
        {
            temp12 = bitSet.get(StaticStuff.Permutation[j]);
            bitSet1.set(j,temp12);
        }
        bitSet1.set(32,true);
        temp[i]=bitSet1.toByteArray();
        return temp[i];
    }

    private BitSet resolvingKnapsack(long d, BitSet bitSet) {
        long result = 0;

        for (int j = superArray.length -1 ; j >= 0; j--) {
            result +=superArray[j];
            if (result> d) {
                result -= superArray[j];
                bitSet.set(j,false);
            } else {
                bitSet.set(j, true);
            }
            bitSet.set(32,true);
        }
        return bitSet;
    }


    private long conductMsg(byte[] msg, long[] A) {
        BitSet bitSet;
        bitSet = BitSet.valueOf(msg);
        long sum=0;

        boolean temp;
        for (int i = 0; i < 32; i++) {
            temp = bitSet.get(i);
            if (temp == true) {
                sum +=(A[i]);
            }
        }
        return sum;
    }


}
