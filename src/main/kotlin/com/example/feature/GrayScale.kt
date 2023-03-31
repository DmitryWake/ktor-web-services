package com.example.feature

import com.example.utils.changeExtension
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.awt.image.BufferedImage
import java.io.File
import java.io.FileNotFoundException
import javax.imageio.ImageIO

class GrayScale {
    suspend fun grayscale(path: String): File? = withContext(Dispatchers.IO) {

        val file = File(path)
        if (!file.exists()) {
            throw FileNotFoundException()
        }

        println("BEGINNING grayscale of $file...")

        val origin = runCatching { ImageIO.read(file) }.getOrNull() ?: return@withContext null

        val blackAndWhite = BufferedImage(origin.width, origin.height, BufferedImage.TYPE_BYTE_GRAY)
        val graphics2D = blackAndWhite.createGraphics()
        graphics2D.drawImage(origin, 0, 0, null)

        val resultFile = kotlin.io.path.createTempFile().toFile().changeExtension("png")

        // will complete in new coroutine
        ImageIO.write(
            blackAndWhite, "png",
            resultFile
        )

        resultFile.also {
            println("RASPBERRY saved in $resultFile")
        }
    }

    companion object {
        val instance by lazy { GrayScale() }
    }
}