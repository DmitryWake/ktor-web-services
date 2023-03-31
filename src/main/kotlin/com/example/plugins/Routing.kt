package com.example.plugins

import com.example.feature.GrayScale
import com.example.model.WordsWithLetterModel
import com.example.utils.saveInputFile
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        get("/words") {
            val letterWord = call.receive<WordsWithLetterModel>()
            val filtered = letterWord.copy(
                words = letterWord.words.filter {
                    it.contains(letterWord.letter)
                }
            )
            call.respond(filtered)
        }

        post("/grayscale/upload") { _ ->
            val multipart = call.receiveMultipart()

            multipart.forEachPart { part ->
                if (part is PartData.FileItem) {
                    val file = saveInputFile(part)
                    GrayScale.instance.grayscale(file.absolutePath).let {
                        if (it == null) {
                            call.respond(HttpStatusCode.BadRequest)
                        } else {
                            call.respondFile(it)
                        }
                    }
                } else {
                    call.respond(HttpStatusCode.BadRequest)
                }

                part.dispose()
            }
        }
    }
}
