package com.vgaidarji.dependencies.overview.writer

import java.io.File

interface DependenciesWriter<in T> {
    /**
     * Writes given artifacts to file.
     */
    fun write(artifacts: T)

    /**
     * Writes given artifacts to file.
     */
    fun write(folder: String?, artifacts: T)

    /**
     * Writes given content to file.
     * @param fileName Destination file name (relative to the project)
     * @param content Content to be written in destination file
     */
    fun writeToFile(fileName: String, content: String) {
        writeToFile(null, fileName, content)
    }

    /**
     * Writes given content to file.
     * @param folder Destination folder. Pass <code>null</code> to create file in root folder.
     * @param fileName Destination file name
     * @param content Content to be written in destination file
     */
    fun writeToFile(folder: String?, fileName: String, content: String) {
        folder?.let { File(it).mkdirs() }
        File(folder, fileName).printWriter().use { out ->
            out.println(content)
        }
    }
}