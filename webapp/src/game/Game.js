import './Game.css';
import React, {Component} from 'react';
import axios from "axios";
import {Question} from "./Question";
import {ExerciseTitleBar} from "./ExerciseTitleBar";
import diff from "./diff";

const baseUrl = 'http://localhost:3000';

class Game extends Component {

    constructor(props) {
        super(props);
        console.log(props);
        this.state = {
            exercises: [],
            exerciseIndex: 0,
            questionIndex: 0,
            rows: [],
            input: '',
            loadingData: true,
        };

        this.onSubmit = this.onSubmit.bind(this);
    }

    componentDidMount() {
        this.fetchExercises();
    }

    render() {
        return <div className={"Game"}>
            <div className={"Game-row-wrapper"}>
                <div className={"Game-row"}>
                    {this.renderExerciseTitle()}
                </div>
                <div className={"Game-row"}>
                    {this.renderQuestion()}
                </div>
            </div>
        </div>
    }

    renderExerciseTitle() {
        if (this.state.loadingData) {
            return <ExerciseTitleBar/>;
        }
        return <ExerciseTitleBar text={this.state.exercises[this.state.exerciseIndex].title}/>
    }

    renderQuestion() {
        if (this.state.loadingData) {
            return <Question/>;
        }
        return <Question {...this.state.exercises[this.state.exerciseIndex].questions[this.state.questionIndex]}/>
    }

    renderRows() {
        if (this.state.loadingData) return [];
        return ([
            <div className={"Game-row"}>
                <ExerciseTitleBar text={this.state.exercises[this.state.exerciseIndex].title}/>
            </div>,
            <div className={"Game-row"}>
                <Question {...this.state.exercises[this.state.exerciseIndex].questions[this.state.questionIndex]}/>
            </div>
        ])
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

    loadNextExercise() {
        this.setState({
            rows: this.state.rows.push(this.renderExerciseTitle())
        })
    }

    onAnswerChange(txt) {
        this.setState({
            answer: txt
        }, console.log(txt))
    }

    onSubmit(input) {
        console.log('submitted ' + input);
        this.reviewAnswer(input);
    }

    reviewAnswer(input) {
        let question = this.state.exercises[this.state.exerciseIndex].questions[this.state.questionIndex];
        for (let answer of question.answerKey) {
            console.log(diff(input, answer));
        }
    }

}

class Answer extends Component {
    render() {
        return <div className={"answer"}>
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