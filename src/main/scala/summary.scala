case class summary()
trait CaseClass
case class RepositoryCase(description: Option[String], name: Option[String],
                       forkCount:Option[Int], hasIssuesEnabled:Option[Boolean],
                       hasProjectEnabled:Option[Boolean],hasWikiEnabled:Option[Boolean],
                       isArchived:Option[Boolean],isFork:Option[Boolean],
                       isLocked:Option[Boolean],isMirror:Option[Boolean],
                       isPrivate:Option[Boolean],nameWithOwner:Option[String],
                       pushedAt:Option[String],updatedAt:Option[String],createdAt:Option[String]) extends CaseClass
case class StargazersCase(totalCount: Option[Int], nodes: Option[Seq[UserCase]])             //not sure if List or Seq and also if should be a list of users...

case class UserCase(repository: Option[RepositoryCase], location: Option[String],name:Option[String],login:Option[String])
//----------------------------------------------------------------
case class TopicCase(name: Option[String],id: Option[String],stargazers: Option[StargazersCase])
case class Data(repositoryOwner: Option[RepositoryOwnerCase],topic: Option[TopicCase])
case class RepositoryOwnerCase(repository: RepositoryCase,login: Option[String],id: Option[String])
case class RootInterface (data: Data)