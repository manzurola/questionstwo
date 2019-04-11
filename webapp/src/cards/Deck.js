import React, {Component} from "react";
import './Deck.css';
import Question from "./Question";
import {Review} from "./Review";

export class Deck extends Component {

    render() {
        return <div className={'Deck'}>
            <Question onSubmit={this.onAnswerSubmitted}/>
            <Review/>
        </div>
    }

    onAnswerSubmitted = (event) => {
        console.log("onAnswerSubmitted " + event);
    };

    renderQuestion() {

    }

}

export default Deck;