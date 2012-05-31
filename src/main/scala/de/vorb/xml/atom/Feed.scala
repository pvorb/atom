package de.vorb.xml.atom

import java.net.URI
import java.text.SimpleDateFormat
import scala.xml.{ Text => TextElem }
import scala.xml._
import de.vorb.xml.atom._

/** Atom feed. */
case class Feed(
  id: String,
  title: String,
  updated: Long,
  authors: Seq[Person] = Nil,
  contributors: Seq[Person] = Nil,
  links: Seq[Link] = Nil,
  entries: Seq[Entry] = Nil,
  categories: Seq[Category] = Nil,
  icon: Option[String] = None,
  logo: Option[String] = None,
  rights: Option[Text] = None) {

  // Ensure authors
  if (authors isEmpty)
    require(entries.forall(!_.authors.isEmpty), "If the feed doesn't have an " +
      "author, all entries must have at least one author.")

  val namespace = de.vorb.xml.atom.namespace

  private val df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")

  /** Returns the XML representation of the Atom feed. */
  def toXML =
    <feed>
      <id>{ id }</id>
      <title>{ title }</title>
      <updated>{ df.format(updated) }</updated>
      { for (a <- authors) yield a.toXML.copy(label = "author") }
      { for (c <- contributors) yield c.toXML.copy(label = "contributor") }
      { for (l <- links) yield l.toXML }
      { for (e <- entries) yield e.toXML }
      { for (c <- categories) yield c.toXML }
      {
        icon match {
          case Some(uri) => <icon>{ uri.toString }</icon>
          case None => Null
        }
      }
      {
        logo match {
          case Some(uri) => <logo>{ uri.toString }</logo>
          case None => Null
        }
      }
    </feed> % Attribute(None, "xmlns", TextElem(namespace.toString), Null)
}
