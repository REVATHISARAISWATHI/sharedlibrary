def call()
{
def build=sh 'curl -s http://18.188.152.185:8080/job/bamboo1/lastBuild/api/json?tree=estimatedDuration --user vj:11e428e94b267ffbab27fa713e2da8e6e8 -o output.json'
  println(build)
}
