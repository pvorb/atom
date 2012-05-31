package de.vorb.xml.atom

import scala.xml._

/** Represents a Text node. */
case class Text(
  content: AnyRef,
  typ: String = "text") {

  // Requirements
  require(typ match {
    case "xhtml" =>
      require(content.isInstanceOf[Elem], "If typ is \"xhtml\" content must " +
        "be of type scala.xml.Elem."); true
    case _ if (typ == "text" || typ == "html") =>
      require(content.isInstanceOf[String], "If typ is not \"xhtml\" content " +
        "must be of type String"); true
    case _ => false
  }, "typ must either equal \"text\", \"html\", or \"xhtml\"")

  /** Returns the XML representation of the Text. */
  def toXML = {
    def typeAttr =
      typ match {
        case "text" => Null
        case _ => new UnprefixedAttribute("type", typ, Null)
      }

    def inner: Node = {
      def plain = {
        scala.xml.Text(content.asInstanceOf[String])
      }

      def xhtml =
        content match {
          case div @ <div>{ _* }</div> if (div \ "@xmlns" ==
            xhtmlNamespace.toString()) => div
          case _ => throw new IllegalArgumentException("If typ equals " +
            "\"xhtml\" content must be of type scala.xml.Elem like `<div " +
            "xmlns=\"http://www.w3.org/1999/xhtml\">...</div>`.")
        }

      typ match {
        case "text" => plain
        case "html" => plain
        case "xhtml" => xhtml
      }
    }

    Elem(null, "text", typeAttr, TopScope, inner)
  }

  /** Returns the XML representation of the Text. */
  override def toString = {
    val sb = new StringBuilder

    sb.append("Text(typ = ").append(typ)
    sb.append(", content = \"\"\"")
    sb.append(content.toString)
    sb.append("\"\"\")")

    sb.result
  }
}