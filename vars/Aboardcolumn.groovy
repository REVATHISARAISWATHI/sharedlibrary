import groovy.json.*

@NonCPS
influx(){
def jsonSlurper = new JsonSlurper()
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/ouput.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
def count = resultJson.count
 
sh """curl -i -XPOST 'http://ec2-13-58-47-71.us-east-2.compute.amazonaws.com:8086/write?db=Collector' --data-binary 'azureboard workitems=${count}' 
"""
}
def call()
{
 
def response=sh """curl -i -s -XGET https://dev.azure.com/vickysastryvs/d2/_apis/work/boardcolumns?api-version=5.1 --user vickysastry.vs@outlook.com:zsxapkj3zwk6rtz7zm4tyli7ayk7yt5yehp5ic7erlec4xsf7tya   >test.txt """
 //influx()
  echo " ++++++++++++ $response"

if(response == "204" || response == "200")
{
 echo " Data pushed into influxDB "
}
else
{
 error("Error while pushing")
}
 }
