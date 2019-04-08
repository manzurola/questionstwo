import React, {Component} from 'react';
import './App.css';
import Game from "./game/Game";
import Deck from "./cards/Deck";

const baseUrl = 'http://localhost:3000';

class App extends Component {

    render() {
        return (
            <div className="App">
                <div className="App-header"/>
                <div className="App-game">
                    {/*<Game/>*/}
                    <Deck/>
                </div>
                <div className="App-footer"/>
            </div>
        );
    }


}

export default App;
