/* $HeadURL::                                                                            $
 * $Id$
 *
 * Copyright (c) 2007-2008 by Topaz, Inc.
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
package org.topazproject.ambra.models;

import org.topazproject.otm.annotations.Entity;
import org.topazproject.otm.annotations.GeneratedValue;
import org.topazproject.otm.annotations.Id;

/**
 * Represents the body of an Annotation object.
 *
 * @author Pradeep Krishnan
 */
@Entity()
public class AnnotationBlob extends Blob {
  @Id
  @GeneratedValue(uriPrefix = "annoteaBodyId:", generatorClass = "org.topazproject.ambra.models.support.BlobIdGenerator")
  private String id;

  /**
   * Creates a new AnnotationBlob object.
   */
  public AnnotationBlob() {
  }

  /**
   * Creates a new AnnotationBlob object.
   *
   * @param contentType the content-type 
   * @param body the annotation body blob
   */
  public AnnotationBlob(String contentType, byte[] body) {
    super(contentType, body);
  }

  /**
   * Get id.
   *
   * @return id as String.
   */
  public String getId() {
    return id;
  }

  /**
   * Set id.
   *
   * @param id the value to set.
   */
  public void setId(String id) {
    this.id = id;
  }
}
