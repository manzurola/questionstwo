import React, {Component} from 'react';
import './App.css';
import Deck from "./cards/Deck";
import {Router, Route} from "react-router-dom";

const baseUrl = 'http://localhost:3000';

class App extends Component {

    render() {
        return (
            <div className="App">
                <div className="App-header"/>
                <div className="App-game">
                    <Deck {...baseUrl}/>
                </div>
                <div className="App-footer"/>
            </div>
        );
    }


}

export default App;
