import groovy.json.*
def call(IP)
{
  sh "curl -X GET -s -u rig:rigaDapt@devOps ${IP}/rest/api/latest/chart.json?reportKey%3Dcom.atlassian.bamboo.plugin.system.reports%3AnumberOfBuilds&buildKeys%3DLAT-WEB&groupByPeriod%3DYEAR&dateFilter%3DRANGE&dateFrom%3D22/2/2020&dateTo%3D23/2/2020  -o  ouput.json"
  
 
 def jsonSlurper = new JsonSlurper()
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/ouput.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
def nobuild=resultJson.imageMap
println(nobuild)
 //def val = user.split('')
}
