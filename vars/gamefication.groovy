def call(bamboo1)
{
  def jsonString = bamboo1
def jsonObj = readJSON text: jsonString
  def cnt = jsonObj.bamboo.teamsuccessbuild_cnt
  println(cnt)
}
