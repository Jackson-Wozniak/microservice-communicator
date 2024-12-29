
function Message(props) {
    return (
    <div className="Message">
        <h1>{props.messageId}</h1>
        <p>{props.name}</p>
        <p>{props.timestamp}</p>
    </div>
    );
}

export default Message;
