package com.mapeiyu

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.aspectj.bridge.IMessage
import org.aspectj.bridge.MessageHandler
import org.aspectj.tools.ajc.Main

class AspectJPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {

        if (project.plugins.findPlugin('com.android.application')) {
            project.android.applicationVariants.all { variant ->
                processCompile(variant.javaCompile, project.android.bootClasspath.join(File.pathSeparator))
            }
        } else if (project.plugins.findPlugin('com.android.library')) {
            project.android.libraryVariants.all { variant ->
                processCompile(variant.javaCompile, project.android.bootClasspath.join(File.pathSeparator))
            }
        } else if (project.plugins.findPlugin('java')) {
            processCompile(tasks.getByName("compileJava"), null)
        } else {
            throw new IllegalStateException("'android' or 'android-library' or 'java' plugin required.")
        }

        project.dependencies {
            compile 'org.aspectj:aspectjrt:1.8.6'
        }
    }

    def processCompile(compileTask, bootClassPath) {
        compileTask.doLast {
            String[] args = [
                    "-showWeaveInfo",
                    "-1.8",
                    "-inpath", destinationDir.toString(),
                    "-aspectpath", classpath.asPath,
                    "-d", destinationDir.toString(),
                    "-classpath", classpath.asPath
            ]
            if (bootClassPath != null) {
                args += ["-bootclasspath", bootClassPath]
            }
            MessageHandler handler = new MessageHandler(true);
            new Main().run(args, handler)

            def log = project.logger
            for (IMessage message : handler.getMessages(null, true)) {
                switch (message.getKind()) {
                    case IMessage.ABORT:
                    case IMessage.ERROR:
                    case IMessage.FAIL:
                        log.error message.message, message.thrown
                        break;
                    case IMessage.WARNING:
                    case IMessage.INFO:
                        log.info message.message, message.thrown
                        break;
                    case IMessage.DEBUG:
                        log.debug message.message, message.thrown
                        break;
                }
            }
        }
    }
}


