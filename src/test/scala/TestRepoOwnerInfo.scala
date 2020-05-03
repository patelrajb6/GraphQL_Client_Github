import junit.framework.TestCase
import net.liftweb.json.DefaultFormats
import org.junit.Assert.assertEquals
import org.junit.Test

class TestRepoOwnerInfo extends TestCase{
  private implicit val formats = DefaultFormats

  @Test
  def testRepoOwnerInfo(): Unit ={
    // set up variable to use for query
    val owner = "ramirez915"

    // build query
    val repoOwnerInfoDemo = new QueryBuilder().setRepositoryOwnerQuery(new RepositoryOwner(owner).getLogin().getId()).build()
    val jVal = QueryObject().addHeaders().getGqlRequestResponse(repoOwnerInfoDemo).getJValue()

    // extract data to compare
    val repoOwnerObj = jVal \ "data" \ "repositoryOwner"
    val ownerLogin = repoOwnerObj.children(0).extract[String]
    val ownerId = repoOwnerObj.children(1).extract[String]

    // extract and store to case class
    val repoOwnerCc = JsonToScala(jVal).getRepoOwnerQueryCaseClass()

    //assert
    assertEquals(ownerLogin,repoOwnerCc.login.get)
    assertEquals(ownerId,repoOwnerCc.id.get)
  }
}
