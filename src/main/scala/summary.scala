case class summary()
//trait CaseClass
case class Repository1(description: Option[String], name: Option[String],
                       forkCount:Option[Int], hesIssuesEnabled:Option[Boolean],
                       hasProjectEnabled:Option[Boolean],hasWikiEnabled:Option[Boolean],
                       isArchived:Option[Boolean],isFork:Option[Boolean],
                       isLocked:Option[Boolean],isMirror:Option[Boolean],
                       isPrivate:Option[Boolean],nameWithOwner:Option[Boolean],
                       pushedAt:Option[String],updatedAt:Option[String],createdAt:Option[String])
case class Stargazers(totalCount: Option[Int], nodes: Seq[User])             //not sure if List or Seq and also if should be a list of users...
case class Topic (id: Option[String], name: Option[String],
                  viewerHasStarred: Option[Boolean], stargazers: Seq[User])   // not sure if List or Seq and also list of users...

case class User1(repository:Repository1, location: Option[String])
case class Data(repository: Repository1)
case class R00tJsonObject(data: Data)