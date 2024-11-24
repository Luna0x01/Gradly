package dev.lunasa.gradly.format

import org.jetbrains.skia.EncodedImageFormat

enum class ImageFormat(internal val internalValue: EncodedImageFormat) {
    BMP(EncodedImageFormat.BMP),
    GIF(EncodedImageFormat.GIF),
    ICO(EncodedImageFormat.ICO),
    JPG(EncodedImageFormat.JPEG),
    PNG(EncodedImageFormat.PNG),
    WEBP(EncodedImageFormat.WEBP),
    HEIF(EncodedImageFormat.HEIF)
}