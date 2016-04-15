/*
* Part of Protocoder http://www.protocoder.org
* A prototyping platform for Android devices 
*
* Copyright (C) 2013 Victor Diaz Barrales victormdb@gmail.com
* 
* Protocoder is free software: you can redistribute it and/or modify
* it under the terms of the GNU Lesser General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* Protocoder is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
* 
* You should have received a copy of the GNU Lesser General Public License
* along with Protocoder. If not, see <http://www.gnu.org/licenses/>.
*/

package org.protocoderrunner.events;

import org.protocoderrunner.models.Project;

public class Events {
    public static final String KEY                  = "key";
    public static final String SMS                  = "sms";
    public static final String NFC_READ             = "nfc_read";
    public static final String NFC_WRITTEN          = "nfc_written";
    public static final String BLUETOOTH            = "bluetooth";
    public static final String VOICE_RECOGNITION    = "voice_recognition";


    public static class ProjectEvent {
        private Project project;
        private String action;

        public ProjectEvent(String action, Project project) {
            this.action = action;
            this.project = project;
        }

        public ProjectEvent(String action, String folder, String name) {
            this.action = action;
            this.project = new Project(folder, name);
        }

        public String getAction() {
            return action;
        }

        public Project getProject() {
            return project;
        }
    }


    public static class LogEvent {
        private String log;

        public LogEvent(String log) {
            this.log = log;
        }

        public String getLog() {
            return log;
        }
    }


}