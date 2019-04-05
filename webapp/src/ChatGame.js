import './Question.css';
import React, {Component} from 'react';
import Question from "./Question";

class ChatGame extends Component {

    constructor(props) {
        super(props);
        this.state = {
            exerciseIndex: 0,
            questionIndex: 0,
            input: ''
        }
    }

    render() {
        return <div className={"chat-game"}>
            <ExerciseTitleBar text={this.currentExercise().title}/>
            <Question {...this.currentQuestion()}/>
        </div>
    }

    currentExercise() {
        return this.props.exercises[this.state.exerciseIndex];
    }

    currentQuestion() {
        return this.currentExercise().questions[this.state.questionIndex];
    }

}

class ExerciseTitleBar extends Component {
    render() {
        return (
            <div className={"question title"}>
                <p>{this.props.text}</p>
            </div>
        );
    }
}

class Question extends Component {

    render() {
        return <div className={"question"}>
            <p>{this.currentQuestion().title}</p>
        </div>
    }
}

class Answer extends Component {
    render() {
        return <div className={"answer"}>
            <p>{this.state.input}</p>
        </div>
    }
}

class Input extends Component {
    render() {
        return <div className={"input"}>
            <p>{this.state.input}</p>
        </div>
    }
}

class Submit extends Component {
    render() {
        return <div className={"submit-button"}/>
    }
}

export default Exercise;