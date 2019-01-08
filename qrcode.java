public class qrcode {
    public int[][] qr, vis;
    public int size, mask = 0, lv;

    qrcode(int level) {
        lv = level;
        size = Templates.l2s(lv);
        qr = new int[size][size];
        vis = new int[size][size];
        k=new pr();
    }

    public void setMask(int mask) {
        this.mask = mask;
    }

    public int[][] getQr() {//add quite zone
        int[][] op = new int[size + 8][size + 8];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++)
                op[4 + i][4 + j] = qr[i][j];
        }
        return op;
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (qr[i][j] == 0) s += "\u25A1";//white
                else s += "\u25A0";//black
            }
            s += "\n";
        }
        return s;
    }

    pr k;

    public class pr{
        public String toString() {
            String s = "";
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (qr[i][j] == 0) s += "\u25A1";//white
                    else s += "\u25A0";//black
                }
                s += "\n";
            }
            s+="\n\n\n";
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (vis[i][j] == 0) s += "\u25A1";//white
                    else s += "\u25A0";//black
                }
                s += "\n";
            }
            return s;
        }
    }

}
