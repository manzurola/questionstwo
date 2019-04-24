import React, {Component} from "react";
import './AnswerInput';
import TextInput from '../input/TextInput';

export default class AnswerInput extends Component{

    constructor(props) {
        super(props);
        this.state = {
            review: props.review,
            input: '',
        }
    }


    render() {
        return <TextInput/>
    }
}