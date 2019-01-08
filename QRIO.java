public class QRIO {

    final private static int[] RemainderBits = {
            0, 7, 7, 7, 7, 7,
            0, 0, 0, 0, 0, 0, 0,
            3, 3, 3, 3, 3, 3, 3,
            4, 4, 4, 4, 4, 4, 4,
            3, 3, 3, 3, 3, 3, 3,
            0, 0, 0, 0, 0, 0
    };

    public static qrcode IO(String msg, int codemode, int EC) {
        EcdModes enc0 = new EcdModes(msg, codemode, EC);
        int lv = enc0.level;//get encoding size
        //lv=5;EC=3;
       // lv=1;EC=2;
        qrcode qr = new qrcode(lv);

        //reserve blank for format
        //add version information
        Templates.PDP(qr);
        //enc0.code="0100001101010101010001101000011001010111001001100101010111000010011101110011001000000110000100100000011001100111001001101111011011110110010000100000011101110110100001101111001000000111001001100101011000010110110001101100011110010010000001101011011011100110111101110111011100110010000001110111011010000110010101110010011001010010000001101000011010010111001100100000011101000110111101110111011001010110110000100000011010010111001100101110000011101100000100011110110000010001111011000001000111101100";
       //enc0.code="00010000001000000000110001010110011000011000000011101100000100011110110000010001111011000001000111101100000100011110110000010001";
        //enc0.end=10;
        //encode
        int[] msgOut = Block.ECMsg(enc0.code, lv, EC);     //split block and get ec words

        fill(qr, msgOut, RemainderBits[lv - 1]);    //fill

        Masks.ptmsk(qr,EC);//choose and mask//1-8

        return qr;
    }


    private static void fill(qrcode q, int[] msgOut, int rm) {
        String b = "";
        for (int i = 0; i < msgOut.length; i++) {
            b += toByte(msgOut[i], 8);
        }
        for (int i = 0; i < rm; i++) {
            b += "0";
        }
        String z = b;
        int x = q.size - 2, sign = 1;
        while (true) {
            int head = fillColumnByte(q, z, x, sign);
            x -= 2;
            if (x == 5) x--;
            sign ^= 1;
            if (head < z.length())
                z = z.substring(head);
            else break;
            try {
                if (x < 0) throw new ArrayIndexOutOfBoundsException("Code too long!!Something wrong happens.");
            } catch (ArrayIndexOutOfBoundsException e1) {
                // System.out.println(e1.getMessage());
                e1.printStackTrace();
                System.exit(1);
                break;
            }
        }
    }

    private static int fillColumnByte(qrcode q, String b, int x0, int sign) {
        int dir = -2 * sign + 1, y = (q.size - 1) * sign;
        int x = 1;
        int head = 0;
        try {
            while (y != (q.size - 1) * (sign ^ 1) + dir) {
                if (q.vis[y][x0 + x] != 1) {
                    if (head >= b.length()) throw new ArrayIndexOutOfBoundsException("Code too short!");
                    q.qr[y][x0 + x] = Character.getNumericValue(b.charAt(head++));
                }
                if (x == 0) y += dir;
                x ^= 1;
            }
        } catch (ArrayIndexOutOfBoundsException e1) {
            //System.out.println(e1.getMessage());
            e1.printStackTrace();
            System.exit(1);

        }
        return head;
    }


    public static String toByte(int num, int length) {//the same as Ecdmodes
        String s = Integer.toBinaryString(num);
        while (s.length() < length) {
            s = "0" + s;
        }
        return s;
    }
}
