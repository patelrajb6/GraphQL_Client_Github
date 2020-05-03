class QueryBuilder {    //the main querybuilder class
  private var query:String= "{"   // opening the query
  def setRepositoryOwnerQuery(repositoryOwner: RepositoryOwner):this.type={   //query for repositoryowner
    query=query+s"${repositoryOwner.build()} }"
    this
  }
  def setRepositoryQuery(repository: Repository):this.type ={     // query for repository
    query=query+s"${repository.build()} }"
    this
  }
  def setUserQuery(user: User):this.type={   //query for user
    query=query+s"${user.build()} }"
    this
  }
  def setTopicQuery(topic: Topic):this.type ={  //query for topic
    query+=s"${topic.build()}} "
    this
  }

  def build(): String ={    //the function which builds the whole query
    query
  }
}

