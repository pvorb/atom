Atom
====

Atom feed library written in Scala.

Usage example
-------------

```scala
import java.net.URI
import scala.compat.Platform
import scala.xml.{ PrettyPrinter, Utility }
import de.vorb.xml.atom

object UsageExample extends App {

  lazy val entries =
    Entry(
      id = "http://example.com/2012/test.html",
      title = Text("Test"),
      content = Some(Text("<p>This is some example text.</p>", "html")),
      updated = Platform.currentTime) ::
    Entry(
      id = "http://example.com/2012/test2.html",
      title = Text("Test 2"),
      content = Some(Text("<p>This is some example text.</p>", "html")),
      updated = Platform.currentTime) ::
    Nil

  val feed = Feed(
    id = "http://example.com/feed.xml",
    title = "Example articles",
    updated = Platform.currentTime,
    authors = List(
      Person("John Doe",
        Some("john@example.com"),
        Some("http://example.com/john.html")),
      Person("Jane Doe",
        Some("jane@example.com"),
        Some("http://example.com/jane.html"))),
    links = List(Link("http://example.com/feed.xml", Some("self"))),
    entries = entries,
    categories = List(Category("test")),
    icon = Some("http://example.com/favicon.ico"))

  val prettyPrinter = new PrettyPrinter(80, 2);

  println(prettyPrinter.format(feed.toXML))
}
```

This will print something like:

```
<feed xmlns="http://www.w3.org/2005/Atom">
  <id>http://example.com/feed.xml</id>
  <title>Example articles</title>
  <updated>2012-05-31T16:46:33.851+02:00</updated>
  <author>
    <name>John Doe</name>
    <email>john@example.com</email>
    <uri>http://example.com/john.html</uri>
  </author>
  <author>
    <name>Jane Doe</name>
    <email>jane@example.com</email>
    <uri>http://example.com/jane.html</uri>
  </author>
  <link href="http://example.com/feed.xml" rel="self"></link>
  <entry>
    <id>http://example.com/2012/test.html</id>
    <title>Test</title>
    <updated>2012-05-31T16:46:33.911+02:00</updated>
    <content type="html">&lt;p&gt;This is some example text.&lt;/p&gt;</content>
  </entry>
  <entry>
    <id>http://example.com/2012/test2.html</id>
    <title>Test 2</title>
    <updated>2012-05-31T16:46:33.952+02:00</updated>
    <content type="html">&lt;p&gt;This is some example text.&lt;/p&gt;</content>
  </entry>
  <category term="test"></category>
  <icon>http://example.com/favicon.ico</icon>
</feed>
```

License
-------

Copyright © 2012 Paul Vorbach

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the “Software”), to deal in
the Software without restriction, including without limitation the rights to
use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
the Software, and to permit persons to whom the Software is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, OUT OF OR IN CONNECTION WITH THE
SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
