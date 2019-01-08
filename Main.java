import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        String s = "abcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdef";//"HELLO WORLD";
        //int[] enc = {32, 91, 11, 120, 209, 114, 220, 77, 67, 64, 236, 17, 236, 17, 236, 17};
        //196  35  39  119  235  215  231  226  93  23
        //00100000 01011011 00001011 01111000 11010001 01110010 11011100 01001101 01000011 01000000 11101100 00010001 11101100 00010001 11101100 00010001
        //{"11101100", "00010001"};
        //int[] out=Encode.ec(enc,10);
        //String o=Encode.BCH(0b000111,1);
        System.out.println(Decode.getDecodeText(Paths.get(".\\pic.png")));
//        qrcode q = QRIO.IO(s, 3, 4);//2 ,3
//        int[][] QR = q.getQr();
//        System.out.print(q);
    }



}


