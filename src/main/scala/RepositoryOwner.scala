class RepositoryOwner(login:String){    //the Repository owner has a mandatory field of login
  private var loginName = "\\\""+login+"\\\""
  private var ownerquery=s"repositoryOwner(login:$loginName) { "  //setting login
  def setRepository(repository: Repository):RepositoryOwner={   // repository owner has nested repository
    ownerquery=ownerquery+s"${repository.build()} "
    this
  }

  def build():String={    //building the query for Repository owner
    ownerquery+="}"
    ownerquery
  }

}