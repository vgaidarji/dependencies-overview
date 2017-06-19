package com.vgaidarji.dependencies.overview.writer

import java.io.File

interface DependenciesWriter<in T> {
    fun write(artifacts: T)

    fun writeToFile(fileName: String, content: String) {
        File(fileName).printWriter().use { out ->
            out.println(content)
        }
    }
}