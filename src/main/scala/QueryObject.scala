import net.liftweb.json.JsonAST.JValue
import net.liftweb.json.{DefaultFormats, parse}
import org.apache.http.client.methods.{CloseableHttpResponse, HttpPost}
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.HttpClientBuilder

import scala.io.Source

object QueryObject {
  implicit val formats = DefaultFormats
  private val BASE_GHQL_URL = "https://api.github.com/graphql"
  // val TOKEN = "b24f584000aa47629266eac2857f9d0ab618ddde"
  private val TOKEN = "8ed5131d2ab4a6d7704e87f3a23e202b6dfc3621"
  private val client = HttpClientBuilder.create.build
  private val httpUriRequest = new HttpPost(BASE_GHQL_URL)
  private var response:CloseableHttpResponse=_
  def setHeaders(name:String,value:String,Token:String):this.type ={
    httpUriRequest.addHeader(name, value+ Token)
    httpUriRequest.addHeader("Accept", "application/json")
    this
  }

  def addHeaders():this.type={
    httpUriRequest.addHeader("Authorization", "Bearer "+ TOKEN)
    httpUriRequest.addHeader("Accept", "application/json")
    this
  }
  def getGqlRequestResponse(queryString:String):this.type = {
    println(queryString)
    val pp = "{\"query\":\"" + queryString + "\"}"
    val gqlReq = new StringEntity(pp)

    httpUriRequest.setEntity(gqlReq)
    response = client.execute(httpUriRequest)
    this
  }

  def getJValue(): Option[JValue] ={
    // println("Response:" + response)
    response.getEntity match {
      case null => null
      case x if x != null => {
        val respJson = Source.fromInputStream(x.getContent).getLines.mkString

        //        println("response "+respJson)
        val jValue = parse(respJson)
        println("jval "+ jValue)
        response.close()
        Some(jValue)
      }
    }
  }
}
