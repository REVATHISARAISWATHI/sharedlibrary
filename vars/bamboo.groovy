import groovy.json.*
def call(IP)
{
 

  sh "curl -X GET -s -u rig:rigaDapt@devOps ${IP}/rest/api/latest/result/LAT-WEB.json?max-result=50%26expand=results.result.artifacts%26expand=changes.change.files%26start-index=0  -o output.json"
def jsonSlurper = new JsonSlurper()
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/output.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
def state=resultJson.results.result[0].buildStartedTime
  println(state)
 /* for(i=0;i<50;i++)
  {
    def state=resultJson.result.buildCompletedDate

  if(state.equals("2020-02-24"))
  {
    def user=resultJson.buildReason
    println(user)
    //String res = user.substring(3, user.indexOf(' '))
    def val = user.split('>')
    //def str=val[1]
     def vals = val[1].split('<')
     def username=vals[0].split(' ')
     println(username[0])
    
  }
    else
      break;
  }*/
}
