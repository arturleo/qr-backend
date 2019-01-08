import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class EcdModes {//TODO auto detecting mode and mixing modes
    private final int[][] head = {
            {1, 10, 12, 14},//by encoding mode
            {2, 9, 11, 13},
            {4, 8, 16, 16},
            {8, 8, 10, 12}
    }, capacity = {
            //by size level and ecmode
            {19, 16, 13, 9}, {34, 28, 22, 16}, {55, 44, 34, 26}, {80, 64, 48, 36},
            {108, 86, 62, 46}, {136, 108, 76, 60}, {156, 124, 88, 66}, {194, 154, 110, 86},
            {232, 182, 132, 100}, {274, 216, 154, 122}, {324, 254, 180, 140}, {370, 290, 206, 158},
            {428, 334, 244, 180}, {461, 365, 261, 197}, {523, 415, 295, 223}, {589, 453, 325, 253},
            {647, 507, 367, 283}, {721, 563, 397, 313}, {795, 627, 445, 341}, {861, 669, 485, 385},
            {932, 714, 512, 406}, {1006, 782, 568, 442}, {1094, 860, 614, 464}, {1174, 914, 664, 514},
            {1276, 1000, 718, 538}, {1370, 1062, 754, 596}, {1468, 1128, 808, 628}, {1531, 1193, 871, 661},
            {1631, 1267, 911, 701}, {1735, 1373, 985, 745}, {1843, 1455, 1033, 793}, {1955, 1541, 1115, 845},
            {2071, 1631, 1171, 901}, {2191, 1725, 1231, 961}, {2306, 1812, 1286, 986}, {2434, 1914, 1354, 1054},
            {2566, 1992, 1426, 1096}, {2702, 2102, 1502, 1142}, {2812, 2216, 1582, 1222}, {2956, 2334, 1666, 1276}
    };
    private final String[] padByte = {"11101100", "00010001"};
    private final String aln = " $%*+-./:";

    String msg;
    int ec, mode, level;
    String code = "";
    int end = 0;

    EcdModes(String msgIn, int cm, int ecm) {
        msg = msgIn;
        mode = cm;
        ec = ecm;
        encMode();
    }

    private void encMode() {
        String code0 = "";

        // string.codePointCount(0, string.length());
        switch (mode) {
            case 1:
                Numeric();
                break;
            case 2:
                Alphanumeric();
                break;
            case 3:
                Byte();
                break;
            case 4:
                Kanji();
                break;
        }
        chooseLv();
        code0 += toByte(head[mode - 1][0], 4);
        code0 += toByte(msg.length(), head[mode - 1][level >= 27 ? 3 : (level >= 10 ? 2 : 1)]);//TODO length for chinese
        code = code0 + code;
        int lv = level;
        end = code.length();
        //Add a Terminator of 0s
        for (int i = 0; i < 4; i++) {
            if (code.length() < capacity[lv - 1][ec - 1] * 8) code += "0";
        }
        //Add More 0s to Make the Length a Multiple of 8
        while (code.length() % 8 != 0) {
            code += "0";
        }
        //Add Pad Bytes if the String is Still too Short
        int i = 0;
        while (code.length() < capacity[lv - 1][ec - 1] * 8) {
            code += padByte[i];
            i ^= 1;
        }
    }

    private void chooseLv() {//TODO 255
        int lv = 1;
        try {
            while (capacity[lv - 1][ec - 1] * 8 < code.length() + 4 + head[mode - 1][lv >= 27 ? 3 : (lv >= 10 ? 2 : 1)])
                if (lv < 40) lv++;
                else
                    throw new Excep.MsgTooLongException(
                            "The message is too long or error correction level is too high!");
        } catch (Excep.MsgTooLongException e1) {
            System.out.println(e1.getMessage());
            e1.printStackTrace();
        }
        level = lv;
    }

    private void Numeric() {
        try {
            for (int i = 0; i < msg.length(); i += 3) {
                int k = 0;
                for (int g = 0; g < 3 && g + i < msg.length(); g++) {
                    char ch1 = msg.charAt(i + g);
                    if (Character.isDigit(ch1)) {
                        k = k * 10 + Integer.parseInt(ch1 + "");
                    } else throw new Excep.ChooingWrongEncodingException(
                            "You chose the wrong Encoding Mode!It contains '" + (char) ch1 + "' .");
                }
                if (i + 2 < msg.length()) code += toByte(k, 10);
                else if (i + 1 < msg.length()) code += toByte(k, 7);
                else code += toByte(k, 4);
            }
        } catch (Excep.ChooingWrongEncodingException e1) {
            System.out.println(e1.getMessage());
            e1.printStackTrace();
        }
    }

    private void Alphanumeric() {
        try {
            for (int i = 0; i < msg.length(); i += 2) {
                int k = 0;
                for (int g = 0; g < 2 && g + i < msg.length(); g++) {
                    if (g == 1) k *= 45;
                    char ch1 = msg.charAt(i + g);
                    if (Character.isDigit(ch1)) {
                        k += Integer.parseInt(ch1 + "");
                    } else if (Character.isUpperCase(ch1)) {
                        k += ch1 - 'A' + 10;
                    } else if (aln.indexOf(ch1) != -1) {//TODO to test
                        k += aln.indexOf(ch1) + 36;
                    } else throw new Excep.ChooingWrongEncodingException(
                            "You chose the wrong Encoding Mode!It contains '" + (char) ch1 + "' .");
                }
                if (i + 1 < msg.length())
                    code += toByte(k, 11);
                else
                    code += toByte(k, 6);
            }
        } catch (Excep.ChooingWrongEncodingException e1) {
            System.out.println(e1.getMessage());
            e1.printStackTrace();
        }
    }

    private void Byte() {//UTF-8
        // TODO add chinese support//ECI
        byte[] b = msg.getBytes(StandardCharsets.UTF_8);//IF chinese, one will be separated into two to four bytes.
        if (msg.length() < b.length) {

        }
        for (int z : b) {
            z = z & 0xFF;
            code += toByte(z, 8);
        }
    }

    private void Kanji() {//TODO full test needed
        try {
            byte[] b = msg.getBytes("Shift-JIS");
            int k = b.length;
            for (int i = 0; i < k; i += 2) {
                int z = b[i] & 0xFF;
                z = z * 256 + (b[i + 1] & 0xFF);
                if (z >= 0x8140 && z <= 0x9FFC) {
                    z -= 0x8140;
                    int a = z / 256 * 0xC0 + z % 256;
                    code += toByte(a, 13);
                } else if (z >= 0xE040 && z <= 0xEBBF) {
                    z -= 0xC140;
                    int a = z / 256 * 0xC0 + z % 256;
                    code += toByte(a, 13);
                } else throw new Excep.ChooingWrongEncodingException(
                        "You chose the wrong Encoding Mode!It contains '" + (char) z + "' .");
            }
        } catch (Excep.ChooingWrongEncodingException e1) {
            System.out.println(e1.getMessage());
            e1.printStackTrace();
        } catch (UnsupportedEncodingException e2) {
            System.out.println("Wrong Encoding!");
            e2.printStackTrace();
        }
    }


    private String toByte(int num, int length) {
        String s = Integer.toBinaryString(num);
        while (s.length() < length) {
            s = "0" + s;
        }
        return s;
    }
}
