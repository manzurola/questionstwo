import React, {Component} from "react";
import './Deck.css';
import Question from "./Question";

export class Deck extends Component {

    render() {
        return <div className={'Deck'}>
            <Question/>
        </div>
    }
}

export default Deck;