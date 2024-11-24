package dev.lunasa.gradly.util

import org.jetbrains.skia.Surface
import java.awt.Color
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import javax.imageio.ImageIO
import kotlin.random.Random

internal fun imageFromBytes(imageData: ByteArray): BufferedImage {
    val stream = ByteArrayInputStream(imageData)
    return ImageIO.read(stream)
}

internal fun addNoiseToImage(image: BufferedImage, noiseLevel: Int = 20): BufferedImage {
    val compatibleImage = if (image.type == BufferedImage.TYPE_INT_ARGB || image.type == BufferedImage.TYPE_INT_RGB) {
        image
    } else {
        val convertedImage = BufferedImage(image.width, image.height, BufferedImage.TYPE_INT_ARGB)
        val graphics = convertedImage.createGraphics()
        graphics.drawImage(image, 0, 0, null)
        graphics.dispose()
        convertedImage
    }

    val width = compatibleImage.width
    val height = compatibleImage.height

    val noisyImage = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)

    for (x in 0 until width) {
        for (y in 0 until height) {
            val argb = compatibleImage.getRGB(x, y)

            val alpha = (argb shr 24) and 0xFF
            val red = (argb shr 16) and 0xFF
            val green = (argb shr 8) and 0xFF
            val blue = argb and 0xFF

            val noisyRed = (red + Random.nextInt(-noiseLevel, noiseLevel)).coerceIn(0, 255)
            val noisyGreen = (green + Random.nextInt(-noiseLevel, noiseLevel)).coerceIn(0, 255)
            val noisyBlue = (blue + Random.nextInt(-noiseLevel, noiseLevel)).coerceIn(0, 255)

            val noisyArgb = (alpha shl 24) or (noisyRed shl 16) or (noisyGreen shl 8) or noisyBlue
            noisyImage.setRGB(x, y, noisyArgb)
        }
    }

    return noisyImage
}

internal fun averageColor(colors: IntArray): Color {
    var totalRed = 0
    var totalGreen = 0
    var totalBlue = 0

    for (color in colors) {
        val red = (color shr 16) and 0xFF
        val green = (color shr 8) and 0xFF
        val blue = color and 0xFF

        totalRed += red
        totalGreen += green
        totalBlue += blue
    }

    val size = colors.size
    val avgRed = totalRed / size
    val avgGreen = totalGreen / size
    val avgBlue = totalBlue / size

    return Color(avgRed, avgGreen, avgBlue)
}

internal fun averageSize(colorCount: Int, size: Float): Float {
    var totalSize = 0f

    for (i in 0..colorCount) {
        totalSize += size * java.util.Random().nextFloat(1f, 10f)
    }

    return totalSize / colorCount
}

internal fun createSurface(width: Int, height: Int) = Surface.makeRasterN32Premul(width, height)
