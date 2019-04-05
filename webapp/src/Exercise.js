import './Question.css';
import React, {Component} from 'react';
import Question from "./Question";

class Exercise extends Component {

    constructor(props) {
        super(props);
        this.state = {
            questions: props.questions
        }
    }

    render() {
        return <div className={"question"}>
            <Title text={this.props.title}/>
            <Question {...this.props.questions[this.state.questionIndex]}/>
        </div>
    }

}

class Title extends Component {
    render() {
        return (
            <div className={"question title"}>
                <p>{this.props.text}</p>
            </div>
        );
    }
}

export default Exercise;