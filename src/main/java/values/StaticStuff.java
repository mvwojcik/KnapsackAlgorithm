package values;

import algorithm.Plecaczek;
import com.sun.org.apache.xerces.internal.xs.StringList;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public final class StaticStuff {
    public static byte[] Permutation = {
            7, 12, 8, 54, 42, 11, 63, 47,
            61, 5, 19, 23, 52, 33, 41, 13,
            28, 43, 55, 62, 17, 2, 14, 46,
            21, 1, 45, 10, 37, 15, 32, 39,
            3, 16, 31, 34, 40, 50, 60, 4,
            6, 9, 18, 20, 22, 51, 57, 27,
            24, 56, 26, 0, 30, 59, 48, 38,
            44, 36, 49, 53, 25, 58, 35, 29};

    public static byte[][] saveAs2DBytesArray(byte[] fileContent, int size) {
        byte[][] temp = new byte[size][8];
        int temp1 = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < 8; j++) {
                if (temp1 == fileContent.length) {
                    temp[i][j] = 0;
                } else {
                    temp[i][j] = fileContent[temp1];
                    temp1++;
                }
            }
        }
        return temp;
    }

    public static void showArray(byte tab[][], int param, int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < param; j++) {
                System.out.print(" " + tab[i][j]);
            }
            System.out.println();
        }
    }

    public static void showArrayAsText(byte tab[][], int param, int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < param; j++) {
                System.out.print(" " + (char) tab[i][j]);
            }
            System.out.println();
        }
    }

    public static void saveStringToFile(int size, BigInteger[] tab,String extension) {
        String[] tabString = new String[tab.length];

        try {

            BufferedWriter fw = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "\\test\\encrypted."+extension));
            fw.write(String.valueOf(size));
            fw.newLine();
            for (int i = 0; i < tab.length; i++) {
                tabString[i] = tab[i].toString();
                fw.write(tabString[i]);
                fw.newLine();
            }
            fw.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }


    public static String[] saveFileToString(Plecaczek plecaczek,String extension) {
        List<String> strings = new LinkedList<>();
        try {
            BufferedReader fr = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\test\\encrypted."+extension));

            plecaczek.setSize(Integer.parseInt(fr.readLine()));
            String temp = fr.readLine();
            while (!temp.isEmpty()) {
                strings.add(temp);
                temp = fr.readLine();
            }

        } catch (Exception e) {
            e.getMessage();
        }
        String [] temp = new String[strings.size()];
        strings.toArray(temp);
    return temp;
    }

    public static void saveBytestoFile(Plecaczek plecaczek,String extension)
    {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(System.getProperty("user.dir")+"\\test\\decrypted."+extension));
            fileOutputStream.write(plecaczek.getMsg());
        }
        catch(Exception ex)
        {
            ex.getMessage();
        }
    }

    public static byte[] saveAs1DBytesArray(byte[][] outputContent, int size) {
        byte[] temp = new byte[size * 8];
        int licznik = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < 8; j++) {
                temp[licznik] = outputContent[i][j];
                licznik++;
            }
        }
        return temp;
    }

}
