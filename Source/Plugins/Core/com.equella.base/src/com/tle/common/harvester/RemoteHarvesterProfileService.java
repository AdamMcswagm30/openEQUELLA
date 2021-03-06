/*
 * Licensed to The Apereo Foundation under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * The Apereo Foundation licenses this file to you under the Apache License,
 * Version 2.0, (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tle.common.harvester;

import com.tle.core.remoting.RemoteAbstractEntityService;
import java.util.List;

public interface RemoteHarvesterProfileService
    extends RemoteAbstractEntityService<HarvesterProfile> {
  String ENTITY_TYPE = "HARVESTER_PROFILE"; // $NON-NLS-1$

  /**
   * List the harvester profiles
   *
   * @return
   */
  List<HarvesterProfile> enumerateEnabledProfiles();

  /**
   * Runs a harvester profile
   *
   * @param profile The profile
   * @throws Exception
   */
  int testProfile(String profileUuid);

  /**
   * Runs a harvester profile as a SpringClusteredTask
   *
   * @param harvesterProfile The profile
   */
  void startHarvesterTask(String profileUuid, boolean manualKickoff);
}
