package algorithm;

import values.Values;

import java.math.BigInteger;

public class Plecaczek {

    BigInteger superArray[]; //Klucz PRYWATNY
    BigInteger modul;
    BigInteger W;
    ///////////////////////////////////////////////
    BigInteger[] A; // KLUCZ PUBLICZNY

    public Plecaczek() {
        prepareKey();


    }

    private BigInteger[] getPublicKey() {
return this.A;
    }

    private byte[] getPrivateKeyPi() {
return Values.Permutation;
    }

    private BigInteger getPrivateKeyModul() {
return this.modul;
    }

    private BigInteger[] getPrivateKeyB() {
return this.superArray;
    }

    private BigInteger getPrivateKeyW() {
        return this.W;
    }

    private void prepareKey() {
        /**
         * key generating //trzeba to będzie potem podzielić na oddzielną metodę generującą się odrazu po odpaleniu w maincontroller
         * a potem otwiera się ten plik z listy i podlicza i zwraca i odpala kolejną funkcje bede iwedzial ocb
         */
        superArray = new BigInteger[64];
        generateSuperArray();
        modul = generateModul();
        System.out.println(modul + "modul");
        W = generateW();
        System.out.println(W + " liczba pierwsza");
        generatePIpermutation();//o ch chodzi http://zon8.physd.amu.edu.pl/~miran/lectures/crypto/pkc.pdf
        A = conductA();


    }

    private BigInteger[] conductA() {
        BigInteger temp[] = new BigInteger[64];
        for (int i = 0; i < 64; i++) {
            temp[i] = (W.multiply(this.superArray[Values.Permutation[i]])).mod(this.modul);
        }
        return temp;
    }

    private void generateSuperArray() {
        BigInteger temp = new BigInteger("2");
        for (int i = 0; i < 64; i++) {
            System.out.println(superArray[i] = temp.pow(i));
        }
    }

    private BigInteger generateModul() {
        BigInteger temp = new BigInteger("2");

        return temp.pow(65);//43 randomowo modul musi byc wiekszy od sumy wygenerowanego ciągu superrosnącego
    }

    private BigInteger generateW() //nwd(W,M)=1 gdzie M to modul
    {
        return new BigInteger(String.valueOf(modul)).nextProbablePrime();
    }

    private byte[] generatePIpermutation() {
        return Values.Permutation;
    }


}
