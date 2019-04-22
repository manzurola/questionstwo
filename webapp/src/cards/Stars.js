import coin from '../icons/dollar-coins.svg';
import React, {Component} from "react";
import "./Stars.css";

export class Stars extends Component {

    render() {
        return <div >
            <img className={'stars'} src={coin} alt="Coin" />
        </div>
    }
}