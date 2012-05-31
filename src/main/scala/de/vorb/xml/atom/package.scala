package de.vorb.xml

import java.net.URI
import scala.xml.{ Null, UnprefixedAttribute }

package object atom {
  /** The namespace used for Atom feeds. */
  val namespace = new URI("http://www.w3.org/2005/Atom")
  /** The namespace used for XHTML elements within an Atom feed. */
  val xhtmlNamespace = new URI("http://www.w3.org/1999/xhtml")

  /** Helper function, that simplifies the creation of unprefixed attributes. */
  def Attr(key: String, value: String) =
    new UnprefixedAttribute(key, value, Null)
}
