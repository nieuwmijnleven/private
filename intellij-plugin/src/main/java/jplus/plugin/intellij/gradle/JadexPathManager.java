/*
 * JADEx - Java Advanced Development Extension
 *
 * Copyright (C) 2026 Cheol Jeon <nieuwmijnleven@outlook.com>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 only,
 * as published by the Free Software Foundation.
 *
 * Alternatively, this software may be used under a commercial license
 * from Cheol Jeon.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License version 2 for more details:
 * <https://www.gnu.org/licenses/old-licenses/gpl-2.0.html>.
 *
 * For commercial licensing, please contact <nieuwmijnleven@outlook.com>.
 *
 * Contributors to this project must sign a Contributor License Agreement (CLA)
 * granting Cheol Jeon the right to relicense their contributions under
 * a commercial license. See the CLA file in the project root for details.
 */

package jplus.plugin.intellij.gradle;

import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import jadex.gradle.JadexModel;
import jplus.plugin.intellij.annotator.JPlusExternalAnnotator;
import jplus.plugin.intellij.settings.JadexProjectSettings;

import java.util.List;

public class JadexPathManager {

    private static final Logger LOG = Logger.getInstance(JadexPathManager.class);

    public static void refresh(Project project) {
        ApplicationManager.getApplication().executeOnPooledThread(() -> {
            JadexPathResolver resolver = JadexPathResolver.getInstance(project);
            resolver.invalidateCache();

            List<JadexModel> jadexModelList = resolver.resolve(project);
            if (jadexModelList.isEmpty()) {
                notifyNoJadexModuleFound(project);
                return;
            }

            jadexModelList.forEach(jadexModel -> {
                LOG.debug("-- JADEx Gradle Settings info --");
                LOG.debug("JADEx projectDir: ", jadexModel.getProjectDir());
                LOG.debug("JADEx pluginVersion: ", jadexModel.getPluginVersion());
                LOG.debug("JADEx javaHome: ", jadexModel.getJavaHome());
                LOG.debug("JADEx javaVersion: ", jadexModel.getJavaVersion());
                LOG.debug("JADEx javaSrcDirs: ", jadexModel.getJavaSrcDirs());
                LOG.debug("JADEx classPath: ", jadexModel.getClassPath());

                int majorVersion = parseMajorVersion(jadexModel.getJavaVersion());
                if (majorVersion < 21) {
                    notifyJavaVersionTooLow(project, jadexModel.getJavaVersion());
                }

                JadexProjectSettings.getInstance(project)
                        .update(jadexModel);
            });

            JPlusExternalAnnotator.clearProjectCache();
        });
    }

    private static void notifyNoJadexModuleFound(Project project) {
        NotificationGroupManager.getInstance()
                .getNotificationGroup("JADEx")
                .createNotification(
                        "JADEx: No module found",
                        "No Gradle module with the JADEx plugin was found.<br>" +
                                "Please apply the JADEx plugin in your <b>build.gradle</b>:<br><br>" +
                                "<code>id 'io.github.nieuwmijnleven.jadex'</code>",
                        NotificationType.WARNING)
                .notify(project);
    }

    private static void notifyJavaVersionTooLow(Project project, String currentVersion) {
        NotificationGroupManager.getInstance()
                .getNotificationGroup("JADEx")
                .createNotification(
                        "JADEx: Java version not supported",
                        "JADEx requires <b>Java 21 or higher</b>.<br><br>" +
                                "Gradle JVM version: <b>" + currentVersion + "</b><br><br>" +
                                "Please update the Gradle JVM in:<br>" +
                                "<b>Settings → Build, Execution, Deployment → Build Tools → Gradle → Gradle JVM</b>",
                        NotificationType.ERROR)
                .notify(project);
    }

    public static int parseMajorVersion(String version) {
        if (version == null || version.isBlank()) {
            throw new IllegalArgumentException("Version string must not be null or blank");
        }

        String[] parts = version.split("[.+\\-]");

        int first = Integer.parseInt(parts[0]);

        // 1.x.y → major = x
        if (first == 1 && parts.length >= 2) {
            return Integer.parseInt(parts[1]);
        }

        // Modern: x.y.z → major = x
        return first;
    }

}