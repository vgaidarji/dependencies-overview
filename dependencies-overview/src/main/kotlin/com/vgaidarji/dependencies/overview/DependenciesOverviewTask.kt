package com.vgaidarji.dependencies.overview

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class DependenciesOverviewTask : DefaultTask() {

    override fun getDescription(): String {
        return "Generates project dependencies overview table from project dependencies"
    }

    override fun getGroup(): String {
        return "documentation"
    }

    @TaskAction
    fun generate() {
        println("---DependenciesOverviewTask---")
    }
}
