import React, {Component} from "react";
import axios from 'axios';
import {Button} from "@material-ui/core";
import {TextInput} from '../input/TextInput';
import Text from "../common/Text";
import SlideFromBottom from "../common/SlideFromBottom";
import Strikethrough from "../common/Strikethrough";

import "./Game.css";


export class Game extends Component {
    
    constructor(props) {
        super(props);

        let scenes = this.props.level.map(q => {
            return {
                question: q,
                answer: {
                    questionId: q.id,
                    value:'',
                    reviewed: false,
                    id: null,
                    review: null,
                    submitted: false,
                },
                input: {
                    value: '',
                    disabled: false,
                    focused: true,
                },
            }
        })

        this.state = {
            sceneIndex: 0,
            questions: this.props.level.map(d => <Question key={d.id} {...d}/>),
            answers: [],
            inputDisabled: true,
            scenes: scenes,
        }

        this.baseUrl = props.baseUrl;
        this.level = {
            questions: this.props.level
        }
    }

    render() {

        let messages = [];
        for (var i =0; i<=this.state.sceneIndex; i++) {

            let question = this.state.scenes[i].question;
            messages.push(<Question {...question}/>);
            let answer = this.state.scenes[i].answer;
            if (answer.submitted) {
                messages.push(<Answer {...answer}/>);
            }
        }

        let currentQuestion = this.state.scenes[this.state.sceneIndex].question;
        var label;
        if (currentQuestion) {
            label = currentQuestion.instructions;
        }

        let sceneInput = this.state.scenes[this.state.sceneIndex].input;
        let textInput = <TextInput
            autoFocus
            {...sceneInput}
            label={label}
            onChange={this.answerDidChange}
            onSubmit={this.handleSubmitAnswer}
        />;

        return <div className='game'>
            <div className='game-row-wrapper'>
                <div className='message-list'>
                    {messages}
                </div>
                <div className='input'>
                <div className='text-input'>{textInput}</div>
            </div>
            <div className='continue-button'>
                <Button onClick={() => this.setScene(this.state.sceneIndex +1)}>next</Button>
            </div>
            </div>
        </div>
    }

    renderMessages() {
        let messages = [];
        for (var i =0; i<=this.state.sceneIndex; i++) {
            messages.push(this.state.questions[i]);
            messages.push(this.state.answers[i]);
        }
        return messages;
    }

    handleSubmitAnswer = (event) => {
        console.log("handleSubmitAnswer:");
        console.log(event);

        let answer = {...this.state.scenes[this.state.sceneIndex].answer};
        answer.value = event.target.value;
        answer.submitted = true;
        answer.reviewed = false;
        answer.review = null;

        let input = {...this.state.scenes[this.state.sceneIndex].input};
        input.value = '';
        input.disabled = true;
        input.focused = false;

        this.setState(state =>
            state.scenes[state.sceneIndex].answer = answer
        , () => this.fetchReview(answer));
    };

    fetchReview = (answer) => {
        let request = {
            questionId: answer.questionId,
            answer: answer.value,
        };

        this.setState({reviewed: false}, () => {

            axios.post('/questions/' + request.questionId + '/answer', request)
            .then(res => this.handleAnswerReviewed(res.data))
        });
    }

    handleAnswerReviewed = (result) => {

        let answer = {...this.state.scenes[this.state.sceneIndex].answer};
        answer.submitted = true;
        answer.reviewed = true;
        answer.review = result.review;

        this.setState(state =>
            state.scenes[state.sceneIndex].answer = answer
        );
    }

    setScene = (next) => {
        if (next < 0 || next >= this.state.scenes.length) {
            console.warn("scene out of bounds: " + next);
            return;
        }

        this.setState({
            sceneIndex: next,
        });
    };
}

class Message extends Component {

    render () {
        return <div key={this.props.key} className='message'>
            {this.props.children}
        </div>
    }
}

class Question extends Component {

    render () {
        return <Message key={this.props.id}><div className='question'>{this.props.body}</div></Message>
    }
}

class Answer extends Component {

    state = {
        reviewed: false,
        review: {},    
    }

    render () {
        let answer;
        if (this.props.reviewed) {
            answer = this.props.review.isCorrect ? this.renderTrue() : this.renderFalse();
        } else {
            answer = this.props.value;
        }
        return <Message key={this.props.id}>{
            <div className='answer'>{answer}</div>
        }</Message>
    }

    renderTrue() {
        return [
            <div key={0} className={'answer-correct'}>
                {this.renderReviewedWords()}
            </div>,
            <div key={1} className={'answer-correct-icon slide-top'}>
                <i className="material-icons">done</i>
            </div>
        ]
    }

    renderFalse() {
        return <div className={'answer-mistake'}>
            {this.renderReviewedWords()}
        </div>
    }

    renderReviewedWords() {
        let words = [];
        let diff = this.props.review.diff.words;
        for (let i = 0; i < diff.length; i++) {
            let step = diff[i];
            console.log(step);
            let word = step.item.original;
            switch (step.operation) {
                case 'DELETE':
                    words.push(<Strikethrough key={i}><Text color={'#cccccc'} text={word}/></Strikethrough>);
                    break;
                case 'INSERT':
                    words.push(<SlideFromBottom key={i}><Text color={'#ff0000'} text={word}/></SlideFromBottom>);
                    break;
                case 'EQUAL':
                    words.push(<Text key={i} text={word}/>);
                    break;
            }
        }
        return words;
    }

}