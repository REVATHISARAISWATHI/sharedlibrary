import groovy.json.*
   long ids;
   def call(jsondata){
      def jsonString = jsondata
      def jsonObj = readJSON text: jsonString
     withCredentials([usernamePassword(credentialsId: 'gitlab_cred', passwordVariable: 'password', usernameVariable:'username')]) {
      sh "curl -X GET    -u $username:$password https://gitlab.com/api/v4/users/5418155/projects -o output.json"
     }
   def jsonSlurper = new JsonSlurper()
 def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/ouput.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
def usertotal = resultJson.size()
      println(usertotal)
      for(i=0;i<usertotal;i++)
         {
            if(jsonObj.scm.projects.project.project_name==resultJson[i].name)
            {
               def id1 = resultJson[i].id
               println(id1)
             return id1
            }
         }
   }


   def commit(ids){
      withCredentials([usernamePassword(credentialsId: 'gitlab_cred', passwordVariable: 'password', usernameVariable:'username')]) {
         sh "curl -X GET -i -H  -d  -u $username:$password https://gitlab.com/api/v4/projects/${id1}/merge_requests -o output.json"
      }
   def jsonSlurper = new JsonSlurper()
   def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/ouput.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
def total = resultJson.size()
   println(total)
}
