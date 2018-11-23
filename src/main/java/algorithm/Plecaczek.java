package algorithm;

import values.StaticStuff;

import java.math.BigInteger;
import java.util.BitSet;

public class Plecaczek {

    private BigInteger superArray[]; //Klucz PRYWATNY
    private BigInteger modul;
    private BigInteger W;
    ///////////////////////////////////////////////
    private BigInteger[] A; // KLUCZ PUBLICZNY
    private byte[] msg;
    private byte[][] msg2D;
    private int size;
    private Key key;
    private BigInteger [] msgg;

    public Plecaczek() {
        key = new Key();
        prepareKey();
        System.out.println();
    }

    private void prepareKey() {
        /**
         * key generating //trzeba to będzie potem podzielić na oddzielną metodę generującą się odrazu po odpaleniu w maincontroller
         * a potem otwiera się ten plik z listy i podlicza i zwraca i odpala kolejną funkcje bede iwedzial ocb
         */
        superArray = key.generateSuperArray();
        modul = key.generateModul();
        System.out.println(modul + "modul");
        W = key.generateW(this.modul);
        System.out.println(W + " liczba pierwsza");
        key.generatePIpermutation();//o ch chodzi http://zon8.physd.amu.edu.pl/~miran/lectures/crypto/pkc.pdf
        A = key.conductA(this.W, this.superArray, this.modul);


    }

    public void setMsg(byte[] msg) {
        this.msg = msg;
    }

    public void setSize(int size) {
        this.size = size;
    }


    public void encrypt() {
        this.msg2D = StaticStuff.saveAs2DBytesArray(msg, size);
BigInteger[] results = new BigInteger[size];
        for (int i = 0; i < size; i++) {
            results[i] = conductMsg(msg2D[i], A);
        }
        System.out.println("***********-------------*******************");
        for (BigInteger temp: results) {            System.out.println(temp); }
        System.out.println("***********-------------*******************");
        msgg=results;
    }

    public void decrypt()
    {
        
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
            System.out.println(sum);
        }
        return sum;
    }

}
