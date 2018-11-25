package algorithm;

import values.StaticStuff;

import java.math.BigInteger;
import java.util.BitSet;

public class Plecaczek {
    private Key key;

    private BigInteger superArray[]; //Klucz PRYWATNY
    private BigInteger modul;
    private BigInteger W;
    ///////////////////////////////////////////////
    private BigInteger[] A; // KLUCZ PUBLICZNY

    private byte[] msg;
    private byte[][] msg2D;

    public int size ;
    private BigInteger[] msgg;

    public Plecaczek() {
        key = new Key();
        prepareKey();
        System.out.println();
    }

    public byte[][] getMsg2D() {
        return msg2D;
    }

    public BigInteger[] getMsgg() {
        return msgg;
    }
    public void setMsgg(String[] tab)
    {
        msgg = new BigInteger[tab.length];
        for (int i=0; i<tab.length;i++)
       this.msgg[i]= new BigInteger(tab[i]);
    }
    public byte [] getMsg()
    {
        return this.msg;
    }

    private void prepareKey() {
        /**
         * key generating //trzeba to będzie potem podzielić na oddzielną metodę generującą się odrazu po odpaleniu w maincontroller
         * a potem otwiera się ten plik z listy i podlicza i zwraca i odpala kolejną funkcje bede iwedzial ocb
         */
        superArray = key.generateSuperArray();
        modul = key.generateModul();
        System.out.println("Modul");
        System.out.println(modul);
        W = key.generateW(this.modul);
        System.out.println(" liczba pierwsza");
        System.out.println(W);
        key.generatePIpermutation();//o ch chodzi http://zon8.physd.amu.edu.pl/~miran/lectures/crypto/pkc.pdf
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
        BigInteger[] results = new BigInteger[size];
        for (int i = 0; i < size; i++) {
            results[i] = conductMsg(msg2D[i], A);
        }
        msgg = results;
    }
    public void decrypt() {
        System.out.println("DECRYPTING\n");

        byte[][] temp = new byte[size][8];

        for (int i = 0; i < msgg.length; i++) {

            BitSet bitSet = new BitSet(64);
            bitSet.clear();

            BigInteger d ;
            d = getD(msgg[i]);
            resolvingKnapsack(d, bitSet);

            temp[i] = PiPermutation(temp, i, bitSet);

        }
        StaticStuff.showArrayAsText(temp,8,size);
        this.msg2D=temp;
       this.msg = StaticStuff.saveAs1DBytesArray(this.msg2D,size);
    }

    private BigInteger getD(BigInteger bigInteger) {
        BigInteger d;
        d = (bigInteger.multiply(W.modInverse(this.modul)).mod(this.modul));
        return d;
    }

    private byte[] PiPermutation(byte[][] temp, int i, BitSet bitSet) {
        BitSet bitSet1 = new BitSet(64);
        boolean temp12;
        for (int j=0; j<64;j++)
        {
            temp12 = bitSet.get(StaticStuff.Permutation[j]);
            bitSet1.set(j,temp12);
        }
        bitSet1.set(64,true);
        temp[i]=bitSet1.toByteArray();
        return temp[i];
    }

    private BitSet resolvingKnapsack(BigInteger d, BitSet bitSet) {
        BigInteger result = new BigInteger("0");

        for (int j = superArray.length -1 ; j >= 0; j--) {
            result = result.add(superArray[j]);
            if (result.compareTo(d) == 1) {
                result = result.subtract(superArray[j]);
                bitSet.set(j,false);
            } else {
                bitSet.set(j, true);
            }
            bitSet.set(64,true);
        }
        return bitSet;
    }

    private BigInteger conductMsg(byte[] msg, BigInteger[] A) {
        BitSet bitSet;
        bitSet = BitSet.valueOf(msg);
        BigInteger sum = new BigInteger("0");
        boolean temp;
        for (int i = 0; i < 64; i++) {
            temp = bitSet.get(i);
            if (temp == true) {
                sum = sum.add(A[i]);
            }
        }
        return sum;
    }


}
