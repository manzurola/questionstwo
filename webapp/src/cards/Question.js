import React, {Component} from "react";
import './Question.css';
import {TextInput} from "../cards/TextInput";

export class Question extends Component {

    constructor(props) {
        super(props);
        this.state = {
            answer: '',
            submitted: false,
        };
    }

    render() {
        return <div className={'question'}>
            {this.renderTitle()}
            {this.renderInput()}
        </div>
    }

    renderTitle() {
        return <div className={'question-title'}>{'A dog is cute.'}</div>;
    }

    renderInput() {
        return <div className={'question-input'}>
            <TextInput
                autoFocus
                label="Put the sentence into the plural"
                onChange={this.answerDidChange}
                onSubmit={this.answerWasSubmitted}
                disabled={this.state.submitted}
            />
        </div>
    }

    answerDidChange = (event) => {
        console.log(event);
        this.setState({answer: event.target.value}, () => {
            if(this.props.onInputChange) this.props.onInputChange(event);
        });
    };

    answerWasSubmitted = (event) => {
        this.setState({submitted: true}, () => {
            if(this.props.onSubmit) this.props.onSubmit(event);
        });
    };

}

export default Question;