package com.vgaidarji.dependencies.overview

import com.vgaidarji.dependencies.overview.DependenciesOverviewPlugin.Companion.DEPENDENCIES_OVERVIEW_TASK
import com.vgaidarji.dependencies.overview.writer.MarkdownWriter
import org.amshove.kluent.shouldEqualTo
import org.amshove.kluent.shouldNotBeBlank
import org.gradle.api.Project
import org.gradle.internal.impldep.com.google.gson.stream.JsonWriter
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Before
import org.junit.Ignore
import org.junit.Test

class DependenciesOverviewTaskTest {

    lateinit var task: DependenciesOverviewTask
    lateinit var project: Project

    @Before
    fun setUp() {
        prepareTask()
    }

    @Test
    fun `task should be in documentation group`() = "documentation" shouldEqualTo task.group

    @Test
    fun `task has description`() = task.description.shouldNotBeBlank()

    @Ignore
    @Test
    fun `writes artifacts list in JSON format if JSON is enabled`() {
        with(project) {
            extensions.findByType(DependenciesOverviewExtension::class.java).output.json = true
            extensions.findByType(DependenciesOverviewExtension::class.java).output.markdown = false
        }

        task.generate()

    }

    private fun prepareTask() {
        project = ProjectBuilder.builder().build()
        project.pluginManager.apply(DependenciesOverviewPlugin::class.java)
        task = project.tasks.findByPath(DEPENDENCIES_OVERVIEW_TASK) as DependenciesOverviewTask
    }
}
