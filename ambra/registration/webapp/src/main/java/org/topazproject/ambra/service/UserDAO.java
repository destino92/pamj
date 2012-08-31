/* $HeadURL::                                                                            $
 * $Id$
 *
 * Copyright (c) 2006-2007 by Topaz, Inc.
 * http://topazproject.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.topazproject.ambra.service;

import org.topazproject.ambra.registration.User;

/**
 * Contract for all User DAO's.
 */
public interface UserDAO {
  /**
   * Save or update the user
   * @param user User
   */
  void saveOrUpdate(final User user);

  /**
   * Delete user
   * @param user User
   */
  void delete(final User user);

  /**
   * Find user with a given login name.
   * @param loginName
   * @return User
   */
  User findUserWithLoginName(final String loginName);
}