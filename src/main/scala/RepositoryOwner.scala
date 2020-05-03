import com.typesafe.scalalogging.LazyLogging

class RepositoryOwner(login:String) extends LazyLogging{    //the Repository owner has a mandatory field of login
  logger.debug("RepositoryOwner object is created.")
  private var loginName = "\\\""+login+"\\\""
  private var ownerquery=s"repositoryOwner(login:$loginName) { "  //setting login
  def setRepository(repository: Repository):RepositoryOwner={   // repository owner has nested repository
    ownerquery=ownerquery+s"${repository.build()} "
    this
  }

  // added to be able to get the login info and id of repo owner
  def getLogin(): RepositoryOwner ={
    ownerquery=ownerquery+" login "
    this
  }

  def getId(): RepositoryOwner ={
    ownerquery=ownerquery+" id "
    this
  }

  def build():String={    //building the query for Repository owner
    ownerquery+="}"
    ownerquery
  }

}