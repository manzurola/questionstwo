import React, {Component} from "react";
import './Question.css';
import {TextInput} from "../input/TextInput";
import axios from 'axios';

export class Question extends Component {

    constructor(props) {
        super(props);
        this.state = {
            id: props.id,
            body: props.body,
            answer: '',
            submitted: false,
            reviewed: false,
        }
    };

    render() {
        return <div className={'question'}>
            {this.renderTitle()}
            {this.renderInput()}
        </div>
    }

    renderTitle() {
        return <div className={'question-title'}>{this.props.body}</div>;
    }


    renderInput() {
        return <div className={'question-input'}>
            <TextInput
                key={this.state.id}
                autoFocus
                label={this.props.instructions}
                onChange={this.answerDidChange}
                onSubmit={this.answerWasSubmitted}
                disabled={this.state.submitted}
            />
        </div>
    }

    answerDidChange = (event) => {
        console.log(event);
        this.setState({answer: event.target.value}, () => {
            if (this.props.onInputChange) this.props.onInputChange(event);
        });
    };

    answerWasSubmitted = (event) => {
        this.setState({submitted: true}, () => {
            if (this.props.onSubmit) {
                this.props.onSubmit({
                    id: this.state.id,
                    answer: this.state.answer,
                });
            }
        });
    };

}

export default Question;