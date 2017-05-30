package com.vgaidarji.dependencies.overview

import com.vgaidarji.dependencies.overview.DependenciesOverviewPlugin.Companion.DEPENDENCIES_OVERVIEW_TASK
import org.amshove.kluent.shouldEqualTo
import org.amshove.kluent.shouldNotBeBlank
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Before
import org.junit.Test

class DependenciesOverviewTaskTest {

    lateinit var task: DependenciesOverviewTask

    @Before
    fun setUp() {
        prepareTask()
    }

    @Test
    fun `task should be in documentation group`() = "documentation" shouldEqualTo task.group

    @Test
    fun `task has description`() = task.description.shouldNotBeBlank()

    private fun prepareTask() {
        val project = ProjectBuilder.builder().build()
        project.pluginManager.apply(DependenciesOverviewPlugin::class.java)
        task = project.tasks.findByPath(DEPENDENCIES_OVERVIEW_TASK) as DependenciesOverviewTask
    }
}
