import junit.framework.TestCase
import net.liftweb.json.DefaultFormats
import org.junit.Assert.assertTrue
import org.junit.Test

class RepoOwnerTest2 extends TestCase {
  @Test
  def testRepoOwner2(): Unit ={
    implicit val formats = DefaultFormats
    val owner = "ramirez915"
    val repo = "BioE-CS-494"
    val createdAtDemo = new QueryBuilder()
      .setRepositoryOwnerQuery(new RepositoryOwner(owner)
        .setRepository(new Repository(repo)
          .getcreatedAt().getDescription().getname()))
      .build()

    val jVal = QueryObject.addHeaders().getGqlRequestResponse(createdAtDemo).getJValue().get
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

}
