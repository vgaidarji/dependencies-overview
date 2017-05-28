package com.vgaidarji.dependencies.overview

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class DependenciesOverviewTask : DefaultTask() {
    @TaskAction
    fun generate() {
        println("---DependenciesOverviewTask---")
    }
}
