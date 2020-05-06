import Builder.{QueryBuilder, RepositoryOwner}
import GithubConnector.QueryObject
import junit.framework.TestCase
import net.liftweb.json.DefaultFormats
import org.junit.Assert.assertNull
import org.junit.Test

class InvalidOwnerTest extends TestCase{
  private implicit val formats = DefaultFormats

  @Test
  def testInvalidRepoOwner(): Unit ={
    // arrange the invalid information
    val invalidOwner = "perpita"      // invalid name i was able to find on github lol

    // build the invalid query
    val invalidDemo = new QueryBuilder().setRepositoryOwnerQuery(new RepositoryOwner(invalidOwner).getLogin().getId()).build()

    // get data from invalid query
    val jVal = QueryObject().addHeaders().getGqlRequestResponse(invalidDemo).getJValue()
    val invalidRespObj = jVal \ "data" \ "repositoryOwner"
    val mNull = invalidRespObj.values

    //assert null
    assertNull(mNull)
  }
}
