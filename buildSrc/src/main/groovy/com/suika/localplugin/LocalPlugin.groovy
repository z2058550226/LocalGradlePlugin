package com.suika.localplugin

import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

public class LocalPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        println '==============================='
        println 'this is the local plugin'
        println '==============================='
        project.extensions.findByType(BaseExtension.class)
                .registerTransform(new MyTransfor(project))
    }
}