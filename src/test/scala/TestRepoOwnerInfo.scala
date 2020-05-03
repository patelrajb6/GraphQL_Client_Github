import junit.framework.TestCase
import net.liftweb.json.DefaultFormats
import org.junit.Assert.assertEquals
import org.junit.Test

class TestRepoOwnerInfo extends TestCase{
  @Test
  def testRepoOwnerInfo(): Unit ={
    implicit val formats = DefaultFormats
    val owner = "ramirez915"
    val repoOwnerInfoDemo = new QueryBuilder().setRepositoryOwnerQuery(new RepositoryOwner(owner).getLogin().getId()).build()
    val jVal = QueryObject.addHeaders().getGqlRequestResponse(repoOwnerInfoDemo).getJValue().get

    val repoOwnerObj = jVal \ "data" \ "repositoryOwner"
    print(repoOwnerObj.children(0))
    val ownerLogin = repoOwnerObj.children(0).extract[String]

    val ownerId = repoOwnerObj.children(1).extract[String]

    // extract and store
    val repoOwnerCc = repoOwnerObj.extract[RepositoryOwnerCase]

    //assert
    assertEquals(ownerLogin,repoOwnerCc.login.get)
    assertEquals(ownerId,repoOwnerCc.id.get)

  }
}
