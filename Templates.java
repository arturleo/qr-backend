/*=============================================================
 **
 *  A static class aiming at creating templates
 *
 * ==============================================================
 */

public class Templates {
    final static int[][] algm = {//alignment position
            {},
            {6, 18},
            {6, 22},
            {6, 26},
            {6, 30},
            {6, 34},
            {6, 22, 38},
            {6, 24, 42},
            {6, 26, 46},
            {6, 28, 50},
            {6, 30, 54},
            {6, 32, 58},
            {6, 34, 62},
            {6, 26, 46, 66},
            {6, 26, 48, 70},
            {6, 26, 50, 74},
            {6, 30, 54, 78},
            {6, 30, 56, 82},
            {6, 30, 58, 86},
            {6, 34, 62, 90},
            {6, 28, 50, 72, 94},
            {6, 26, 50, 74, 98},
            {6, 30, 54, 78, 102},
            {6, 28, 54, 80, 106},
            {6, 32, 58, 84, 110},
            {6, 30, 58, 86, 114},
            {6, 34, 62, 90, 118},
            {6, 26, 50, 74, 98, 122},
            {6, 30, 54, 78, 102, 126},
            {6, 26, 52, 78, 104, 130},
            {6, 30, 56, 82, 108, 134},
            {6, 34, 60, 86, 112, 138},
            {6, 30, 58, 86, 114, 142},
            {6, 34, 62, 90, 118, 146},
            {6, 30, 54, 78, 102, 126, 150},
            {6, 24, 50, 76, 102, 128, 154},
            {6, 28, 54, 80, 106, 132, 158},
            {6, 32, 58, 84, 110, 136, 162},
            {6, 26, 54, 82, 110, 138, 166},
            {6, 30, 58, 86, 114, 142, 170}
    };



    Templates() {
    }

    public static int l2s(int lv) {
        return 17 + lv * 4;
    }

    public static qrcode PDP(qrcode qr) {//position detecting pattern

        PDPsub(qr, 0);
        PDPsub(qr, 1);
        PDPsub(qr, 2);

        int ver = qr.lv;
        for (int i = 0; i < algm[ver - 1].length; i++) {
            for (int j = 0; j < algm[ver - 1].length; j++) {
                alignment(
                        qr,
                        algm[ver - 1][i],
                        algm[ver - 1][j]
                );
            }
        }

        timing(qr);

        if (qr.lv >= 7) {
            version(qr);
        }
        return qr;
    }

    static private void version(qrcode q) {
        try {
            int sz = q.size;
            String ver = Encode.BCH(q.lv, 1);
            int c = 0;

            for (int j = 0; j < 6; j++) {
                for (int i = 0; i < 3; i++) {
                    if (ver.length() <= c) throw new ArrayIndexOutOfBoundsException("Version code too short!");
                    q.qr[j][sz - 11 + i] = Character.getNumericValue(ver.charAt(17-c));
                    q.vis[j][sz - 11 + i] = 1;
                    q.qr[sz - 11 + i][j] = Character.getNumericValue(ver.charAt(17-c));
                    q.vis[sz - 11 + i][j] = 1;
                    c++;
                }
            }
            if (c != ver.length()) throw new ArrayIndexOutOfBoundsException("Version code too long!");
        } catch (ArrayIndexOutOfBoundsException e1) {
            System.out.println(e1.getMessage());
            e1.printStackTrace();
        }


    }

    static private void timing(qrcode q) {
        int sz = q.size;
        for (int i = 8; i < sz - 8; i++) {
            q.qr[6][i] = (i % 2) ^ 1;
            q.vis[6][i] = 1;
        }
        for (int i = 8; i < sz - 8; i++) {
            q.qr[i][6] = (i % 2) ^ 1;
            q.vis[i][6] = 1;
        }
        q.qr[sz - 8][8] = 1;
        q.vis[sz - 8][8] = 1;
    }

    static private void alignment(qrcode q, int x, int y) {
        int sign = 0;
        for (int j = 0; j < 5; j++) {//find if blank
            for (int i = 0; i < 5; i++) {
                if (q.vis[x - 2 + i][y - 2 + j] == 1) {
                    sign = 1;
                    break;
                }
            }
        }
        if (sign == 1) return;
        for (int i = 0; i < 5; i++) {
            q.qr[x - 2][y - 2 + i] = 1;
            q.qr[x + 2][y - 2 + i] = 1;
        }
        for (int i = 0; i < 5; i++) {
            q.qr[x - 2 + i][y - 2] = 1;
            q.qr[x - 2 + i][y + 2] = 1;
        }
        q.qr[x][y] = 1;
        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < 5; i++) {
                q.vis[x - 2 + i][y - 2 + j] = 1;
            }
        }

    }

    static private void PDPsub(qrcode qr, int p) {
        int sz = qr.size;
        final int[][] pz = {{0, 0, 0, 0, 9, 9}, {0, sz - 8, 0, sz - 7, 9, 8}, {sz - 8, 0, sz - 7, 0, 8, 9}};
        for (int j = 0; j < pz[p][4]; j++) {
            for (int i = 0; i < pz[p][5]; i++) {//reserve format
                qr.vis[j + pz[p][0]][i + pz[p][1]] = 1;
            }
        }
        for (int i = 0; i < 7; i++) {
            qr.qr[pz[p][2]][i + pz[p][3]] = 1;
            qr.qr[6 + pz[p][2]][i + pz[p][3]] = 1;
        }
        for (int i = 0; i < 7; i++) {
            qr.qr[i + pz[p][2]][pz[p][3]] = 1;
            qr.qr[i + pz[p][2]][6 + pz[p][3]] = 1;
        }
        for (int i = 0; i < 9; i++) {
            qr.qr[i % 3 + pz[p][2] + 2][i / 3 + pz[p][3] + 2] = 1;
        }
    }

}
