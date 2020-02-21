def call(organization,projectname,id)
{
sh "curl -X POST  -H  Accept: application/json -H  'Content-Type: application/json' -d { 'text': 'Moving to the right area path' } -u vickysastry.vs@outlook.com:zsxapkj3zwk6rtz7zm4tyli7ayk7yt5yehp5ic7erlec4xsf7tya https://dev.azure.com/${organization}/${projectname}/_apis/wit/workItems/${id}/comments?api-version=5.1-preview.3 "
}
