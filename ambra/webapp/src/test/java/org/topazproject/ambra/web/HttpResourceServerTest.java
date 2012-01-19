/* $HeadURL$
 * $Id$
 *
 * Copyright (c) 2006-2009 by Topaz, Inc.
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

package org.topazproject.ambra.web;

import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpServletRequest;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.custommonkey.xmlunit.Diff;
import org.xml.sax.SAXException;

import java.net.URL;
import java.io.IOException;

/**
 * @author Dragisa Krsmanovic
 * TODO: Test ranges
 */
public class HttpResourceServerTest {
  private static final String EXPECTED_TEXT = "Hello World !";
  private static final String EXPECTED_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
      "<test>Hello World !</test>";
  private URL xmlUrl;
  private URL txtUrl;

  @BeforeClass
  protected void setUpClass() throws Exception {
    xmlUrl = this.getClass().getResource("/TestResource.xml");
    txtUrl = this.getClass().getResource("/TestResource.txt");
  }

  @Test
  public void testServerResourceTxt() throws IOException {
    MockHttpServletResponse responseMock = new MockHttpServletResponse();
    MockHttpServletRequest requestMock = new MockHttpServletRequest();
    HttpResourceServer server = new HttpResourceServer();
    server.serveResource(requestMock, responseMock, new HttpResourceServer.URLResource(txtUrl));
    assertEquals(responseMock.getContentAsString(), EXPECTED_TEXT, "Wrong content served");
    assertEquals(responseMock.getContentType(), "text/plain", "Wrong content type");
    assertEquals(responseMock.getContentLength(), EXPECTED_TEXT.length(),
        "Wrong content length");
  }

  @Test
  public void testServerResourceXml() throws IOException, SAXException {
    MockHttpServletResponse responseMock = new MockHttpServletResponse();
    MockHttpServletRequest requestMock = new MockHttpServletRequest();
    HttpResourceServer server = new HttpResourceServer();
    server.serveResource(requestMock, responseMock, new HttpResourceServer.URLResource(xmlUrl));
    Diff diff = new Diff(EXPECTED_XML, responseMock.getContentAsString());
    assertTrue(diff.identical(), diff.toString());
    assertEquals(responseMock.getContentType(), "application/xml", "Wrong content type");
    assertEquals(responseMock.getContentLength(), responseMock.getContentAsString().length(),
        "Wrong content length");
  }

  @Test
  public void testServerResourceForHead() throws IOException {
    MockHttpServletResponse responseMock = new MockHttpServletResponse();
    MockHttpServletRequest requestMock = new MockHttpServletRequest();
    requestMock.setMethod("HEAD");
    HttpResourceServer server = new HttpResourceServer();
    server.serveResource(requestMock, responseMock, new HttpResourceServer.URLResource(txtUrl));
    assertEquals(responseMock.getContentAsString(), "", "Content is not empty");
    assertEquals(responseMock.getContentType(), "text/plain", "Wrong content type");
    assertEquals(responseMock.getContentLength(), EXPECTED_TEXT.length(),
        "Wrong content length");
  }

  @Test
  public void testServerResourceWithContent() throws IOException {
    MockHttpServletResponse responseMock = new MockHttpServletResponse();
    MockHttpServletRequest requestMock = new MockHttpServletRequest();
    HttpResourceServer server = new HttpResourceServer();
    server.serveResource(requestMock, responseMock, true, new HttpResourceServer.URLResource(txtUrl));
    assertEquals(responseMock.getContentAsString(), EXPECTED_TEXT, "Wrong content served");
    assertEquals(responseMock.getContentType(), "text/plain", "Wrong content type");
    assertEquals(responseMock.getContentLength(), EXPECTED_TEXT.length(),
        "Wrong content length");
  }

  @Test
  public void testServerResourceWithoutContent() throws IOException {
    MockHttpServletResponse responseMock = new MockHttpServletResponse();
    MockHttpServletRequest requestMock = new MockHttpServletRequest();
    HttpResourceServer server = new HttpResourceServer();
    server.serveResource(requestMock, responseMock, false, new HttpResourceServer.URLResource(txtUrl));
    assertEquals(responseMock.getContentAsString(), "", "Content is not empty");
    assertEquals(responseMock.getContentType(), "text/plain", "Wrong content type");
    assertEquals(responseMock.getContentLength(), EXPECTED_TEXT.length(),
        "Wrong content length");
  }
}
