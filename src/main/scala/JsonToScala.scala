import net.liftweb.json._

class JsonToScala(private var json:String) {
  implicit val formats = DefaultFormats
  var jValue:JValue=_

  def parseJson()={
    jValue=parse(json)
  }
  def getScalaClass()={
    val data= (jValue\"Data").extract[Data]

  }

}
