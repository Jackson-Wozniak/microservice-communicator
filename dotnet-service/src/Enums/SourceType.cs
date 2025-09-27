namespace dotnet_service.Enums;

public enum SourceType
{
    DotnetService = 1,
    SpringBootService = 2
}

public static class SourceTypeUtils
{
    public static string ToName(this SourceType type)
    {
        return type switch
        {
            SourceType.DotnetService => "DotnetService",
            SourceType.SpringBootService => "SpringBootService",
            _ => ""
        };
    }
    
    public static SourceType FromName(string name)
    {
        return name.ToLower() switch
        {
            "dotnetservice" => SourceType.DotnetService,
            "springbootservice" => SourceType.SpringBootService,
            _ => SourceType.DotnetService
        };
    }
}