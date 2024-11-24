import dev.lunasa.gradly.Gradly
import java.awt.Color
import java.io.File
import javax.imageio.ImageIO

fun main() {
    val colors = listOf(
        Color(100, 100, 255).rgb,
        Color(30, 70, 110).rgb,
        Color(90, 64, 200).rgb,
        Color(30, 23, 150).rgb
    )

    val image = Gradly.createGradientWithNoise(
        90f,
        90f,
        colors,
        noiseLevel = 15
    )

    val output = File("image.png")

    ImageIO.write(image, "png", output)
}