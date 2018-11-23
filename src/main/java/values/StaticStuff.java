package values;

public final class StaticStuff {
    public static byte[] Permutation = {
            7,12,8,54,42,11,63,47,
            61,5,19,23,52,33,41,13,
            28,43,55,62,17,2,14,46,
            21,1,45,10,37,15,32,39,
            3,16,31,34,40,50,60,4,
            6,9,18,20,22,51,57,27,
            24,56,26,0,30,59,48,38,
            44,36,49,53,25,58,35,29};

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

}
