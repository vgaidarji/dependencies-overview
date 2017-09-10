package com.vgaidarji.dependencies.overview

import com.vgaidarji.dependencies.overview.writer.DependenciesWriter
import com.vgaidarji.dependencies.overview.writer.JsonWriter
import com.vgaidarji.dependencies.overview.writer.MarkdownWriter
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class DependenciesOverviewTask : DefaultTask() {

    var writers: List<DependenciesWriter<*>>
    var artifactsResolver: ArtifactsResolver

    init {
        description = "Generates project dependencies overview table from project dependencies"
        group = "documentation"
        artifactsResolver = ArtifactsResolver(project)
        writers = listOf(JsonWriter(), MarkdownWriter())
    }

    @TaskAction
    fun generate() {
        val extension = project.extensions.getByName(DependenciesOverviewPlugin.EXTENSION)
                as DependenciesOverviewExtension
        if (extension.output.json) {
            (writers.find { it is JsonWriter } as JsonWriter)
                    .write(extension.output.folder, artifactsResolver.resolve())
        }
        if (extension.output.markdown) {
            (writers.find { it is MarkdownWriter } as MarkdownWriter)
                    .write(extension.output.folder, artifactsResolver.resolve())
        }
    }
}
