def call()
{
def build= sh 'curl -s  https://dev.azure.com/vickysastryvs/d2/_apis/wit/workitems?ids=1,4,3&api-version=5.1 --user vickysastry.vs@outlook.com:zsxapkj3zwk6rtz7zm4tyli7ayk7yt5yehp5ic7erlec4xsf7tya  -o ouput.json'
}
