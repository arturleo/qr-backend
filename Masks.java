public class Masks {
    final private static int[] formatECl = {1, 0, 3, 2};

    static public int ptmsk(qrcode q,int EC) {
        int maskMode = chmsk(q,EC);
        q.setMask(maskMode);
        changemask(q.qr, q.vis, q.mask, q.size,EC);
        return maskMode;//1 to 8
    }

    static private int chmsk(qrcode q,int EC) {//choose mask
        int la = 1, ct;
        long lap = 1 << 60, ctp = 0;
        for (int i = 1; i <= 8; i++) {
            ct = i;
            ctp = ctPenalty(q, i,EC);
            if (ctp < lap) {
                la = ct;
                lap = ctp;
            }
        }
        return la;
    }

    static private int[][] changemask(int[][] qr, int[][] vis, int p, int sz,int EC) {
        for (int i = 0; i < sz; i++) {
            for (int j = 0; j < sz; j++) {
                if (vis[i][j] == 0 && fill(j, i, p)) {
                    qr[i][j] ^= 1;
                }
            }
        }


        int format = (formatECl[EC-1] << 3) + p - 1;
        String formatInfor = Encode.BCH(format, 0);
        //format information
        qr=fillFormat(qr, formatInfor, sz);

        return qr;
//        qrcode tmp = new qrcode((sz - 17) / 4);
//        tmp.qr = qr;
//        tmp.getQr();
    }

    static private long ctPenalty(qrcode q, int p,int EC) {
        int[][] qr = new int[q.size][], vis = q.vis;
        for(int i=0;i<q.size;i++){
            qr[i]=q.qr[i].clone();
        }//copy the value
        qr=changemask(qr, vis, p, q.size,EC);
        long score = ctN1(qr, q.size)
                + ctN2(qr, q.size)
                + ctN3(qr, q.size)
                + ctN4(qr, q.size);
        return score;
    }

    static private long ctN1(int[][] qr, int sz) {
        long p = 0;
        for (int i = 0; i < sz; i++) {
            int last = -1, cnt = 0, last2 = -1, cnt2 = 0;
            for (int j = 0; j < sz; j++) {
                if (last == qr[i][j]) {
                    cnt++;
                    if (cnt == 5)
                        p += 3;
                    else if (cnt >= 6) p++;
                } else {
                    last = qr[i][j];
                    cnt = 1;
                }
                if (last2 == qr[j][i]) {
                    cnt2++;
                    if (cnt2 == 5)
                        p += 3;
                    else if (cnt2 >= 6) p++;
                } else {
                    last2 = qr[j][i];
                    cnt2 = 1;
                }
            }
        }
        return p;
    }


    static private long ctN2(int[][] qr, int sz) {
        //rather than looking for blocks larger than 2x2,
        // simply add 3 to the penalty score fo every 2x2 block
        long p = 0;
        for (int i = 0; i < sz - 1; i++) {
            for (int j = 0; j < sz - 1; j++) {
                int t = qr[i][j];
                if (qr[i + 1][j] == t && qr[i + 1][j + 1] == t
                        && qr[i][j + 1] == t) {
                    p += 3;
                }

            }
        }
        return p;
    }

    static private long ctN3(int[][] qr, int sz) {
        final String pat1 = "10111010000", pat2 = "00001011101";
        long p = 0;
        for (int i = 0; i < sz ; i++) {
            String a = "", b = "";
            for (int j = 0; j < sz; j++) {
                a += qr[i][j];
                b += qr[j][i];
            }
            int n = 0;
            while ((n = a.indexOf(pat1, n)) != -1) {
                p += 40;
                n++;
            }
            n = 0;
            while ((n = a.indexOf(pat2, n)) != -1) {
                p += 40;
                n++;
            }
            n = 0;
            while ((n = b.indexOf(pat1, n)) != -1) {
                p += 40;
                n++;
            }
            n = 0;
            while ((n = b.indexOf(pat2, n)) != -1) {
                p += 40;
                n++;
            }
        }
        return p;
    }

    static private long ctN4(int[][] qr, int sz) {
        int bl = 0;
        for (int i = 0; i < sz ; i++) {
            for (int j = 0; j < sz ; j++) {
                if (qr[i][j] != 0) bl++;
            }
        }
        long numfive = Math.round((float) bl  * 100 / sz / sz) ;
        long z = Math.max(Math.abs(numfive - 50)/5,0);
        return z;
    }


    static private boolean fill(int i, int j, int pte) {//fill masks
        switch (pte) {
            case 1:
                return (i + j) % 2 == 0;
            case 2:
                return j % 2 == 0;
            case 3:
                return i % 3 == 0;
            case 4:
                return (i + j) % 3 == 0;
            case 5:
                return (j / 2 + i / 3) % 2 == 0;
            case 6:
                return (i * j) % 2 + (i * j) % 3 == 0;
            case 7:
                return ((i * j) % 2 + (i * j) % 3) % 2 == 0;
            case 8:
                return ((i * j) % 3 + (i + j) % 2) % 2 == 0;
        }
        return false;
    }

    private static int[][] fillFormat(int[][] qr, String form, int size) {
        try {
            for (int i = 0; i < 7; i++) {
                if (i < form.length()) {
                    qr[8][i == 6 ? i + 1 : i] = Character.getNumericValue(form.charAt(i));
                    qr[size - 1 - i][8] = Character.getNumericValue(form.charAt(i));
                } else
                    throw new ArrayIndexOutOfBoundsException("Format code too short!");
            }
            for (int i = 7; i < 15; i++) {
                if (i < form.length()) {
                    qr[i < 9 ? 15 - i : 14 - i][8] = Character.getNumericValue(form.charAt(i));
                    qr[8][size - 15 + i] = Character.getNumericValue(form.charAt(i));
                } else {
                    throw new ArrayIndexOutOfBoundsException("Format code too short!");

                }

            }
            if (15 != form.length()) throw new ArrayIndexOutOfBoundsException("Format code too long!");
        } catch (ArrayIndexOutOfBoundsException e1) {
            e1.printStackTrace();
            System.exit(1);
        }
        return qr;
    }

}
