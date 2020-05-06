import Builder.{QueryBuilder, Repository, StargazerConnection, Topic, User}
import GithubConnector.QueryObject
import Parser.JsonToScala
import com.typesafe.scalalogging.LazyLogging


object Main extends App with LazyLogging {
//we got 3 parts to our project   1 query builder 2 QueryRunner 3 Query to Scala Class
  val d= new QueryBuilder()
    .setTopicQuery(new Topic("android")
      .setStarGrazers(new StargazerConnection(first = 3)
        .setNodeList(new User().getname())))
    .build()

  val c= new QueryBuilder()
    .setRepositoryQuery(new Repository("patelrajb6","Baccarat_game")
      .getname())
    .build()
  println(c)
  val a= new QueryBuilder()
    .setUserQuery(new User()
      .getname()
      .setRepository(new Repository("Baccarat_game")
        .getname()))
    .build()
  println(a)
  val result= QueryObject().getGqlRequestResponse(d).getJValue()
  val scalacClass= new JsonToScala(result).getScalaClass()

 // println(d)

}