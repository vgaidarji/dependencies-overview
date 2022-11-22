package com.vgaidarji.dependencies.overview

open class DependenciesOverviewExtension {
    /**
     * Specifies output format.
     */
    var output = OutputFormat()

    open class OutputFormat {
        /**
         * Dependencies list will be printed out in markdown format.
         * Enabled by default.
         */
        var markdown = true

        /**
         * Dependencies list will be printed out in json format.
         * Disabled by default.
         */
        var json = false

        /**
         * Folder path where dependencies will be written (relative to the project).
         * By default files with dependencies will be located on project root level (folder = null).
         * Example of usage: "build/reports/dependencies".
         */
        var folder: String? = null
    }
}
