def call(jobname){
def build=sh "curl -s http://18.188.152.185:8080/job/'${jobname}'/lastFailedBuild/api/json --user vj:11e428e94b267ffbab27fa713e2da8e6e8"
println(build)
}
