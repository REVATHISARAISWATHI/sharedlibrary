def call()
{
sh 'curl -X POST -i -H  "Accept: application/json" -H "Content-Type: application/json" -d "@repo.json" --user vickysastry.vs@outlook.com:zsxapkj3zwk6rtz7zm4tyli7ayk7yt5yehp5ic7erlec4xsf7tya "https://dev.azure.com/vickysastryvs/d2/_apis/wit/workitems/$Epic?api-version=5.1"'
}
