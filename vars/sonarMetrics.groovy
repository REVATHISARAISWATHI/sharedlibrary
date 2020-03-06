def call(jsondata){
def jsonString = jsondata
def jsonObj = readJSON text: jsonString

String a = jsonObj.code_quality.projects.project.project_name
String ProjectName=a.replaceAll("\\[", "").replaceAll("\\]","");
  
withCredentials([usernamePassword(credentialsId: 'sonar_cred', passwordVariable: 'password', usernameVariable: 'username')]){
  sh "curl -u ${username}:${password} -X GET 'http://ec2-3-133-107-212.us-east-2.compute.amazonaws.com:9000/api/measures/component?component=${ProjectName}&metricKeys=coverage,vulnerabilities,bugs,violations,complexity,tests,duplicated_lines,sqale_index' -o metrics.json"
  echo 'metrics collected'
}
}
