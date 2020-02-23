import groovy.json.*
def call(IP)
{
  //sh "curl -X GET -s -u rig:rigaDapt@devOps ${IP}/rest/api/latest/chart.json?reportKey=com.atlassian.bamboo.plugin.system.reports%3AnumberOfBuilds%26buildKeys=LAT-WEB%26groupByPeriod=YEAR%26dateFilter=RANGE%26dateFrom=22%2F2%2F2020%26dateTo=23%2F2%2F2020  -o  ouput.json"
 sh """ curl -X GET \
  '${IP}/rest/api/latest/chart.json?reportKey=com.atlassian.bamboo.plugin.system.reports%3AnumberOfBuilds&buildKeys=LAT-WEB&groupByPeriod=YEAR&dateFilter=RANGE&dateFrom=23%2F2%2F2020&dateTo=24%2F2%2F2020' \
  -H 'authorization: Basic cmlnOnJpZ2FEYXB0QGRldk9wcw==' \
  -H 'cache-control: no-cache' \
  -H 'postman-token: ad68a811-ecd6-864c-2685-5f8c15a81cc3'  -o ouput.json
  """ 
 
 def jsonSlurper = new JsonSlurper()
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/ouput.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
def nobuild=resultJson.imageMap
println(nobuild)
  if(nobuild!=null)
  {
    def val = nobuild.split('title=\"')
   // println(val[1])
   //def val1=val[1]
     //println(val1)
     def res = val[1].split(' ')
    println(res[3])
  }
  else
  {
    
    echo "the no of build is 0" 
  }
 
}
