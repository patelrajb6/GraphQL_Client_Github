import junit.framework.TestCase
import net.liftweb.json.DefaultFormats
import org.junit.Test
import org.junit.Assert._

class RepoTest extends TestCase{
  implicit val formats = DefaultFormats

  @Test
  def testBasicRepoQuery(): Unit ={
    val owner = "patelrajb6"
    val repoName = "Baccarat_Game"

    //Repository only query
    val repoDemo= new QueryBuilder()
      .setRepositoryQuery(new Repository(owner,repoName)  //setting the query needs a repository object
        .getname().getpushedAt().getupdatedAt())//repository has name and description
      .build()

    val jVal = QueryObject.addHeaders().getGqlRequestResponse(repoDemo).getJValue().get
    val repoObj = jVal \ "data" \ "repository"
    val nameGot = repoObj.children(0).extract[String]
    val pushedGot = repoObj.children(1).extract[String]
    val updateGot = repoObj.children(2).extract[String]

    //extract
    val extracted = repoObj.extract[RepositoryCase]

    assertEquals(nameGot,extracted.name.get)
    assertEquals(pushedGot,extracted.pushedAt.get)
    assertEquals(updateGot,extracted.updatedAt.get)
  }
}
