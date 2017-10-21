# Dependencies overview generator plugin

[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)

[Gradle plugin](https://docs.gradle.org/current/userguide/custom_plugins.html) which gathers project dependencies and exports them in `Markdown`/`JSON` format.


Installation
------------

Apply the plugin in your `build.gradle`:
```groovy
buildscript {
  repositories {
    mavenCentral()
  }
  dependencies {
    classpath 'com.vgaidarji:dependencies-overview:1.0.0'
  }
}

apply plugin: 'dependencies.overview'
```

Configuration
-------------

Full list of available configuration properties can be found in [DependenciesOverviewExtension](https://github.com/vgaidarji/dependencies-overview/blob/master/dependencies-overview/src/main/kotlin/com/vgaidarji/dependencies/overview/DependenciesOverviewExtension.kt).

```groovy
dependenciesOverview {
    output.json = true
    output.markdown = true
    output.folder = "build/reports/dependencies" // by default `= null` (project root)
}
```

Development
------------

This project is written mainly in [Kotlin](https://kotlinlang.org/) and uses [ktlint](https://github.com/shyiko/ktlint) linter.

Developed By
------------

* Veaceslav Gaidarji - <veaceslav.gaidarji@gmail.com>

<a href="https://twitter.com/v_gaidarji">
  <img alt="Follow me on Twitter" src="http://image.flaticon.com/icons/svg/124/124021.svg" height="40" width="40"/>
</a>
<a href="https://www.linkedin.com/in/veaceslavgaidarji">
  <img alt="Add me to Linkedin" src="http://image.flaticon.com/icons/svg/124/124011.svg" height="40" width="40"/>
</a>


TODO
----
- [ ] Add more sample integrations projects (Java application, project with modules)
- [ ] Divide dependencies into groups by project module
- [ ] Introduce task parameter `should_print_to_console`
- [ ] Support other project configurations (currently `compile` configuration is analysed)

License
-------

    Copyright 2017 Veaceslav Gaidarji

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.



