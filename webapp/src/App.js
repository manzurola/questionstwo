import React, {Component} from 'react';
import './App.css';
import Deck from "./cards/Deck";
import {Router, Route} from "react-router-dom";
import axios from 'axios';
import {Game} from './game/Game';

const baseUrl = 'http://localhost:3000';

class App extends Component {

    state = {
        data: [],
        loaded: false,
    };

    render() {
        return (
            <div className="App">
                <div className="App-header"/>
                <div className="App-game">
                    {this.state.loaded ? <Game baseUrl={baseUrl} level={this.state.data}/> : null}
                </div>
                <div className="App-footer"/>
            </div>
        );
    }

    componentDidMount() {
        axios.get('/questions')
            .then(res => {
                this.setState({data: res.data, loaded:true});
            });
    }

}

export default App;
