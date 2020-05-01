import com.typesafe.scalalogging.LazyLogging
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.HttpClientBuilder
import spray.json
import scala.io.Source
import net.liftweb.json._


object Main extends App with LazyLogging {

  logger.info("logging hello!!!!")
  implicit val formats = DefaultFormats
  val BASE_GHQL_URL = "https://api.github.com/graphql"
  val temp="{viewer {login url}}"
  //val TOKEN = "b24f584000aa47629266eac2857f9d0ab618ddde"        // ********** CHANGE TOKEN???
  val TOKEN = "8ed5131d2ab4a6d7704e87f3a23e202b6dfc3621"
  // maybe not if we can do queries with a specific name

  //the topic query builder
  val topicdemo= new QueryBuilder()
    .setTopicQuery(new Topic("android") //set the topic name
    .getname().setStarGrazers(new StargazerConnection(first = 100) //topic name consists of stargazers so its nested
    .gettotalCount().setNodeList(new User().getlogin()))) //stargazer contains total count and list of nodes which are user so nested again with login
    .build()  //building the whole query
  println(topicdemo)

  //REpository only query
  val repoDemo= new QueryBuilder()
    .setRepositoryQuery(new Repository("patelrajb6","Baccarat_Game")  //setting the query needs a repository object
      .getname().getDescription())  //repository has name and description
    .build()

  val ownerdemo= new QueryBuilder()
    .setRepositoryOwnerQuery(new RepositoryOwner("patelrajb6")  //repository owner has repository nested
      .setRepository(new Repository("Baccarat_Game")    //needs name of the particular repository to search for
        .getcreatedAt().getname().getDescription().gethasIssuesEnabled())) //all the getter of repository
    .build()    //building the query
//  println(ownerdemo)
 // println(repoDemo)
  val client = HttpClientBuilder.create.build
  val httpUriRequest = new HttpPost(BASE_GHQL_URL)
  httpUriRequest.addHeader("Authorization", "Bearer "+ TOKEN)
  httpUriRequest.addHeader("Accept", "application/json")
  val ss= "{\"query\":\"" + temp + "\"}"
//  val pp="{\"query\":\"" + topicdemo+ "\"}"
val pp="{\"query\":\"" + ownerdemo+ "\"}"
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
//      println((jValue\"data").extract[Data].repository.name.get)
//      println(jValue)
      println((jValue.extract[RootInterface]))
//      println(jValue)
      val y = json.JsonParser.apply(respJson)
      val q=y.prettyPrint
//      print(q)


    }
  }
}