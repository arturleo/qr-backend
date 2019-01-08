import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class TestPic2 extends Application {

    public void start(Stage stage) throws IOException {

        QRIO q = new QRIO();
        qrcode qr = q.IO("ASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKL" +
//                "ASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKL" +
              "ASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKL" +
                "ASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKLASDFGHJKL",2, 3);///////////////"�ٶ�

        int[][] mode = qr.getQr();


        Image img = new Image("file:.\\12.jpg");///////���ٶ�ͼƬ��
        ImageView imgv = new ImageView(img);
        imgv.setFitHeight(mode[0].length);
        imgv.setFitWidth(mode.length);

        Group gp = new Group();

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
        gp.setScaleX(10);
        gp.setScaleY(10);
        gp.setTranslateX(150);
        gp.setTranslateY(150);

        Image image = gp.snapshot(null, null);
        File file1 = new File(".\\pic.png");
        if (!file1.exists()) {
            file1.mkdirs();
        }
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file1);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        Scene scene = new Scene(gp, 600, 450);


        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
