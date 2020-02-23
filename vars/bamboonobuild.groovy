import groovy.json.*
def call(IP)
{
  sh "curl -X GET -s -u rig:rigaDapt@devOps ${IP}/rest/api/latest/chart.json?reportKey=com.atlassian.bamboo.plugin.system.reports:numberOfBuilds&buildKeys=LAT-WEB&groupByPeriod=YEAR&dateFilter=RANGE&dateFrom=22/2/2020&dateTo=23/2/2020  -o  ouput.json"
 def jsonSlurper = new JsonSlurper()
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/ouput.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
def nobuild=resultJson.imageMap
println(nobuild)
 //def val = user.split('')
}
