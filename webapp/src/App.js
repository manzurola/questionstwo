import React, {Component} from 'react';
import './App.css';
import Game from "./game/Game";

const baseUrl = 'http://localhost:3000';

class App extends Component {

    render() {
        return (
            <div className="App">
                <Game/>
            </div>
        );
    }


}

export default App;
