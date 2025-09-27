package com.service.app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SourceType {
    DOTNET_SERVICE("DotnetService"),
    SPRINGBOOT_SERVICE("SpringBootService");

    private final String name;

    public static SourceType fromName(String name){
        if(name.equalsIgnoreCase("dotnetservice")) return DOTNET_SERVICE;
        return SPRINGBOOT_SERVICE;
    }
}
