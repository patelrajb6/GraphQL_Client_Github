import junit.framework.TestCase
import net.liftweb.json.DefaultFormats
import org.junit.Test
import org.junit.Assert._

class RepoOwnerTest extends TestCase{
  implicit val formats = DefaultFormats

  /*
  test uses the repository case class
   */
  @Test
  def testRepoOwner(): Unit ={
    val owner = "patelrajb6"
    val repo = "Baccarat_Game"

    val ownerdemo= new QueryBuilder()
      .setRepositoryOwnerQuery(new RepositoryOwner(owner)  //repository owner has repository nested
        .setRepository(new Repository(repo)    //needs name of the particular repository to search for
          .getcreatedAt().getname().gethasIssuesEnabled().getnameWithOwner())) //all the getter of repository
      .build()    //building the query

    val jVal = Tools.getJValue(Tools.getGqlRequestResponse(ownerdemo)).get
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

  /*
  test used the root case class
   */
  @Test
  def testRepoOwner2(): Unit ={
    val owner = "ramirez915"
    val repo = "BioE-CS-494"
    val createdAtDemo = new QueryBuilder()
      .setRepositoryOwnerQuery(new RepositoryOwner(owner)
        .setRepository(new Repository(repo)
          .getcreatedAt().getDescription().getname()))
      .build()

    val jVal = Tools.getJValue(Tools.getGqlRequestResponse(createdAtDemo)).get
    val createdAtObj = jVal \ "data" \ "repositoryOwner" \ "repository"
    val createdAt = createdAtObj.children(0).extract[String]    // this extracts the created at value from jVal
    val description = createdAtObj.children(1).extract[String]
    val name = createdAtObj.children(2).extract[String]

    // extract into case class
    val root = jVal.extract[RootInterface]

    // assert what was queried for
    assertTrue(root.data.repositoryOwner.get.repository.createdAt.get == createdAt)
    assertTrue(root.data.repositoryOwner.get.repository.description.get == description)
    assertTrue(root.data.repositoryOwner.get.repository.name.get == name)
  }

  /*
  will test getting the repoowner info

   */
  @Test
  def testRepoOwnerInfo(): Unit ={
    val owner = "ramirez915"
    val repoOwnerInfoDemo = new QueryBuilder().setRepositoryOwnerQuery(new RepositoryOwner(owner).getLogin().getId()).build()
    val jVal = Tools.getJValue(Tools.getGqlRequestResponse(repoOwnerInfoDemo)).get

    val repoOwnerObj = jVal \ "data" \ "repositoryOwner"
    val ownerLogin = repoOwnerObj.children(0).extract[String]
    val ownerId = repoOwnerObj.children(1).extract[String]

    // extract and store
    val repoOwnerCc = repoOwnerObj.extract[RepositoryOwnerCase]

    //assert
    assertEquals(ownerLogin,repoOwnerCc.login.get)
    assertEquals(ownerId,repoOwnerCc.id.get)

  }

  /*
  Tests for an invalid repo owner
   */
  @Test
  def testInvalidRepoOwner(): Unit ={
    val invalidOwner = "perpita"      // invalid name i was able to find on github lol
    val invalidDemo = new QueryBuilder().setRepositoryOwnerQuery(new RepositoryOwner(invalidOwner).getLogin().getId()).build()
    val jVal = Tools.getJValue(Tools.getGqlRequestResponse(invalidDemo)).get

    val invalidRespObj = jVal \ "data" \ "repositoryOwner"
    val mNull = invalidRespObj.values

    assertNull(mNull)
  }
}
