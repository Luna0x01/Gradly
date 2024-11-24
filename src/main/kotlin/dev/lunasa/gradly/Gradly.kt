package dev.lunasa.gradly

import dev.lunasa.gradly.format.ImageFormat
import dev.lunasa.gradly.util.*
import dev.lunasa.gradly.util.averageColor
import dev.lunasa.gradly.util.averageSize
import dev.lunasa.gradly.util.imageFromBytes
import org.jetbrains.skia.*
import java.awt.image.BufferedImage

/**
 * Holds methods to generate randomized gradients from colors.
 *
 * @author Lunasa
 * @since 1.0.0
 */
public object Gradly {
    /**
     * Create a position randomized gradient from a list of colors.
     *
     * @param width the width of the image
     * @param height the height of the image
     * @param colors a list of colors, any size is accepted
     * @param imageFormat the format of the image
     */
    @JvmOverloads
    @JvmStatic
    public fun createGradient(
        width: Float,
        height: Float,
        colors: List<Int>,
        imageFormat: ImageFormat = ImageFormat.PNG
    ): BufferedImage {
        val surface = createSurface(width.toInt(), height.toInt())
        val canvas = surface.canvas

        val gradientSurface = createSurface(width.toInt(), height.toInt())
        val gradientCanvas = gradientSurface.canvas

        val random = java.util.Random()
        val paint = Paint()

        paint.color = averageColor(colors.toIntArray()).rgb

        canvas.drawRect(Rect.makeWH(width, height), paint)

        repeat(colors.size) {
            paint.color = colors[random.nextInt(colors.size)]

            gradientCanvas.drawRect(Rect.makeXYWH(
                random.nextFloat(0f, width),
                random.nextFloat(0f, height),
                averageSize(colors.size, width),
                averageSize(colors.size, height),
            ), paint)
        }

        val gradientImage = gradientSurface.makeImageSnapshot()
        val blurFilter = ImageFilter.makeBlur(50f, 50f, FilterTileMode.CLAMP)

        Paint().use { blurPaint ->
            blurPaint.imageFilter = blurFilter

            canvas.drawImage(gradientImage, 0f, 0f, blurPaint)
        }

        gradientImage.close()
        gradientSurface.close()
        paint.close()

        return imageFromBytes(surface.makeImageSnapshot().encodeToData(imageFormat.internalValue)!!.bytes)
    }

    /**
     * Create a position randomized gradient with noise from a list of colors.
     *
     * @param width the width of the image
     * @param height the height of the image
     * @param colors a list of colors, any size is accepted
     * @param imageFormat the format of the image
     * @param noiseLevel the level of noise to apply to the final image
     */
    @JvmOverloads
    @JvmStatic
    public fun createGradientWithNoise(
        width: Float,
        height: Float,
        colors: List<Int>,
        imageFormat: ImageFormat = ImageFormat.PNG,
        noiseLevel: Int = 5
    ): BufferedImage {
        return addNoiseToImage(
            createGradient(width, height, colors, imageFormat),
            noiseLevel
        )
    }
}
