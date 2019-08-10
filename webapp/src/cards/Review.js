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
            review: props.review,
//            score: props.review.score.value,
//            scale: props.review.scale,
        };
    }

    componentWillReceiveProps(nextProps, nextContext) {
        this.setState({...nextProps})
    }

    render() {
        return <div className={'review'}>
            {this.renderTitle()}
            <div className={'review-input'}>
                <div className={'review-input-label'}><Text>{this.state.question.instructions}</Text></div>
                <div className={'review-input-answer'}>
                    {this.state.review.isCorrect ? this.renderTrue() : this.renderFalse()}
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
        for (let i = 0; i < this.state.review.diff.length; i++) {
            let step = this.state.review.diff[i];
            console.log(step);
            let word = step.object.value;
            switch (step.operation) {
                case 'DELETE':
                    words.push(<Strikethrough key={i}><Text color={'#cccccc'}>{word}</Text></Strikethrough>);
                    break;
                case 'INSERT':
                    words.push(<SlideFromBottom key={i}><Text color={'#ff0000'}>{word}</Text></SlideFromBottom>);
                    break;
                case 'EQUAL':
                    words.push(<Text key={i}>{word}</Text>);
                    break;
            }

            words.push(<Text key={i + 333}> </Text>);
        }
        return words;
    }


}

export default Review;