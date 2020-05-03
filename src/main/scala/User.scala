class User{   //the user class
  private var repoQuery="{"

  //attributes that can be grabbed from the api
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
  def setRepository(repository: Repository):this.type ={    //has nested repository
    repoQuery=repoQuery+s"${repository.build()}"
    this
  }

  def build():String={
   repoQuery=repoQuery+"}"
   repoQuery
  }

}
