/* $HeadURL$
 * $Id$ 
 *
 * Copyright (c) 2006-2010 by Public Library of Science
 * http://plos.org
 * http://ambraproject.org
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
package org.ambraproject.model.article;

import java.io.Serializable;
import java.net.URI;

public class RelatedArticleInfo implements Serializable {
  public URI    uri;
  public String title;

  /**
   * Get the article uri.
   *
   * @return the article uri.
   */
  public URI getUri() {
    return uri;
  }

  public void setUri(URI uri) {
    this.uri = uri;
  }

  /**
   * Get the title.
   *
   * @return the title.
   */
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String toString() {
    return "RelatedArticleInfo[uri='" + uri + "', title='" + title + "']";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    RelatedArticleInfo that = (RelatedArticleInfo) o;

    if (title != null ? !title.equals(that.title) : that.title != null) return false;
    if (uri != null ? !uri.equals(that.uri) : that.uri != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = uri != null ? uri.hashCode() : 0;
    result = 31 * result + (title != null ? title.hashCode() : 0);
    return result;
  }
}
