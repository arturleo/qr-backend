/*================================================================================================
 **  Reed–Solomon codes for error correcting
 *
 *   This is originally from
 *   https://en.wikiversity.org/wiki/Reed%E2%80%93Solomon_codes_for_coders
 *
 *   Translated from Python and did some improvements
 * ===============================================================================================
 */

import java.util.Arrays;

public class Encode {
    final static int[] generator = {0b10100110111, 0b1111100100101};
    final static int mask=0b101010000010010;

    static int[] exp = new int[512];
    static int[] log = new int[256];

    public static int[] ec(int[] msgIn, int n) {
        table();
        int[] gen = generator(n);
        //division
        int[] msgOut = Arrays.copyOf(msgIn,
                msgIn.length + gen.length-1);
        for (int i = 0; i < msgIn.length; i++) {
            int y = msgOut[i];
            for (int j = 0; j < gen.length; j++) {
                msgOut[i + j] ^= mult(gen[j], y);
            }
        }
        for (int i = 0; i < msgIn.length; i++) {
            msgOut[i] = msgIn[i];
        }
        return msgOut;
    }

    //generate table
    private static void table() {
        int a = 1, prim = 0b100011101;
        for (int i = 0; i < 255; i++) {
            exp[i] = a;
            log[a] = i;
            a <<= 1;
            while ((a & 1 << 8) != 0) a ^= prim;
        }
        //reduce %255
        for (int i = 0; i < 257; i++) {
            exp[255 + i] = exp[i];
        }
    }

    private static int mult(int a, int b) {
        table();
        return a == 0 || b == 0 ? 0 : exp[log[a] + log[b]];
        //0 is not defined in the multiply ring
    }

    private static int[] generator(int num) {
        table();
        int[] g = new int[num + 1];
        g[0] = 1;
        for (int i = 0; i < num; i++) {
            int y = exp[i];
            int[] tm=g.clone();
            for (int j = 0; j <= i; j++) {//todo
                g[j + 1] ^= mult(tm[j], y);
            }
        }
        return g;
    }

    public static String BCH(int msg, int type) {//todo check
        table();
        int g = generator[type];
        msg <<= (10 + type * 2);
        int omsg = msg;
        for (int i = type + 4; i >= 0; i--)
            if ((msg & (1 << (i + 10 + type * 2))) != 0)
                msg ^= g << i;
        int msgO=omsg ^ msg;
        if(type==0)msgO^=mask;
        return QRIO.toByte(msgO, 15 + 3 * type);
    }

}
