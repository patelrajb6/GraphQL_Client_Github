class RepositoryInfo {
  private var repoQuery="{ "
  def getcreatedAt(): RepositoryInfo ={
    repoQuery=repoQuery+" createdAt "
    this
  }
  def getDescription(): RepositoryInfo ={
    repoQuery=repoQuery+" description "
    this
  }
  def getForkCount(): RepositoryInfo ={
    repoQuery=repoQuery+ " forkCount "
    this
  }
  def gethasIssuesEnabled(): RepositoryInfo ={
    repoQuery=repoQuery+ "hasIssuesEnabled "
    this
  }
  def gethasProjectEnabled(): RepositoryInfo ={
    repoQuery=repoQuery+ "hasProjectEnabled "
    this
  }
  def gethasWikiEnabled(): RepositoryInfo ={
    repoQuery=repoQuery+ "hasWikiEnabled "
    this
  }

  def getisArchived(): RepositoryInfo ={
    repoQuery=repoQuery+ "isArchived "
    this
  }
  def getisFork(): RepositoryInfo ={
    repoQuery=repoQuery+ "isFork "
    this
  }
  def getisLocked(): RepositoryInfo ={
    repoQuery=repoQuery+ "isLocked "
    this
  }
  def getisMirror(): RepositoryInfo ={
    repoQuery=repoQuery+ "isMirror "
    this
  }
  def getisPrivate(): RepositoryInfo ={
    repoQuery=repoQuery+ "isPrivate "
    this
  }
  def getname(): RepositoryInfo ={
    repoQuery=repoQuery+ "name "
    this
  }
  def getnameWithOwner(): RepositoryInfo ={
    repoQuery=repoQuery+ "nameWithOwner "
    this
  }

  def getpushedAt(): RepositoryInfo ={
    repoQuery=repoQuery+ "pushedAt "
    this
  }

  def getupdatedAt(): RepositoryInfo ={
    repoQuery=repoQuery+ "updatedAt "
    this
  }

  def build():String={
    repoQuery=repoQuery+" }"
    repoQuery
  }

}
