import groovy.json.*
def call(IP)
{
 // sh "curl -X GET -s -u rig:rigaDapt@devOps ${IP}/rest/api/latest/chart.json?reportKey%3Dcom.atlassian.bamboo.plugin.system.reports%3AnumberOfBuilds%26buildKeys%3DLAT-WEB%26groupByPeriod%3DYEAR%26dateFilter%3DRANGE%26dateFrom%3D22%2F2%2F2020%26dateTo%3D23%2F2%2F2020  -o  ouput.json"
 sh """ curl -X GET \
  '${IP}/rest/api/latest/chart.json?reportKey=com.atlassian.bamboo.plugin.system.reports%3AnumberOfBuilds&buildKeys=LAT-WEB&groupByPeriod=YEAR&dateFilter=RANGE&dateFrom=22%2F2%2F2020&dateTo=23%2F2%2F2020' \
  -H 'authorization: Basic cmlnOnJpZ2FEYXB0QGRldk9wcw==' \
  -H 'cache-control: no-cache' \
  -H 'postman-token: ad68a811-ecd6-864c-2685-5f8c15a81cc3'  -o ouput.json
  """
 
 def jsonSlurper = new JsonSlurper()
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/ouput.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
def nobuild=resultJson.imageMap
println(nobuild)
 //def val = user.split('')
}
