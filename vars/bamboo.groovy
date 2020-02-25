import groovy.json.*
def call(IP)
{
 sh """ curl -X GET \
  'http://18.220.143.53:8085/rest/api/latest/result/LAT-WEB.json?max-result=50&expand=results.result.artifacts&expand=changes.change.files&start-index=0' \
  -H 'authorization: Basic cmlnOnJpZ2FEYXB0QGRldk9wcw==' \
  -H 'cache-control: no-cache' \
  -H 'postman-token: 50b866a3-885a-2d59-ea9d-b76fb8b13a16'  -o output.json """

 // sh "curl -X GET -s -u rig:rigaDapt@devOps ${IP}/rest/api/latest/result/LAT-WEB.json?max-result=50%26expand=results.result.artifacts%26expand=changes.change.files%26start-index=0  -o output.json"
def jsonSlurper = new JsonSlurper()
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/output.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
//def state=resultJson.results.result[0].buildCompletedDate
  //println(state)
 def cnt=0
  for(i=0;i<50;i++)
  {
   def date=resultJson.results.result[i].buildCompletedDate
   //println(date)
def state=resultJson.results.result[i].buildState
  // println(state)
   
  if((state.equals("Successful")))
  {
   // def user=resultJson.buildReason
   // println(user)
    //String res = user.substring(3, user.indexOf(' '))
   /* def val = user.split('>')
    //def str=val[1]
     def vals = val[1].split('<')
     def username=vals[0].split(' ')
     println(username[0])*/
   //echo "hi"
  cnt++
   println(resultJson.results.result[i])
   //sh "echo  ${JsonOutput.toJson(resultJson.results.result[i])} >>success.json"
    
  }
   // else
     // break;
  }
 println(cnt)
 //echo "$cnt"
}
