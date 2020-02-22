def call()
{
sh "curl -X GET -H  -u rig:rigaDapt@devOps http://18.220.143.53:8085/rest/api/latest/result/LAT-WEB.json?max-result=1 "
  println(${ManualBuildTriggerReason.getUserName()})
}
