import groovy.json.*
def call(IP)
{
  sh "curl -X GET -s -u Priyakumar:pri4jay16 https://gitlab.com/api/v4/projects/17097955/repository/contributors -o ouput.json"
  def jsonSlurper = new JsonSlurper()
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/ouput.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
  def size = resultJson.size()
  for(i=0;i<size;i++){
def uname=resultJson[i].name
  println(uname)
def commit=resultJson[i].commits
println(commit)
  }
  //ef val=arr  { reg
  //println(val)
}
