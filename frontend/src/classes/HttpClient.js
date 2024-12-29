class MessageData {
    constructor(id, name, time){
        this.messageId = id;
        this.conversation = name;
        this.timestampSent = time;
        this.timestampReceived = time;
    }
    messageId;
    messageNumber;
    conversation;
    timestampSent;
    timestampReceived;
}

export function getSpringBootMessages(){
    return Array.of(new MessageData(1, "Springboot", "00:00"), new MessageData(2, "Springboot", "00:00"));
}

export function getDotnetMessages(){
    return Array.of(new MessageData(1, "Dotnet", "00:00"), new MessageData(2, "Dotnet", "00:00"));
}

export {MessageData};