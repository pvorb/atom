package de.vorb.xml.atom

import java.net.URI
import scala.xml.Null

/** Represents a category element in an Atom feed. */
case class Category(
  term: String,
  scheme: Option[URI] = None,
  label: Option[String] = None) {

  /** Returns the XML representation of the Category. */
  def toXML =
    <category/> % Attr("term", term) % (scheme match {
      case Some(uri) => Attr("scheme", uri.toString)
      case None => Null
    }) % (label match {
      case Some(lb) => Attr("label", lb)
      case None => Null
    })

  /** Returns the String representation of the Category. */
  override def toString = {
    val sb = new StringBuilder

    sb.append("Category(term = ").append(term)
    if (scheme != None) sb.append(", scheme = URI(" + scheme.get.toString + ")")
    if (label != None) sb.append(", label = " + label.get + ")")
    sb.append(")")

    sb.result
  }
}
