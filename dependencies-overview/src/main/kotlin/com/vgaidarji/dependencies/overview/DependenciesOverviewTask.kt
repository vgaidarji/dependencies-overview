package com.vgaidarji.dependencies.overview

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class DependenciesOverviewTask : DefaultTask() {

    init {
        description = "Generates project dependencies overview table from project dependencies"
        group = "documentation"
    }

    @TaskAction
    fun generate() {
        println("---DependenciesOverviewTask---")
    }
}
