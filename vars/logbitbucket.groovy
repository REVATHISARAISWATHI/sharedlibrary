import groovy.json.JsonSlurper 
@NonCPS
def call(message)
{
 println(message)
 def request = libraryResource 'bitbucket.json'
 def jsonSlurper = new JsonSlurper() 
 def resultJson = jsonSlurper.parseText(request)
 def repoName = resultJson.bitbucketname
  Date date = new Date() 
  sh " echo '${date}' '${repoName}' repository ${message} in Bitbucket>>log.txt"
}
