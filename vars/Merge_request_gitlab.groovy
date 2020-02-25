  def call(){
   // withCredentials([usernamePassword(credentialsId: 'nexus_cred', passwordVariable: 'password', usernameVariable:'username')]) {
sh "curl -X GET -i -H  -d  -u Priyakumar:pri4jay16 https://gitlab.com/api/v4/projects/17097955/merge_requests -o output.json"
   def jsonSlurper = new JsonSlurper()
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/ouput.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
def total = resultJson.size()
   println(total)
}
