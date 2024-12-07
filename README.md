The idea is show off communication between 2 servers, each built in their own framework.

We begin by sending a message from each server, and then have 2 simultaneous conversations, where
each service responds to each other and saves the timestamp

The basic flow is as follows:

- Startup occurs for each service
- Spring Boot begins conversation
- Dotnet begins conversations
- Spring Boot sends its message
- Dotnet gets messages, saves it, and after 10 seconds responds
- Spring Boot gets messages, saves it, and after 10 seconds responds
- Repeat

So a log would look as follows:

- Spring Boot Starts up
- Dotnet Starts up
- Spring Boot creates "Spring Boot Conversation"
- Dotnet creates "Dotnet Conversation"
- Spring Boot Says "Message 1 @ Spring Boot Conversation"
- Dotnet Says "Message 1 @ Dotnet Conversation"
- Dotnet Recieves "Message 1 @ Spring Boot Conversation"
  - Dotnet saves it with a timestamp and info
  - 10-second timer begins
- Spring Boot Recieves "Message 1 @ Dotnet Conversation"
    - Spring Boot saves it with a timestamp and info
    - 10-second timer begins
- Dotnet says "Message 2 @ Spring Boot Conversation"
- Spring Boot says "Message 2 @ Dotnet Conversation"


The conversations are saved as follows:
- conversation: String
- Message: Int (increments each time one is sent)
- Timestamp

Messages are saved by the receiving party, not the sending party



Future additions:
- Authentication could be added
- Fallback/Failure mechanisms could be added to resend failed messages until received
- Messages could be saved when sent to keep track of status (failures or such)
- Monitoring & Logging
- Message Queues