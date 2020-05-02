import net.liftweb.json.JsonAST.JValue
import net.liftweb.json.{DefaultFormats, parse}
import org.apache.http.client.methods.{CloseableHttpResponse, HttpPost}
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.HttpClientBuilder

import scala.io.Source

object Tools {
  implicit val formats = DefaultFormats
  val BASE_GHQL_URL = "https://api.github.com/graphql"
  // val TOKEN = "b24f584000aa47629266eac2857f9d0ab618ddde"
  val TOKEN = "8ed5131d2ab4a6d7704e87f3a23e202b6dfc3621"
  val client = HttpClientBuilder.create.build
  val httpUriRequest = new HttpPost(BASE_GHQL_URL)
  httpUriRequest.addHeader("Authorization", "Bearer "+ TOKEN)
  httpUriRequest.addHeader("Accept", "application/json")

  def getGqlRequestResponse(queryString:String):CloseableHttpResponse = {
    println(queryString)
    val pp = "{\"query\":\"" + queryString + "\"}"
    val gqlReq = new StringEntity(pp)

    httpUriRequest.setEntity(gqlReq)
    val response = client.execute(httpUriRequest)
    response
  }

  def getJValue(response: CloseableHttpResponse):  Option[JValue] ={
    // println("Response:" + response)
    response.getEntity match {
      case null => null
      case x if x != null => {
        val respJson = Source.fromInputStream(x.getContent).getLines.mkString

        //        println("response "+respJson)
        val jValue = parse(respJson)
        println("jval "+ jValue)
        Some(jValue)
      }
    }
  }
}
