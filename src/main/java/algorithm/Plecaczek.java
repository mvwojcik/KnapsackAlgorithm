package algorithm;

import java.math.BigInteger;

public class Plecaczek {
    long superArray[];
    long modul;
    byte [] pi;
    BigInteger W;
public Plecaczek()
{
    /**
     * key generating //trzeba to będzie potem podzielić na oddzielną metodę generującą się odrazu po odpaleniu w maincontroller
     * a potem otwiera się ten plik z listy i podlicza i zwraca i odpala kolejną funkcje bede iwedzial ocb
     */
    superArray = new long[100];
    generateSuperArray();
    modul = generateModul();
    System.out.println(modul + "modul");
    W = generateW();
    System.out.println(W + " liczba pierwsza");
    generatePIpermutation();//o ch chodzi http://zon8.physd.amu.edu.pl/~miran/lectures/crypto/pkc.pdf
    



}
    private void generateSuperArray()
    {
        for (int i=0; i<50; i++)
        {
            System.out.println(superArray[i]=(long)Math.pow(2,i));
        }
    }
    private long generateModul()
    {
        return modul=(long)Math.pow(2,51)+43;//43 randomowo modul musi byc wiekszy od sumy wygenerowanego ciągu superrosnącego
    }
    private BigInteger generateW() //nwd(W,M)=1 gdzie M to modul
    {
        return new BigInteger(String.valueOf(modul)).nextProbablePrime();
    }
    private int [] generatePIpermutation()
    {
        int[]tab=new int[234567];
        return tab;
    }


}
