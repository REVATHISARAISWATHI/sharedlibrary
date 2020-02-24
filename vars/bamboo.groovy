import groovy.json.*
def call(IP)
{
  def var=$(sh "curl -X GET -s -u rig:rigaDapt@devOps ${IP}/rest/api/latest/result/LAT-WEB.json?max-result=50&expand=results.result.artifacts&expand=changes.change.files&start-index=0 ")
 def jsonSlurper = new JsonSlurper()
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/ouput.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
def state=resultJson.result[0].buildCompletedDate
  println(state)
 /* for(i=0;i<50;i++)
  {
    def state=resultJson.result[0].buildCompletedDate

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
