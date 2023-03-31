package com.example.utils

import io.ktor.http.content.*
import java.io.File

fun File.changeExtension(newExtension: String): File {
    val i = name.lastIndexOf('.')
    val name: String = name.substring(0, i)
    val newFile = File(parent, "$name.$newExtension").also { createNewFile() }

    copyTo(newFile, overwrite = true).also { delete() }

    return newFile
}

fun saveInputFile(part: PartData.FileItem): File {
    val file = kotlin.io.path.createTempFile().toFile()

    part.streamProvider().use { its ->
        file.outputStream().buffered().use {
            its.copyTo(it)
        }
    }
    println("RASPBERRY saved ${file.absolutePath}")

    return file
}