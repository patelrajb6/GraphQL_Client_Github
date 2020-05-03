trait RepositoryInfo[T<:RepositoryInfo[T]] {
  //templated type traits so that it returns the type of the subclass
  protected var repoQuery=" "
  def getcreatedAt(): T
  def getDescription(): T
  def getForkCount(): T
  def gethasIssuesEnabled(): T
  def gethasProjectEnabled(): T
  def gethasWikiEnabled(): T
  def getisArchived(): T
  def getisFork(): T
  def getisLocked(): T
  def getisMirror(): T
  def getisPrivate(): T
  def getname(): T
  def getnameWithOwner(): T
  def getpushedAt(): T
  def getupdatedAt(): T
  def build():String

}