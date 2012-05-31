package de.vorb.xml.atom

import java.net.URI
import scala.xml._

/** Represents a person in an Atom feed. */
case class Person(
  name: String,
  email: Option[String] = None,
  uri: Option[String] = None) {

  /** Returns the XML representation of the Person. */
  def toXML =
    <person>
      <name>{ name }</name>
      {
        email match {
          case Some(email) => <email>{ email }</email>
          case None => Null
        }
      }
      {
        uri match {
          case Some(uri) => <uri>{ uri }</uri>
          case None => Null
        }
      }
    </person>

  /** Returns the String representation of the Person. */
  override def toString = {
    val sb = new StringBuilder

    sb.append("Person(name = ").append(name)
    if (email != None) sb.append(", email = ").append(email.get)
    if (uri != None) sb.append(" (" + uri.get + ")")

    sb.result
  }
}
