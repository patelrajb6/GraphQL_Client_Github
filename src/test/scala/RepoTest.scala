import junit.framework.TestCase
import net.liftweb.json.JsonAST.{JField, JValue}
import net.liftweb.json.{DefaultFormats, parse}
import org.apache.http.client.methods.{CloseableHttpResponse, HttpPost}
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.HttpClientBuilder
import org.junit.Test
import org.junit.Assert._

import scala.io.Source
class RepoTest extends TestCase{
  implicit val formats = DefaultFormats
  val BASE_GHQL_URL = "https://api.github.com/graphql"
  // val TOKEN = "b24f584000aa47629266eac2857f9d0ab618ddde"        // ********** CHANGE TOKEN???
  val TOKEN = "8ed5131d2ab4a6d7704e87f3a23e202b6dfc3621"
  val client = HttpClientBuilder.create.build
  val httpUriRequest = new HttpPost(BASE_GHQL_URL)
  httpUriRequest.addHeader("Authorization", "Bearer "+ TOKEN)
  httpUriRequest.addHeader("Accept", "application/json")

  @Test
  def testCreatedAt(){
    val createdAtDemo = new QueryBuilder()
      .setRepositoryOwnerandName("ramirez915","BioE-CS-494")
      .getRepository(new Repository().getcreatedAt().getDescription().getname().build()).build()

    val jVal = getJValue(getGqlRequestResponse(createdAtDemo)).get
    val createdAtObj = jVal.\("data").\("repository").\\("createdAt")
    println("\nCREATED AT OBJ"+ createdAtObj)
    val createdAt = createdAtObj.children(0).extract[String]    // this extracts the created at value from jVal
    val root = jVal.extract[R00tJsonObject]
    assertTrue(root.data.repository.createdAt.get == createdAt)
  }

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

        println("response "+respJson)
        val jValue = parse(respJson)
        println("jval "+ jValue)
        Some(jValue)
      }
    }
  }
}
