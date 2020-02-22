def call(IP)
{
  sh "curl -X GET -i -H  -d  -u rig:rigaDapt@devOps '${IP}'/rest/api/latest/result/LAT-WEB-143.json?buildstate "
}
