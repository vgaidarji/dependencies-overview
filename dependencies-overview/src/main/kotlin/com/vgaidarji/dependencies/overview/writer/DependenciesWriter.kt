package com.vgaidarji.dependencies.overview.writer

import java.io.File

interface DependenciesWriter<in T> {
    /**
     * Writes given artifacts to file.
     */
    fun write(artifacts: T)

    /**
     * Writes given content to file.
     * @param fileName Destination file name
     * @param content Content to be written in destination file
     */
    fun writeToFile(fileName: String, content: String) {
        File(fileName).printWriter().use { out ->
            out.println(content)
        }
    }
}