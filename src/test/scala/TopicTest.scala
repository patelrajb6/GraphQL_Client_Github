import Builder.{QueryBuilder, StargazerConnection, Topic, User}
import GithubConnector.QueryObject
import Parser.JsonToScala
import junit.framework.TestCase
import net.liftweb.json.DefaultFormats
import org.junit.Test
import org.junit.Assert._

class TopicTest extends TestCase{
  implicit val formats = DefaultFormats

  @Test
  def testTopics(): Unit ={
    // variables to use for query
    val topicName = "android"
    val firstAmount = 2

    //the topic query builder
    val topicdemo= new QueryBuilder()
      .setTopicQuery(new Topic(topicName) //set the topic name
        .getname().setStarGrazers(new StargazerConnection(first = firstAmount) //topic name consists of stargazers so its nested
        .gettotalCount().setNodeList(new User().getlogin()))) //stargazer contains total count and list of nodes which are user so nested again with login
      .build()  //building the whole query

    // extract values from query to use for comparison
    val jVal =   QueryObject().addHeaders().getGqlRequestResponse(topicdemo).getJValue()
    val topicObj = jVal \ "data" \ "topic"
    val stargazersCon = topicObj.children(1)                                //stargazerCon from response
    val stargazersCount = stargazersCon.children(0).extract[Int]           //get the stargazer count

    // parse jvalue and put it into the case class
    val root = JsonToScala(jVal).getTopicQueryCaseClass()

    //assert
    assertTrue(root.name.get == topicName)
    assertTrue(root.stargazers.get.totalCount.get == stargazersCount)
    assertTrue(root.stargazers.get.nodes.get.length == firstAmount)
  }
}