import React, {Component} from "react";
import './Review.css';
import './Question.css';
import axios from "axios";

export class Review extends Component {

    constructor(props) {
        super(props);
        this.state = {
            question: props.question,
            diff: props.answerDiff.diff,
            score: props.score.value,
            scale: props.scale,
        };
    }

    componentWillReceiveProps(nextProps, nextContext) {
        this.setState({...nextProps})
    }

    render() {
        return <div className={'question'}>
            {this.renderTitle()}
            <div className={'question-input'}>
                <div className={'question-input-label'}>{this.state.question.instructions}</div>
                {this.state.score > 0 ? this.renderTrue() : this.renderFalse()}
            </div>
        </div>
    }

    renderTitle() {
        return <div className={'question-title'}>{this.state.question.body}</div>;
    }

    renderTrue() {
        return [
            <div key={0} className={'question-input-reviewed-correct'}>
                {this.renderReviewedWords()}
            </div>,
            <div key={1} className={'question-input-reviewed-correct-icon'}>
                <i className="material-icons">done</i>
            </div>
        ]
    }

    renderFalse() {
        return <div className={'question-input-reviewed-mistake'}>
            {this.renderReviewedWords()}
        </div>
    }


    renderReviewedWords() {
        let words = [];
        for (let i = 0; i < this.state.diff.length; i++) {
            let diff = this.state.diff[i].diff;
            console.log(diff);
            switch (diff.operation) {
                case 'DELETE':
                    words.push(<span className={'input-delete input-text'}>{diff.text}</span>);
                    break;
                case 'INSERT':
                    words.push(<span className={'input-insert input-text'}>{diff.text}</span>);
                    break;
                case 'EQUAL':
                    words.push(<span className={'input-equal input-text'}>{diff.text}</span>);
                    break;
            }

            words.push(<span> </span>);
        }
        return words;
    }



}

export default Review;