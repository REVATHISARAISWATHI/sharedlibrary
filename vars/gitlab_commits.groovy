import groovy.json.*
def call()
{
  sh "curl -X GET -s -u Priyakumar:pri4jay16 https://gitlab.com/api/v4/projects/17097955/repository/commits -o ouput.json"
  def jsonSlurper = new JsonSlurper()
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/ouput.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
  def size = resultJson.size()
  println(size)
  for(i=0;i<size;i++){
    //def commit=resultJson[i].author_email
println(resultJson[i])
   /*commit[i]=resultJson[i].author_email
    println(commit[i])*/
     def count = new String[100]
   for(int j=0;j<size;j++)
    {
      count[j]=0
   for(int i=0;i<size;i++){
      //if((resultJson.commit.author.name[i]).equals( collab[j] ))
         if((resultJson.commit.author.name[i]).equals( collab[j] ))
     count[j]++
       println(count)
         }
         } 
      
    
    
  }
}
