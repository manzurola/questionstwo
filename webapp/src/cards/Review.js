import React, {Component} from "react";
import './Review.css';

export class Review extends Component {

    constructor(props) {
        super(props);
        this.state = {
            question: props.question,
            diff: props.explain.steps,
            score: props.score.value,
            scale: props.scale,
        };
    }

    componentWillReceiveProps(nextProps, nextContext) {
        this.setState({...nextProps})
    }

    render() {
        return <div className={'review'}>
            {this.renderTitle()}
            <div className={'review-input'}>
                <div className={'review-input-label'}>{this.state.question.instructions}</div>
                <div className={'review-input-answer'}>
                    {this.state.score > 0 ? this.renderTrue() : this.renderFalse()}
                </div>
            </div>
        </div>
    }

    renderTitle() {
        return <div className={'review-title'}>{this.state.question.body}</div>;
    }

    renderTrue() {
        return [
            <div key={0} className={'review-input-answer-correct'}>
                {this.renderReviewedWords()}
            </div>,
            <div key={1} className={'review-input-answer-correct-icon slide-top'}>
                <i className="material-icons">done</i>
            </div>
        ]
    }

    renderFalse() {
        return <div className={'review-input-answer-mistake'}>
            {this.renderReviewedWords()}
        </div>
    }


    renderReviewedWords() {
        let words = [];
        for (let i = 0; i < this.state.diff.length; i++) {
            let step = this.state.diff[i];
            console.log(step);
            switch (step.result) {
                case 'DELETE':
                    words.push(<span className={'answer-text answer-delete '}>{step.value}</span>);
                    break;
                case 'INSERT':
                    words.push(<span className={'answer-text answer-insert '}>{step.value}</span>);
                    break;
                case 'EQUAL':
                    words.push(<span className={'answer-text answer-equal '}>{step.value}</span>);
                    break;
            }

            words.push(<span> </span>);
        }
        return words;
    }


}

export default Review;