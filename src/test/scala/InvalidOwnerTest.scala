import junit.framework.TestCase
import net.liftweb.json.DefaultFormats
import org.junit.Assert.assertNull
import org.junit.Test

class InvalidOwnerTest extends TestCase{
  @Test
  def testInvalidRepoOwner(): Unit ={
    implicit val formats = DefaultFormats
    val invalidOwner = "perpita"      // invalid name i was able to find on github lol
    val invalidDemo = new QueryBuilder().setRepositoryOwnerQuery(new RepositoryOwner(invalidOwner).getLogin().getId()).build()
    val jVal = QueryObject().addHeaders().getGqlRequestResponse(invalidDemo).getJValue()

    val invalidRespObj = jVal \ "data" \ "repositoryOwner"
    val mNull = invalidRespObj.values

    assertNull(mNull)
  }
}
