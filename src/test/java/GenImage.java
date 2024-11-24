import dev.lunasa.gradly.Gradly;
import dev.lunasa.gradly.format.ImageFormat;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.util.List;

public class GenImage {
    public static void main(String[] args) {
        List<Integer> colors = List.of(
                new Color(100, 100, 255).getRGB(),
                new Color(30, 70, 110).getRGB(),
                new Color(90, 64, 200).getRGB(),
                new Color(30, 23, 150).getRGB()
        );

        var image = Gradly.createGradientWithNoise(
                90f,
                90f,
                colors,
                ImageFormat.PNG,
                15
        );

        File output = new File("image.png");

        try {
            ImageIO.write(image, "PNG", output);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
