def call(job_name){
  sh "curl -s http://18.188.152.185:8080/job/'${job_name}'/lastFailedBuild/api/json --user vj:11e428e94b267ffbab27fa713e2da8e6e8 -o output.json"
}
