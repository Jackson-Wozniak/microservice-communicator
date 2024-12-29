import './App.css';
import { getSpringBootMessages, getDotnetMessages } from './classes/HttpClient';
import Conversation from './components/Conversation';

function App() {
  return (
    <div className="App">
      <Conversation getMessages={getSpringBootMessages} conversationName={"SpringBoot"}/>
      <Conversation getMessages={getDotnetMessages} conversationName={"Dotnet"}/>
    </div>
  );
}

export default App;
