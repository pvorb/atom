package de.vorb.xml.atom

import java.text.SimpleDateFormat
import scala.xml.Null

/** Represents an entry of an Atom feed. */
case class Entry(
  id: String,
  title: Text,
  updated: Long,
  content: Option[Text] = None,
  summary: Option[String] = None,
  links: Seq[Link] = Nil,
  authors: Seq[Person] = Nil,
  contributors: Seq[Person] = Nil) {

  // If the entry has no alternate links, it must have content
  if (links.forall(_.typ != "alternate"))
    require(content != None)

  // TODO: An entry is limited to one alternate per type and hreflang. An entry
  // must contain an alternate link if there is no content element.

  private val df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")

  /** Returns the XML representation for this entry. */
  def toXML =
    <entry>
      <id>{ id }</id>
      { title.toXML.copy(label = "title") }
      <updated>{ df.format(updated) }</updated>
      {}
      {
        summary match {
          case Some(summary) => <summary>summary</summary>
          case None => Null
        }
      }
      {
        content match {
          case Some(content) => content.toXML.copy(label = "content")
          case None => Null
        }
      }
    </entry>
}
