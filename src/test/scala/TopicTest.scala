import junit.framework.TestCase
import net.liftweb.json.JsonAST.{JField, JValue}
import net.liftweb.json.{DefaultFormats, parse}
import org.apache.http.client.methods.{CloseableHttpResponse, HttpPost}
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.HttpClientBuilder
import org.junit.Test
import org.junit.Assert._

import scala.io.Source

class TopicTest extends TestCase{
  implicit val formats = DefaultFormats

  @Test
  def testTopics: Unit ={
    val topicName = "android"
    val firstAmount = 2
    //the topic query builder
    val topicdemo= new QueryBuilder()
      .setTopicQuery(new Topic(topicName) //set the topic name
        .getname().setStarGrazers(new StargazerConnection(first = firstAmount) //topic name consists of stargazers so its nested
        .gettotalCount().setNodeList(new User().getlogin()))) //stargazer contains total count and list of nodes which are user so nested again with login
      .build()  //building the whole query

    val jVal = TestTools.getJValue(TestTools.getGqlRequestResponse(topicdemo)).get
    val topicObj = jVal \ "data" \ "topic"
    //    println("\nTOPIC OBJ"+ topicObj)
    val stargazersCon = topicObj.children(1)                                //stargazerCon from response
    val stargazersCount = stargazersCon.children(0).extract[Int]           //get the stargazer count

    val root = jVal.extract[RootInterface]
    //    println(root.data.topic.get.stargazers.get.nodes.get(0).login)

    //assert
    assertTrue(root.data.topic.get.name.get == topicName)
    assertTrue(root.data.topic.get.stargazers.get.totalCount.get == stargazersCount)
    assertTrue(root.data.topic.get.stargazers.get.nodes.get.length == firstAmount)
  }
}
