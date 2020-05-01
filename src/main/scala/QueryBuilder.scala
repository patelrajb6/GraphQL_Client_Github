class QueryBuilder {
  private var query:String= "{"
  def setRepositoryOwnerQuery(repositoryOwner: RepositoryOwner):this.type={
    query=query+s"${repositoryOwner.build()} }"
    this
  }
  def setRepositoryQuery(repository: Repository):this.type ={
    query=query+s"${repository.build()} }"
    this
  }
  def setUserQuery(user: User):this.type={
    query=query+s"${user.build()} }"
    this
  }
  def setTopicQuery(topic: Topic):this.type ={
    query+=s"${topic.build()}} "
    this
  }

  def build(): String ={
    query
  }
}