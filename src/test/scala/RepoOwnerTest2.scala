import junit.framework.TestCase
import net.liftweb.json.DefaultFormats
import org.junit.Assert.assertTrue
import org.junit.Test

class RepoOwnerTest2 extends TestCase {
  private implicit val formats = DefaultFormats

  @Test
  def testRepoOwner2(): Unit ={
    // arrange variables
    val owner = "ramirez915"
    val repo = "BioE-CS-494"

    // building query
    val createdAtDemo = new QueryBuilder()
      .setRepositoryOwnerQuery(new RepositoryOwner(owner)
        .setRepository(new Repository(repo)
          .getcreatedAt().getDescription().getname()))
      .build()

    // extract data from queries
    val jVal = QueryObject().addHeaders().getGqlRequestResponse(createdAtDemo).getJValue()
    val createdAtObj = jVal \ "data" \ "repositoryOwner" \ "repository"
    val createdAt = createdAtObj.children(0).extract[String]    // this extracts the created at value from jVal
    val description = createdAtObj.children(1).extract[String]
    val name = createdAtObj.children(2).extract[String]

    // extract into case class
    val root = JsonToScala(jVal).getRepoOwnerQueryCaseClass()

    // assert what was queried for
    assertTrue(root.repository.createdAt.get == createdAt)
    assertTrue(root.repository.description.get == description)
    assertTrue(root.repository.name.get == name)
  }

}
