import com.typesafe.scalalogging.LazyLogging
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.HttpClientBuilder
import spray.json
import scala.io.Source
import net.liftweb.json._


object Main extends App with LazyLogging {
  println("hello")
  logger.info("logging hello!!!!")
  implicit val formats = DefaultFormats
  val BASE_GHQL_URL = "https://api.github.com/graphql"
  val temp="{viewer {login url}}"
 // val TOKEN = "b24f584000aa47629266eac2857f9d0ab618ddde"        // ********** CHANGE TOKEN???
  val TOKEN = "8ed5131d2ab4a6d7704e87f3a23e202b6dfc3621"
                                                            // maybe not if we can do queries with a specific name
  val demo= new QueryBuilder().setUser("patelrajb6").getUser(new User().getName().getlogin().build()).build()
  val repoDemo= new QueryBuilder()
    .setRepositoryOwnerandName("patelrajb6","Baccarat_Game")
    .getRepository(new Repository().getDescription().getname().build())
    .build()
  val ownerdemo= new QueryBuilder()
      .setRepositoryOwner("patelrajb6")
      .getRepositoryOwner(new RepositoryOwner().setRepository("Baccarat_Game").getnameWithOwner().getname().build()).build()
    println(ownerdemo)
  println(repoDemo)
  val client = HttpClientBuilder.create.build
  val httpUriRequest = new HttpPost(BASE_GHQL_URL)
  httpUriRequest.addHeader("Authorization", "Bearer "+ TOKEN)
  httpUriRequest.addHeader("Accept", "application/json")
  val ss= "{\"query\":\"" + temp + "\"}"
  val pp="{\"query\":\"" + repoDemo+ "\"}"
 // println (pp)
  val gqlReq = new StringEntity(pp )
//  val gqlReq = new StringEntity(ss )

  httpUriRequest.setEntity(gqlReq)

  val response = client.execute(httpUriRequest)
 // println("Response:" + response)
  response.getEntity match {
    case null => println("Response entity is null")
    case x if x != null => {
      //println("THIS IS X "+ x)
      val respJson = Source.fromInputStream(x.getContent).getLines.mkString

     System.out.println(respJson)
     val jValue = parse(respJson)
      println((jValue\"data").extract[Data].repository.name)
      println(jValue)
      val y = json.JsonParser.apply(respJson)
      val q=y.prettyPrint
      //print(q)


    }
  }
}