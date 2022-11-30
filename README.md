# Dependencies overview generator plugin

[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/) [![Build Status](https://travis-ci.org/vgaidarji/dependencies-overview.svg?branch=master)](https://travis-ci.org/vgaidarji/dependencies-overview) [![codecov](https://codecov.io/gh/vgaidarji/dependencies-overview/branch/master/graph/badge.svg?token=QufEOkMXri)](https://codecov.io/gh/vgaidarji/dependencies-overview)

[Gradle plugin](https://docs.gradle.org/current/userguide/custom_plugins.html) which gathers project dependencies and exports them in `Markdown`/`JSON` format.


Installation
------------

Apply the plugin in your `build.gradle`:
```groovy
buildscript {
    repositories {
        jcenter()
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

Once everything configured, you can generate dependencies overview report by executing appropriate Gradle task:

```groovy
./gradlew dependenciesOverview
```

![showcase](./art/dependencies-overview-showcase.gif)

Sample reports
--------------

You can find sample reports in repository root:

- [DEPENDENCIES-OVERVIEW.md](./DEPENDENCIES-OVERVIEW.md) 
- [DEPENDENCIES-OVERVIEW.json](./DEPENDENCIES-OVERVIEW.json)

**JSON**
```json
{
    "dependencies": [
        {
            "group": "com.android.support",
            "version": "25.3.1",
            "name": "animated-vector-drawable",
            "module": {
                "group": "com.android.support",
                "name": "animated-vector-drawable"
            }
        },
        {
            "group": "com.android.support",
            "version": "25.3.1",
            "name": "appcompat-v7",
            "module": {
                "group": "com.android.support",
                "name": "appcompat-v7"
            }
        },
        {
            "group": "joda-time",
            "version": "2.3",
            "name": "joda-time",
            "module": {
                "group": "joda-time",
                "name": "joda-time"
            }
        },
        {
            "group": "org.slf4j",
            "version": "1.7.7",
            "name": "slf4j-android",
            "module": {
                "group": "org.slf4j",
                "name": "slf4j-android"
            }
        }
    ]
}
```

**Markdown**

| Group                            | Name                     | Version       |
| -------------------------------- | ------------------------ | ------------- |
| com.android.support              | animated-vector-drawable | 25.3.1        |
| com.android.support              | appcompat-v7             | 25.3.1        |
| joda-time                        | joda-time                | 2.3           |
| org.slf4j                        | slf4j-android            | 1.7.7         |


Execution time
--------------

On a [sample Android project](./sample-android-app/app) with ~40 compile time dependencies
`JSON` & `Markdown` reports generation takes up to ~70 milliseconds, which is very insignificant. 

Development
------------

This project is written mainly in [Kotlin](https://kotlinlang.org/) and uses [ktlint](https://github.com/shyiko/ktlint) linter.

`build-install.sh` and `build-install-run.sh` scripts can be used during local development.
They contain sequence of `Gradle` commands which help building/installing/running plugin.

- [build-install.sh](./build-install.sh) builds the plugin and installs it into local repository (`repo` folder in project root)
- [build-install-run.sh](./build-install-run.sh) uses [build-install.sh](./build-install.sh) + runs plugin on sample projects.

**How to release**

Plugin is published to and [Maven Central](https://search.maven.org/) repository ([Gradle guide](https://central.sonatype.org/publish/publish-gradle)).
[maven-publishing.gradle](./gradle/maven-publishing.gradle) contains necessary configurations.  
We need to specify few mandatory properties in global `~/.gradle/gradle.properties` file:
```properties
signing.keyId=
signing.password=
signing.secretKeyRingFile=

SONATYPE_USERNAME=
SONATYPE_PASSWORD=
```
Follow [Signatory credentials](https://docs.gradle.org/current/userguide/signing_plugin.html#sec:signatory_credentials) for more details.

As per https://circleci.com/blog/publishing-java-android-libraries/, 
following environment variables should be added to Circle CI project environment variables:
- `ORG_GRADLE_PROJECT_SONATYPE_USERNAME`
- `ORG_GRADLE_PROJECT_SONATYPE_PASSWORD`

`publishToMavenLocal` task can be used to perform a dry run publishing.

`./gradlew clean build publish` command is used to upload signed plugin artifact to [Maven Central](https://search.maven.org/).

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



