case class summary()
trait CaseClass
case class Repository1(description: Option[String], name: Option[String],
                       forkCount:Option[Int], hesIssuesEnabled:Option[Boolean],
                       hasProjectEnabled:Option[Boolean],hasWikiEnabled:Option[Boolean],
                       isArchived:Option[Boolean],isFork:Option[Boolean],
                       isLocked:Option[Boolean],isMirror:Option[Boolean],
                       isPrivate:Option[Boolean],nameWithOwner:Option[Boolean],
                       pushedAt:Option[String],updatedAt:Option[String],createdAt:Option[String]) extends CaseClass
case class Stargazers(totalCount: Option[Int], nodes: Option[Seq[User1]])             //not sure if List or Seq and also if should be a list of users...

case class User1(repository: Option[Repository1], location: Option[String],name:Option[String],login:Option[String])
//----------------------------------------------------------------
case class TopicCase(name: Option[String],id: Option[String],stargazers: Option[Stargazers])
case class Data(repositoryOwner: Option[RepositoryOwner1],topic: Option[TopicCase])
case class RepositoryOwner1 (repository: Repository1)
case class RootInterface (data: Data)