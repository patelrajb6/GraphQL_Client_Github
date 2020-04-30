case class summary()
case class Repository1(description: Option[String],
                       name: String,
                       forkcount:Option[Int])
case class User1(repository:Repository1, location:String)
case class Data(repository: Repository1)
case class R00tJsonObject(data: Data)