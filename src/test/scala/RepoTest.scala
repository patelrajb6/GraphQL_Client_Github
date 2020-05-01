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

  // change test to test repoOwner
  @Test
  def testCreatedAt(){
    val createdAtDemo = new QueryBuilder()
      .setRepositoryOwnerQuery(new RepositoryOwner("ramirez915")
        .setRepository(new Repository("BioE-CS-494")
          .getcreatedAt().getDescription().getname()))
      .build()

    val jVal = TestTools.getJValue(TestTools.getGqlRequestResponse(createdAtDemo)).get
    val createdAtObj = jVal \ "data" \ "repositoryOwner" \ "repository"
//    println("\nCREATED AT OBJ"+ createdAtObj)
    val createdAt = createdAtObj.children(0).extract[String]    // this extracts the created at value from jVal
    val description = createdAtObj.children(1).extract[String]
    val name = createdAtObj.children(2).extract[String]
//    println(createdAt)
//    println(description)
//    println(name)
    val root = jVal.extract[RootInterface]

    // assert what was queried for
    assertTrue(root.data.repositoryOwner.get.repository.createdAt.get == createdAt)
    assertTrue(root.data.repositoryOwner.get.repository.description.get == description)
    assertTrue(root.data.repositoryOwner.get.repository.name.get == name)
  }
}
