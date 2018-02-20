A couple tips specifically if you're solving this problem using C#:

# Running
## Console (Windows, Linux, OSX)
in the directory of the repo run
```sh
dotnet restore
dotnet build
dotnet run --project RequestReplay/
```

## Visual Studio Code (Windows, Linux, OSX)
Open the directory of the repo

Open Debugging tab

Click Start (green arrow)

## Visual Studio 2017 (Windows)
Open `csharp-interview-prep.sln`

Click Debug start

# Libraries
We recommend you use [Newtonsoft's Json.NET](https://www.newtonsoft.com/json) and [HttpClient](https://docs.microsoft.com/en-us/aspnet/web-api/overview/advanced/calling-a-web-api-from-a-net-client) for implementation, but you can use whichever library you want.
