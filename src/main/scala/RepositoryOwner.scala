class RepositoryOwner(login:String){
  var loginName = "\\\""+login+"\\\""
  var ownerquery=s"repositoryOwner(login:$loginName) { "
  def setRepository(repository: Repository):RepositoryOwner={
    ownerquery=ownerquery+s"${repository.build()} "
    this
  }

  def build():String={
    ownerquery+="}"
    ownerquery
  }

}