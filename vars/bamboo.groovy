def call()
{
sh 'curl -X GET -i -H -u rig:rigaDapt@devOps "http://18.220.143.53:8085/rest/api/latest/result/LAT-WEB-143.json?buildstate" '
}
