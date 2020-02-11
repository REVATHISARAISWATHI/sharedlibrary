def call(JSON){
  String a=jsonObj.ci.jobs.job.job_name
String JobName=a.replaceAll("\\[", "").replaceAll("\\]","");
  
  sh "curl -s http://18.188.152.185:8080/job/'${JobName}'/lastFailedBuild/api/json --user vj:11e428e94b267ffbab27fa713e2da8e6e8 -o output.json"
}
