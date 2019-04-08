import React, {Component} from "react";
import './Question.css';
import TextInputOld from "./TextInput";
import {TextInput} from "../cards/TextInput";

export class Question extends Component {

    constructor(props) {
        super(props);
        this.state = {};
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
                label="Put the sentence into the plural"
            />
        </div>
    }


}

export default Question;