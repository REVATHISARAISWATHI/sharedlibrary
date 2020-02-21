def call(json,type)
{
sh '''curl -X POST  -H  "Accept: application/json" -H "Content-Type: application/json-patch+json" -d "@json" -u vickysastry.vs@outlook.com:zsxapkj3zwk6rtz7zm4tyli7ayk7yt5yehp5ic7erlec4xsf7tya https://dev.azure.com/vickysastryvs/ENG122/_apis/wit/workitems/%24${type}?api-version=5.1 '''
  
}
