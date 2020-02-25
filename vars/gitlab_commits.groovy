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
    def commit=resultJson[i].author_email
println(commit)
    def collab = new String[100]
   //for(int i=0;i<=result1;i++)
   for i in 1 2 
   
      do
      collab[i]=resultJson[i].login
      println(collab[i])
      
    
    
  }
}
