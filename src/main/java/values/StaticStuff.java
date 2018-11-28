package values;

import algorithm.Plecaczek;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public final class StaticStuff {
    public static byte[] Permutation = {
            7, 12, 8, 21, 17, 11, 4, 0,
            30, 5, 19, 23, 16, 31, 28, 13,
            27, 25, 3, 1, 15, 2, 14, 6,
            22, 24, 9, 10, 20,26 , 29, 18};

    public static byte[][] saveAs2DBytesArray(byte[] fileContent, int size) {
        byte[][] temp = new byte[size][4];
        int temp1 = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < 4; j++) {
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

    public static void saveStringToFile(int size, long[] tab,String extension) {
        String[] tabString = new String[tab.length];

        try {

            BufferedWriter fw = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "\\test\\encrypted."+extension));
            fw.write(String.valueOf(size));
            fw.newLine();
            for (int i = 0; i < tab.length; i++) {
                tabString[i] = Long.toString(tab[i]);
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
        byte[] temp = new byte[size * 4];
        int licznik = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < 4; j++) {
                temp[licznik] = outputContent[i][j];
                licznik++;
            }
        }
        return temp;
    }

}
