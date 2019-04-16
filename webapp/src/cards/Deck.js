import React, {Component} from "react";
import './Deck.css';
import Question from "./Question";
import {Review} from "./Review";
import {ReviewMistake} from "./ReviewMistake";

export class Deck extends Component {

    render() {
        return <div className={'Deck'}>
            <Question onSubmit={this.onAnswerSubmitted}/>
            <Review/>
            <ReviewMistake diffs={[
                {
                    operation: 'EQUAL',
                    text: 'Dogs '
                },
                {
                    operation: 'DELETE',
                    text: 'is'
                },
                {
                    operation: 'INSERT',
                    text: ' are'
                },
                {
                    operation: 'EQUAL',
                    text: ' cute.'
                },
            ]}
            />
        </div>
    }

    onAnswerSubmitted = (event) => {
        console.log("onAnswerSubmitted " + event);
    };

    renderQuestion() {

    }

}

export default Deck;