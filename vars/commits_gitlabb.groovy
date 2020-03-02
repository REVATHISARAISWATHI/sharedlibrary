import groovy.json.*
import groovy.json.JsonSlurper 
//int ids1;

def call(jsondata){
      def jsonString = jsondata
      def jsonObj = readJSON text: jsonString
      String a=jsonObj.scm.projects.project.project_name
String Name=a.replaceAll("\\[", "").replaceAll("\\]","");
     withCredentials([usernamePassword(credentialsId: 'gitlab_cred', passwordVariable: 'password', usernameVariable:'username')]) {
      sh "curl -X GET    -u $username:$password https://gitlab.com/api/v4/users/5418155/projects -o output.json"
     }
   def jsonSlurper = new JsonSlurper()
 def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/output.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
def usertotal = resultJson.size()
      println(usertotal)
      println(Name)
      for(i=0;i<usertotal;i++)
         {
            if(Name==resultJson[i].name[j])
            {
               def id1 = resultJson[i].id[i] 
               println(id1,id2)
             return id1,id2
            }
         }
         }
def commit(ids1,jsondata){
	def jsonString = jsondata
def jsonObj = readJSON text: jsonString
      println(ids1)
	int ecount = jsonObj.config.emails.email.size()
         println("No of users "+ ecount)
      withCredentials([usernamePassword(credentialsId: 'gitlab_cred', passwordVariable: 'password', usernameVariable:'username')]) {
	      sh "curl -X GET   -u $username:$password https://gitlab.com/api/v4/projects/${ids1}/repository/commits -o output.json"
      }
   def jsonSlurper = new JsonSlurper()
   def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/output.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
def total = resultJson.size()
   println(total)
	println(ecount)
      //println(JsonOutput.toJson(resultJson))
      List<String> JSON = new ArrayList<String>();
      List<String> JCOPY = new ArrayList<String>();
	Map<String, List<String>> map = new HashMap<String, List<String>>();
def cnt=0
for(i=0;i<ecount;i++)
 {
	 def mail=jsonObj.config.emails.email[i][j]
  for(j=0;j<total;j++)
  {
	 // println(jsonObj.config.emails.email[i])
	 // println(resultJson[j].author_email)
   if(jsonObj.config.emails.email[i]==resultJson[j].author_email[k])
   {
	   JSON.add(resultJson[j])
	   cnt++
     }
     }
	 println(jsonObj.config.emails.email[i][j])
	 JCOPY[i][k]=(JsonOutput.toJson(JSON))
	 println(JCOPY[i][k])
	 map.get(mail,JCOPY[i])
	 USER.clear()
	 
	   
     }
  for(i=0;i<JCOPY.size();i++)
	{
		println(JCOPY[i])
	}
	println(map)
	 def jsonBuilder = new groovy.json.JsonBuilder()
 jsonBuilder.gitlab(
  "commit" : resultJson,
  "commit_cnt" : resultJson.size()
  
  )
 println(jsonBuilder)
     
}
