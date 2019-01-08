/*
use google zxing to decode
 */

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.ImageReader;
import com.google.zxing.common.HybridBinarizer;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

public class Decode {
    public static String getDecodeText(Path file) {
        BufferedImage image;
        try {
            image = ImageReader.readImage(file.toUri());
        } catch (IOException ioe) {
            return ioe.toString();
        }
        LuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result;
        try {
            result = new MultiFormatReader().decode(bitmap);
        } catch (ReaderException re) {
            return re.toString();
        }
        return String.valueOf(result.getText());
    }
}
