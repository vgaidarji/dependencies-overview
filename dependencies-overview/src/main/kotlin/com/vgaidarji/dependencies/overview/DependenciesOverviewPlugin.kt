package com.vgaidarji.dependencies.overview

import org.gradle.api.Plugin
import org.gradle.api.Project

class DependenciesOverviewPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.create("dependenciesOverview")
        println("---DependenciesOverviewPlugin---")
    }
}

