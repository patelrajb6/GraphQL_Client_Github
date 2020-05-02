trait CaseClass{}
case class RepositoryCase(description: Option[String], name: Option[String],
                       forkCount:Option[Int], hesIssuesEnabled:Option[Boolean],
                       hasProjectEnabled:Option[Boolean],hasWikiEnabled:Option[Boolean],
                       isArchived:Option[Boolean],isFork:Option[Boolean],
                       isLocked:Option[Boolean],isMirror:Option[Boolean],
                       isPrivate:Option[Boolean],nameWithOwner:Option[Boolean],
                       pushedAt:Option[String],updatedAt:Option[String],createdAt:Option[String]) extends CaseClass
case class Stargazers(totalCount: Option[Int], nodes: Option[Seq[UserCase]])             //not sure if List or Seq and also if should be a list of users...

case class UserCase(repository: Option[RepositoryCase], location: Option[String],name:Option[String],login:Option[String]) extends CaseClass
//----------------------------------------------------------------
case class TopicCase(name: Option[String],id: Option[String],stargazers: Option[Stargazers]) extends CaseClass
case class Data(repositoryOwner: Option[RepositoryOwnerCase],topic: Option[TopicCase], repository: Option[RepositoryCase],user:Option[UserCase])
case class RepositoryOwnerCase (repository: RepositoryCase) extends CaseClass
case class RootInterface (data: Data)
case class error(typed:String) extends CaseClass