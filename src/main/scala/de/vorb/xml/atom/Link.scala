package de.vorb.xml.atom

import java.net.URI
import scala.xml.Null

case class Link(
  href: String,
  rel: Option[String] = Some("alternate"),
  typ: Option[String] = None,
  hreflang: Option[String] = None,
  title: Option[String] = None,
  length: Option[Int] = None) {

  /** Returns the XML representation of the Link. */
  def toXML =
    <link/> % (Attr("href", href)) % (rel match {
      case Some(rel) => Attr("rel", rel)
      case None => Null
    }) % (typ match {
      case Some(typ) => Attr("type", typ)
      case None => Null
    }) % (hreflang match {
      case Some(hreflang) => Attr("hreflang", hreflang)
      case None => Null
    }) % (title match {
      case Some(title) => Attr("title", title)
      case None => Null
    }) % (length match {
      case Some(length) => Attr("length", length.toString)
      case None => Null
    })

}
