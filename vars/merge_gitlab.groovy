import groovy.json.*
import groovy.json.JsonSlurper 
//int ids1;

def call(jsondata){
      def jsonString = jsondata
      def jsonObj = readJSON text: jsonString
      String a=jsonObj.scm1.projects.project.project_name
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
            if(Name==resultJson[i].name)
            {
               def id1 = resultJson[i].id 
               println(id1)
             return id1
            }
         }
         }
def commit(ids1){
      println(ids1)
      withCredentials([usernamePassword(credentialsId: 'gitlab_cred', passwordVariable: 'password', usernameVariable:'username')]) {
         sh "curl -X GET   -u $username:$password https://gitlab.com/api/v4/projects/${ids1}/merge_requests -o output.json"
      }
   def jsonSlurper = new JsonSlurper()
   def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/output.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
def total = resultJson.size()
   println(total)
      def jsonBuilder = new groovy.json.JsonBuilder()
       jsonBuilder.gitlab(
  "merge" : resultJson,
  "merge_cnt" : resultJson.size(),
      //println(JsonOutput.toJson(resultJson))
)
File file = new File("/var/lib/jenkins/workspace/${JOB_NAME}/merge.json")
	file.write(jsonBuilder.toPrettyString())
}
