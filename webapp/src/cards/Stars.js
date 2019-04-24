import coin from '../icons/dollar-coins.svg';
import React, {Component} from "react";
import "./Stars.css";

export class Stars extends Component {

    render() {
        return <div className={'stars'} >
            <img src={coin} alt="Coin" />
        </div>
    }
}