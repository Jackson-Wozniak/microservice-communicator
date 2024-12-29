import { useState, useEffect } from 'react';
import Message from './Message';

function Conversation(props) {
    const [messages, setMessages] = useState([]);

    useEffect(() => {
        const interval = setInterval(() => {
            let tempMessages = props.getMessages();
            setMessages(tempMessages);
        }, 10000);

        return () => clearInterval(interval);
    }, []);

    return (
    <div className="Conversation">
        <h1>{props.conversationName}</h1>
        {messages.map((message) => (
            <Message key={message.messageId} messageId={message.messageId} name={message.conversationName} timestamp={message.timestamp}/>
        ))}
    </div>
    );
}

export default Conversation;
