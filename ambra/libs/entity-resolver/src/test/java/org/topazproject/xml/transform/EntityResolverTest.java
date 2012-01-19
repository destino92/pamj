/* $HeadURL::                                                                                     $
 * $Id$
 *
 * Copyright (c) 2006-2008 by Topaz, Inc.
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

package org.topazproject.xml.transform;

import java.io.ByteArrayOutputStream;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.InputSource;

import junit.framework.TestCase;

import org.topazproject.xml.transform.cache.CachedSource;

/**
 * @author Ronald Tschalär
 * @version $Id$
 */
public class EntityResolverTest extends TestCase {
  /**
   * Test that the resource cache is correct and complete (nothing needed from the network).
   */
  public void testResourceCache() throws Exception {
    // make sure network access will break
    System.setProperty("http.proxyHost", "-dummy-");
    System.setProperty("http.proxyPort", "-1");

    // run tests
    doTestCachedSource("article_v11.xml");
    doTestCachedSource("article_v20.xml");
    doTestCachedSource("article_v21.xml");
    doTestCachedSource("article_v22.xml");
  }

  private void doTestCachedSource(String input) throws Exception {
    Transformer transformer = TransformerFactory.newInstance().newTransformer();
    InputSource myInputSource = new InputSource(getClass().getResourceAsStream(input));
    ByteArrayOutputStream res = new ByteArrayOutputStream(500);
    transformer.transform(new CachedSource(myInputSource), new StreamResult(res));
  }
}
