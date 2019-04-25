import React, {Component} from "react";
import './Question.css';
import {TextInput} from "../input/TextInput";
import Text from "../common/Text";

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
        return <div>
            {this.renderTitle()}
            {this.renderInput()}
        </div>
    }

    renderTitle() {
        return <Text fontSize={'2em'}>{this.props.body}</Text>;
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
        this.setState({answer: event.target.value.trim()}, () => {
            if (this.props.onInputChange) this.props.onInputChange(event);
        });
    };

    answerWasSubmitted = (event) => {
        this.setState({submitted: true}, () => {
            if (this.props.onSubmit) {
                this.props.onSubmit({
                    id: this.state.id,
                    answer: this.state.answer.trim(),
                });
            }
        });
    };

}

export default Question;