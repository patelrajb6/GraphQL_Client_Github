class User{
  var repoQuery="{"

  def getLocation():User={
    repoQuery=repoQuery+" location "
    this
  }
  def getlogin():User={
    repoQuery=repoQuery+" login "
    this
  }
  def getname():User={
    repoQuery=repoQuery+" name "
    this
  }
  def setRepository(repository: Repository):this.type ={
    repoQuery=repoQuery+s"${repository.build()}"
    this
  }

  def build():String={
   repoQuery=repoQuery+"}"
   repoQuery
  }

}
