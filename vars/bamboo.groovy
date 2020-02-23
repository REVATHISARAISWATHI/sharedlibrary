import groovy.json.*
def call(IP)
{
  sh "curl -X GET -s -u rig:rigaDapt@devOps ${IP}/rest/api/latest/result/LAT-WEB-143.json?buildstate -o ouput.json"
 def jsonSlurper = new JsonSlurper()
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/ouput.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
def state=resultJson.state
  println(state)
  if(state.equals("Successful"))
  {
    def user=resultJson.buildReason
    println(user)
    String res = user.substring(3, user.indexOf(' '))
    println(res)
  }
}
