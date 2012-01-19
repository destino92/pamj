/* $HeadURL$
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
package org.topazproject.ambra.model.article;

import java.io.Serializable;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.topazproject.ambra.model.UserProfileInfo;
import org.topazproject.otm.CollectionType;
import org.topazproject.otm.annotations.Entity;
import org.topazproject.otm.annotations.Id;
import org.topazproject.otm.annotations.Predicate;

/**
 * Just the list of authors.
 */
@Entity(type = "bibtex:Entry", model = "ri")
public class CitationInfo implements Serializable {
  @Id
  public URI id;

  @Predicate(uri = "plos:hasAuthorList", collectionType = CollectionType.RDFSEQ)
  public List<UserProfileInfo> authors = new ArrayList<UserProfileInfo>();
}
