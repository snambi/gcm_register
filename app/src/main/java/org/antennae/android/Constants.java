/**
 * Copyright 2015 Antennea Project. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.antennae.android;

/**
 * Created by nambi sankaran on 6/16/15.
 */
public class Constants {

    // these constants are specific to application.
    // TODO: move these to a configuration file, so that they can be easily changed
    public static final String PREFS_NAME = "GCM_DEMO";
    public static final String PREFS_PROPERTY_REG_ID = "registration_id";
    public static final String PROJECT_ID = "549760952886";


    // these constants are meant for the framework
    // these can be common for all antennea application
    public static final String PREF_ANTENNAE = "PREF_ANTENNAE";
    public static final String ANTENNAE_REGISTRATION_ID = "ANT_REGISTER_ID";
    public static final String ANTENNAE_APP_VERSION = "ANT_APP_VERSION";
}
