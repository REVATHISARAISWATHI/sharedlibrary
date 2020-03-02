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
            if(Name==resultJson[i].name)
            {
               def id1 = resultJson[i].id .toInteger()
                  
               println(id1)
             return id1
                  
            }
         }
      
   }

//@NonCPS
def commit(ids1){
      println(ids1)
      withCredentials([usernamePassword(credentialsId: 'gitlab_cred', passwordVariable: 'password', usernameVariable:'username')]) {
         sh "curl -X GET -i -H  -d  -u $username:$password https://gitlab.com/api/v4/projects/${ids1}/merge_requests -o output.json"
      }
   def jsonSlurper = new JsonSlurper()
   def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/output.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
def total = resultJson.size()
   println(total)
      // List<String> JSON = new ArrayList<String>();
      def jsonBuilder = new groovy.json.JsonBuilder()
     /* for(i=0;i<ecount;i++)
 {
	def email=jsonObj.config.emails.email[i] 
  for(j=0;j<total;j++)
  {
	 // println(jsonObj.config.emails.email[i])
	 // println(resultJson[j].author_email)
   if(email==resultJson[j].author_email)
   {
	   JSON.add(JsonOutput.toJson(resultJson[j]))
	  
     }
     }*/
        jsonBuilder.gitlab(
  "merge" : resultJson,
  "merge_cnt" : resultJson.size(),
	// "individual":LIST
  
  )
File file = new File("/var/lib/jenkins/workspace/${JOB_NAME}/output1.json")
	file.write(jsonBuilder.toPrettyString())
}


