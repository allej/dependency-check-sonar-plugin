/*
 * Dependency-Check Plugin for SonarQube
 * Copyright (C) 2015-2023 dependency-check
 * philipp.dallig@gmail.com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.dependencycheck.report;

import java.io.File;
import java.io.FileNotFoundException;

import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.config.Configuration;
import org.sonar.api.scan.filesystem.PathResolver;
import org.sonar.dependencycheck.base.DependencyCheckConstants;

public class HtmlReportFile extends ReportFile {

    public static HtmlReportFile getHtmlReport(Configuration config, FileSystem fileSystem, PathResolver pathResolver) throws FileNotFoundException {
        String path = config.get(DependencyCheckConstants.HTML_REPORT_PATH_PROPERTY).orElse(DependencyCheckConstants.HTML_REPORT_PATH_DEFAULT);
        File report = pathResolver.relativeFile(fileSystem.baseDir(), path);
        report = checkReport(report, ReportFormat.HTML, DependencyCheckConstants.HTML_REPORT_PATH_PROPERTY);
        if (report == null) {
            throw new FileNotFoundException("HTML-Dependency-Check report does not exist.");
        }
        return new HtmlReportFile(report);
    }

    private HtmlReportFile(File report) {
        super(ReportFormat.HTML, DependencyCheckConstants.HTML_REPORT_PATH_PROPERTY, report);
    }
}
