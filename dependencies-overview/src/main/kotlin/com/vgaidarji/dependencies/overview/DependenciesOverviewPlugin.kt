package com.vgaidarji.dependencies.overview

import org.gradle.api.Plugin
import org.gradle.api.Project

class DependenciesOverviewPlugin : Plugin<Project> {
    private val TASK_NAME = "dependenciesOverview"

    override fun apply(project: Project) {
        project.tasks?.create(TASK_NAME, DependenciesOverviewTask::class.java)
    }
}
