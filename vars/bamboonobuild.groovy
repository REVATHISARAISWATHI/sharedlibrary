import groovy.json.*

int totalbuilds;


def bamboo(IP)
{
  //sh "curl -X GET -s -u rig:rigaDapt@devOps ${IP}/rest/api/latest/chart.json?reportKey=com.atlassian.bamboo.plugin.system.reports%3AnumberOfBuilds%26buildKeys=LAT-WEB%26groupByPeriod=YEAR%26dateFilter=RANGE%26dateFrom=22%2F2%2F2020%26dateTo=23%2F2%2F2020  -o  ouput.json"
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
 // env.name = nobuild

println(nobuild)
  if(nobuild!=null)
  {
    def val = nobuild.split('title=\"')
   // println(val[1])
   //def val1=val[1]
     //println(val1)
     def vals = val[1].split(' ')
    def totalbuild=vals[3]
    println(totalbuild)
  // success(totalbuild);
    return totalbuild
  }
  else
  {
    
    echo "the no of build is 0" 
    return 0
  }
 
}

def success(totalbuilds)
{
   sh """curl -X GET \
  'http://18.220.143.53:8085/rest/api/latest/chart.json?reportKey=com.atlassian.bamboo.plugin.system.reports%3AnumberOfFailures&buildKeys=LAT-WEB&groupByPeriod=YEAR&dateFilter=RANGE&dateFrom=22%2F2%2F2020&dateTo=23%2F2%2F2020' \
  -H 'authorization: Basic cmlnOnJpZ2FEYXB0QGRldk9wcw==' \
  -H 'cache-control: no-cache' \
  -H 'postman-token: 54f9ea34-9831-be64-6e71-45f1e893f2eb'  -o ouput.json
  """
    def jsonSlurper = new JsonSlurper()
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/ouput.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
    def nbuild=resultJson.imageMap
      def val2 = nbuild.split('title=\"')
     def builds = val2[1].split(' ')
    def Failbuild=builds[2]
    println(Failbuild)
  println(totalbuilds)
  def successbuild=${totalbuilds}-Failbuild
     println(successbuild)
}
