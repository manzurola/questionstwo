import './Game.css';
import React, {Component} from 'react';
import axios from "axios";
import {Question} from "./Question";
import {ExerciseTitleBar} from "./ExerciseTitleBar";

const baseUrl = 'http://localhost:3000';

class Game extends Component {

    constructor(props) {
        super(props);
        console.log(props);
        this.state = {
            exercises: [],
            exerciseIndex: 0,
            questionIndex: 0,
            input: '',
            loadingData: true
        }
    }

    componentDidMount() {
        this.fetchExercises();
    }

    render() {
        return <div className={"game"}>
            {this.getExerciseTitle()}
            {this.getQuestion()}
        </div>
    }

    getExerciseTitle() {
        if (this.state.loadingData) {
            return <ExerciseTitleBar/>;
        }
        return <ExerciseTitleBar text={this.state.exercises[this.state.exerciseIndex].title}/>
    }

    getQuestion() {
        if (this.state.loadingData) {
            return <Question/>;
        }
        return <Question {...this.state.exercises[this.state.exerciseIndex].questions[this.state.questionIndex]}/>
    }

    fetchExercises() {
        axios.get(baseUrl + '/exercises')
            .then(response => response.data)
            .then(data => this.setState({
                exercises: data,
                loadingData: false
            }, (data) => console.log(data)))
            .catch(error => console.log(error));
    }

    onAnswerChange(txt) {
        this.setState({
            answer: txt
        }, console.log(txt))
    }

    onSubmit() {
        console.log(this.state.answer);
        // post(baseUrl + '/review')
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

export default Game;