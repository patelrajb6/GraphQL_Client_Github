class Repository(owner:String,name:String) extends RepositoryInfo[Repository] {

  def this( name:String)={
    this(null,name)
  }
  var name1= "\\\""+name+"\\\""
  var owner1= "\\\""+owner+"\\\""
  owner match {
    case null=> repoQuery=repoQuery+s"repository(name:${name1}) {"
    case _=>repoQuery=repoQuery+s"repository(owner:$owner1,name:${name1}) {"
  }

  def getcreatedAt(): Repository ={
    repoQuery=repoQuery+" createdAt "
    this
  }
  def getDescription(): Repository ={
    repoQuery=repoQuery+" description "
    this
  }
  def getForkCount(): Repository ={
    repoQuery=repoQuery+ " forkCount "
    this
  }
  def gethasIssuesEnabled(): Repository ={
    repoQuery=repoQuery+ " hasIssuesEnabled "
    this
  }
  def gethasProjectEnabled(): Repository ={
    repoQuery=repoQuery+ " hasProjectEnabled "
    this
  }
  def gethasWikiEnabled(): Repository ={
    repoQuery=repoQuery+ " hasWikiEnabled "
    this
  }

  def getisArchived(): Repository ={
    repoQuery=repoQuery+ " isArchived "
    this
  }
  def getisFork(): Repository ={
    repoQuery=repoQuery+ "isFork "
    this
  }
  def getisLocked(): Repository ={
    repoQuery=repoQuery+ " isLocked "
    this
  }
  def getisMirror(): Repository ={
    repoQuery=repoQuery+ " isMirror "
    this
  }
  def getisPrivate(): Repository ={
    repoQuery=repoQuery+ " isPrivate "
    this
  }
  def getname(): Repository ={
    repoQuery=repoQuery+ " name "
    this
  }
  def getnameWithOwner(): Repository ={
    repoQuery=repoQuery+ " nameWithOwner "
    this
  }

  def getpushedAt(): Repository ={
    repoQuery=repoQuery+ " pushedAt "
    this
  }

  def getupdatedAt(): Repository ={
    repoQuery=repoQuery+ " updatedAt "
    this
  }

  def build():String={
    repoQuery=repoQuery+"}"
    repoQuery
  }

}