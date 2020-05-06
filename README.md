## Project Overview  
## Contributors: 
    Raj Patel
    Angel Ramirez
    Samarth Patel
## Goal: Creating a small  Scala GraphQL Client framework for Github API 
### Description: 
    This framework consists of 3 parts.
    1. Query making
        * The Queries are built with Builder Design Pattern which is abstracting the raw String from the Builder.User 
    2. executing the query and getting the results from the GitHub
        * The Query made by the user is then passed to the GithubConnector.QueryObject where the Query is executed and http response is recieved
    3. Parsing the Queries into Scala Case Classes.
        * The http response is checked for errors and then sent for parsing of json and json is extracted into particular Scala Case Class.

### Supported Commands :

    * User Query-> Gives information about the current user.
      * getLocation -> gives the location if available else null
      * getlogin-> gives the github username
      * getname -> gives the name of the user if available
      * setRepository(Repository) -> set the Repository using its name in order to get information about that particular Repo
  
    * Repository Query -> Gives information about any Github Repository.  ********requires (login , repository name)******
      * description: Option[String] 
      * name: Option[String]
      * forkCount: Option[Int]
      * hasIssuesEnabled: Option[Boolean]
      * hasProjectEnabled: Option[Boolean]
      * hasWikiEnabled: Option[Boolean]
      * isArchived: Option[Boolean]
      * isFork: Option[Boolean]
      * isLocked: Option[Boolean]
      * isMirror: Option[Boolean]
      * isPrivate: Option[Boolean]
      * nameWithOwner: Option[String]
      * pushedAt: Option[String]
      * updatedAt: Option[String]
      * createdAt: Option[String]
  
    * Repository Owner Query -> Gives information about any Builder.Repository owner.  ********requires(login)********
      * repository: Repository (from Repository Query). *****requires(repo name)******
      * login: Option[String]
      * id: Option[String]
  
    * Topic Query -> Gives information about a particular Topic on Github.     ******requires (Topic name)*******
      * name: Option[String]
      * id: Option[String]
      * stargazers: Option[Stargazers] -> ******requires (first | last | after | before) any one of the parameter*****
        * totalCount: Option[Int]
        * nodes: Option[Seq[Builder.User]]

    Methods are with set and get prefix used in the frame work (Exceptions: Stargazer, Repository):
    * Set prefix means the below required arguments needs to set first in order to get info.
    * Get prefix will be used to get the information.
    * For setting stargazer, repository in subquery, constructor can be used directly for setting the value.
  

### Example Queries:  
    1. User Query
        code :  
            new QueryBuilder()  
                .setUserQuery(new User()  
                    .getname()  
                    .setRepository(new Repository(X)  
                        .getname()))  
            .build()  
        Query Generated: {  
                            { name    
                              repository(name:\"X\")   
                                { name   
                                }  
                            }   
                        }  
    2. Topic Query  
     code :  
          new QueryBuilder()  
                .setTopicQuery(new Topic("android")
                    .setStarGrazers(new StargazerConnection(first = 3)
                        .setNodeList(new User().getname())))  
            .build()   
        Query Generated: {
                            topic(name:\"android\") 
                            { stargazers(first:3, ) 
                                { nodes 
                                    { name }
                                } 
                            }
                        } 
    3. Repository Query
    code :  
          new QueryBuilder()  
                .setRepositoryQuery(new Builder.Repository("X","Y")
                    .getname()) 
            .build() ```  
        Query Generated: { 
                            repository(owner:X,name:Y)
                             { name } 
                        }
    4. Repository Owner Query
    code :  
          new QueryBuilder()  
                .setRepositoryOwnerQuery(RepositoryOwner(Y)  
                    .setRepository(Repository(X)    
                        .getcreatedAt().getname().gethasIssuesEnabled().getnameWithOwner()))
            .build()   
        Query Generated: {
                            repositoryOwner(login:\"Y\") {  
                                repository(name:\"X\") { 
                                    createdAt  
                                    name  
                                    hasIssuesEnabled  
                                    nameWithOwner 
                                } 
                            }   
                        }    
      
### Using the Framework:  
    1. Download or Clone the Repository  
    2. Use SBT Tool to check the testing of framework(optional)  
    3. Using SBT on commandline   
        * sbt clean compile test (to run the tests)
        * sbt package (if you want to use it as jar file and import it)

### New Things Learned: 
    1.  Scala for programming.
        1.  Traits
        2.  Case Classes
        3.  Options[]
        4.  pattern matching
        5.  Types
        6.  Objects
    2. SBT build Tool
    3. GraphQL 

### Additional Information:
    ScalaDocs directory of this repo has all the information about methods.(download the repo and open index.html
    with Browser)

