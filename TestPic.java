import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.IOException;

public class TestPic extends Application {

    public void start(Stage stage) throws IOException {

        QRIO q = new QRIO();
        qrcode qr = q.IO(
//                "Added .gitattributes to define how gidlsAdded .gitattributes to define how gidlsAdded .gitattrib" +
//                "utes to define how gidlsAdded .gitattributes to deAdded .gitattributes to define how gidlsAdded .gitattributes to define how gidls" +
//                "utes to define how gidlsAdded .gitattributes to deAdded .gitattributes to define how gidlsAdded .gitattributes to define how gidls" +
//                "utes to define how gidlsAdded .gitattributes to deAdded .gitattributes to define how gidlsAdded .gitattributes to define how gidls" +
//                "utes to define how gidlsAdded .gitattributes to deAdded .gitattributes to define how gidlsAdded .gitattributes to define how gidls" +
//                  "utes to define how fine how gidlsAdded .gitattributes ded .gutes to define how fine how gidlsAdded .gitattributes ded .gitattrutesded .gitattributesded .gitatt.gitatt\n" +
//                        "utes to define how fine how gidlsAdded .gitattributes ded .gitattrutesded .gitattributesded .gitatt.gitattutes to def" +
//                        "ine how fine how gidlsAdded .gitattributes ded .gitattrutesded .gitattributesded .gitatt.gitattutes to define " +
//                        "how fine how gidlsAdded .gitattributes ded .gitattrutesded .gitattributesded .gitatt.gitattutes to def" +
//                        "ine how fine how gidlsAdded .gitattributes ded .gitattrutesded .gitattributesded .gitatt.git" +
//                        "attutes to define how fine how gidlsAdded .gitattributes ded .gitattrutesded .gitattributesd" +
//                        "ed .gitatt.gitattutes to define how fine how gidlsAdded .gitattributes ded .gitattrutesded .git" +
//                        "attributesded .gitatt.gitattutes to define how fine how gidlsAdded .gitattributes ded .gitatt" +
//                        "rutesded .gitattributesded .gitatt.gitattutes to define how fine how gidlsAdded .gitattributes" +
//                          "シングル連絡" +
                        //"abcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdef"
//                "ASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKL"
               "シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡シングル連絡" ,4, 3);///////////////"�ٶ�
        // ��������Ϊ233

        int[][] mode = qr.getQr();


        Image img = new Image("file:.\\11.jpg");///////���ٶ�ͼƬ��
        ImageView imgv = new ImageView(img);
        imgv.setFitHeight(mode[0].length);
        imgv.setFitWidth(mode.length);

        imgv.setX(-100);
        imgv.setY(-70);

        Group gp = new Group(imgv);

        int counter = 0;
        Line ll = new Line();
        for (int i = 0; i < mode[0].length; i++) {///��
            for (int j = 0; j < mode.length; j++) {//��
                if (mode[i][j] == 1) {
                    if (counter == 0) {
                        counter = j;
                    }
                    if (j == mode.length) {
                        ll = new Line(counter, i, j, i);
                        gp.getChildren().add(ll);
                    }
                } else {
                    if (counter != 0) {
                        ll = new Line(counter, i, j - 1, i);
                        gp.getChildren().add(ll);
                        counter = 0;
                    }
                }


            }
        }


        //ͼ��Ŵ󲿷�
        gp.setScaleX(3);
        gp.setScaleY(3);
        gp.setTranslateX(100);
        gp.setTranslateY(100);
        Scene scene = new Scene(gp, 600, 450);


        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
