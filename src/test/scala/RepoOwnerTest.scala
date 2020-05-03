import junit.framework.TestCase
import net.liftweb.json.DefaultFormats
import org.junit.Test
import org.junit.Assert._

class RepoOwnerTest extends TestCase{
  /*
  test uses the repository case class
   */
  @Test
  def testRepoOwner(): Unit ={
    val owner = "patelrajb6"
    val repo = "Baccarat_Game"
    implicit val formats = DefaultFormats
    val ownerdemo= new QueryBuilder()
      .setRepositoryOwnerQuery(new RepositoryOwner(owner)  //repository owner has repository nested
        .setRepository(new Repository(repo)    //needs name of the particular repository to search for
          .getcreatedAt().getname().gethasIssuesEnabled().getnameWithOwner())) //all the getter of repository
      .build()    //building the query

    val jVal = QueryObject.addHeaders().getGqlRequestResponse(ownerdemo).getJValue().get
    val repoObj = jVal \"data" \ "repositoryOwner" \ "repository"
    println(repoObj)
    val createdGot = repoObj.children(0).extract[String]              // the order is the same as from the builder call****
    val nameGot = repoObj.children(1).extract[String]
    val hasIssuesGot = repoObj.children(2).extract[Boolean]
    val nameWithOwnerGot = repoObj.children(3).extract[String]

    //now parse out with the case class
    val parseCase = repoObj.extract[RepositoryCase]

    //assert
    assertEquals(createdGot,parseCase.createdAt.get)
    assertEquals(nameGot,parseCase.name.get)
    assertEquals(hasIssuesGot,parseCase.hasIssuesEnabled.get)
    assertEquals(nameWithOwnerGot,parseCase.nameWithOwner.get)
  }



}
