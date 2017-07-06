package com.vgaidarji.dependencies.overview

open class DependenciesOverviewExtension {
    /**
     * Specifies output format.
     */
    var output = OutputFormat()

    open class OutputFormat {
        /**
         * Dependencies list will be printed out in markdown format.
         */
        var markdown = true
        /**
         * Dependencies list will be printed out in json format.
         */
        var json = false
    }
}