using System.Collections.Generic;

public class MyLog
{
  public MyRequest request { get; }
  public MyResponse response { get; }
}

public class MyResponse
{
  public string body { get; }
  public int code { get; }
}

public class MyRequest
{
  public string url { get; }
  public Dictionary<string, string> headers { get; }
  public string body { get; }
}
