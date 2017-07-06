package com.vgaidarji.dependencies.overview

import com.vgaidarji.dependencies.overview.writer.JsonWriter
import com.vgaidarji.dependencies.overview.writer.MarkdownWriter
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class DependenciesOverviewTask : DefaultTask() {
    init {
        description = "Generates project dependencies overview table from project dependencies"
        group = "documentation"
    }

    @TaskAction
    fun generate() {
        val extension = project.extensions.getByName(DependenciesOverviewPlugin.EXTENSION)
            as DependenciesOverviewExtension
        val artifacts = ArtifactsResolver(project).resolve()
        if (extension.output.json) {
            JsonWriter().write(artifacts)
        }
        if (extension.output.markdown) {
            MarkdownWriter().write(artifacts)
        }
    }
}
