package com.vgaidarji.dependencies.overview

import com.vgaidarji.dependencies.overview.DependenciesOverviewPlugin.Companion.DEPENDENCIES_OVERVIEW_TASK
import com.vgaidarji.dependencies.overview.writer.JsonWriter
import com.vgaidarji.dependencies.overview.writer.MarkdownWriter
import org.amshove.kluent.*
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Before
import org.junit.Test

class DependenciesOverviewTaskTest : BaseTest() {

    private lateinit var task: DependenciesOverviewTask
    private lateinit var project: Project

    @Before
    fun setUp() {
        prepareTask()
    }

    @Test
    fun `task should be in documentation group`() = "documentation" shouldEqualTo task.group

    @Test
    fun `task has description`() = task.description.shouldNotBeBlank()

    @Test
    fun `task has artifacts resolver`() = task.artifactsResolver.shouldNotBeNull()

    @Test
    fun `task contains markdown and JSON writers by default`() {
        task.writers.find { it is MarkdownWriter }.shouldBeInstanceOf(MarkdownWriter::class.java)
        task.writers.find { it is JsonWriter }.shouldBeInstanceOf(JsonWriter::class.java)
    }

    @Test
    fun `writes artifacts list in JSON format if JSON is enabled`() {
        val testJsonWriter = TestJsonWriter()
        with(project) {
            val extension = extensions.findByType(DependenciesOverviewExtension::class.java)
            extension.output.json = true
            extension.output.markdown = false
        }
        task.artifactsResolver = ArtifactsResolver(projectWithArtifacts(TestArtifact.JODA_TIME))
        task.writers = listOf(testJsonWriter)

        task.generate()

        """
            {
                "dependencies": [
                    {
                        "group": "joda-time",
                        "version": "2.3",
                        "name": "joda-time",
                        "module": {
                            "group": "",
                            "name": ""
                        }
                    }
                ]
            }""".trimIndent() `should equal to` testJsonWriter.content
    }

    @Test
    fun `writes artifacts list in markdown format if markdown is enabled`() {
        val testMarkdownWriter = TestMarkdownWriter()
        with(project) {
            val extension = extensions.findByType(DependenciesOverviewExtension::class.java)
            extension.output.json = false
            extension.output.markdown = true
        }
        task.artifactsResolver = ArtifactsResolver(projectWithArtifacts(TestArtifact.JODA_TIME))
        task.writers = listOf(testMarkdownWriter)

        task.generate()

        """
            | Group     | Name      | Version |
            | --------- | --------- | ------- |
            | joda-time | joda-time | 2.3     |""".trimIndent() `should equal to` testMarkdownWriter.content
    }

    private fun prepareTask() {
        project = ProjectBuilder.builder().build()
        project.pluginManager.apply(DependenciesOverviewPlugin::class.java)
        task = project.tasks.findByPath(DEPENDENCIES_OVERVIEW_TASK) as DependenciesOverviewTask
    }

    class TestJsonWriter : JsonWriter() {
        var content: String = ""
        override fun writeToFile(fileName: String, content: String) {
            this.content = content
        }

        override fun writeToFile(folder: String?, fileName: String, content: String) {
            this.content = content
        }
    }

    class TestMarkdownWriter : MarkdownWriter() {
        var content: String = ""
        override fun writeToFile(fileName: String, content: String) {
            this.content = content
        }

        override fun writeToFile(folder: String?, fileName: String, content: String) {
            this.content = content
        }
    }
}
