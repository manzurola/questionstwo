import React, {Component} from "react";
import './Review.css';
import Text from "../common/Text";
import SlideFromBottom from "../common/SlideFromBottom";
import Strikethrough from "../common/Strikethrough";

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
        return <Text fontSize={'2em'}>{this.props.question.body}</Text>;
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
                    words.push(<Strikethrough><Text color={'#cccccc'}>{step.value}</Text></Strikethrough>);
                    break;
                case 'INSERT':
                    words.push(<SlideFromBottom><Text color={'#ff0000'}>{step.value}</Text></SlideFromBottom>);
                    break;
                case 'EQUAL':
                    words.push(<Text>{step.value}</Text>);
                    break;
            }

            words.push(<span> </span>);
        }
        return words;
    }


}

export default Review;