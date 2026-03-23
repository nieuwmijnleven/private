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
import com.intellij.openapi.project.Project;
import jadex.gradle.JadexModel;
import jplus.plugin.intellij.annotator.JPlusExternalAnnotator;
import jplus.plugin.intellij.settings.JadexProjectSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class JadexPathManager {

    private static final Logger LOG = LoggerFactory.getLogger(JadexPathManager.class);

    public static void refresh(Project project) {
        ApplicationManager.getApplication().executeOnPooledThread(() -> {
            JadexPathResolver resolver = JadexPathResolver.getInstance(project);
            resolver.invalidateCache();

            List<JadexModel> jadexModelList = resolver.resolve(project);
            if (jadexModelList.isEmpty()) {
                notifyNoJadexModuleFound(project);
                return;
            }

            //jadexModelList.forEach(jadexModel -> System.out.println(jadexModel.getJavaSrcDirs()));

            JadexProjectSettings.getInstance(project).update(jadexModelList);
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
}