import com.typesafe.scalalogging.LazyLogging
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.HttpClientBuilder
import spray.json
import scala.io.Source

object Main extends App with LazyLogging {
  println("hello")
  logger.info("logging hello!!!!")

  val BASE_GHQL_URL = "https://api.github.com/graphql"
  val temp="{viewer {email login url}}"
  val TOKEN = "b24f584000aa47629266eac2857f9d0ab618ddde"        // ********** CHANGE TOKEN???
                                                                // maybe not if we can do queries with a specific name

  val client = HttpClientBuilder.create.build
  val httpUriRequest = new HttpPost(BASE_GHQL_URL)
  httpUriRequest.addHeader("Authorization", "Bearer "+ TOKEN)
  httpUriRequest.addHeader("Accept", "application/json")
  val gqlReq = new StringEntity("{\"query\":\"" + temp + "\"}" )
  httpUriRequest.setEntity(gqlReq)

  val response = client.execute(httpUriRequest)
  println("Response:" + response)
  response.getEntity match {
    case null => println("Response entity is null")
    case x if x != null => {
      println("THIS IS X "+ x)
      val respJson = Source.fromInputStream(x.getContent).getLines.mkString
      System.out.println(respJson)
      val y = json.JsonParser.apply(respJson)
      y.prettyPrint

    }
  }
}