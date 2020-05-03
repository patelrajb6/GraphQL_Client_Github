import junit.framework.TestCase
import net.liftweb.json.DefaultFormats
import org.junit.Test
import org.junit.Assert._

class RepoOwnerTest extends TestCase{
  private implicit val formats = DefaultFormats

  /*
  test uses the repository case class
   */
  @Test
  def testRepoOwner(): Unit ={
    //arrange values to use for query
    val owner = "patelrajb6"
    val repo = "Baccarat_Game"

    // build query with values
    val ownerdemo= new QueryBuilder()
      .setRepositoryOwnerQuery(new RepositoryOwner(owner)  //repository owner has repository nested
        .setRepository(new Repository(repo)    //needs name of the particular repository to search for
          .getcreatedAt().getname().gethasIssuesEnabled().getnameWithOwner())) //all the getter of repository
      .build()    //building the query

    // extract data from query to use for checking
    val jVal = QueryObject().addHeaders().getGqlRequestResponse(ownerdemo).getJValue()
    val repoObj = jVal \"data" \ "repositoryOwner" \ "repository"
    val createdGot = repoObj.children(0).extract[String]              // the order is the same as from the builder call****
    val nameGot = repoObj.children(1).extract[String]
    val hasIssuesGot = repoObj.children(2).extract[Boolean]
    val nameWithOwnerGot = repoObj.children(3).extract[String]

    //now parse out with the case class
    val parseCase = JsonToScala(jVal).getRepoOwnerQueryCaseClass().repository

    //assert
    assertEquals(createdGot,parseCase.createdAt.get)
    assertEquals(nameGot,parseCase.name.get)
    assertEquals(hasIssuesGot,parseCase.hasIssuesEnabled.get)
    assertEquals(nameWithOwnerGot,parseCase.nameWithOwner.get)
  }
}
