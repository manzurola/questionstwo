import React, {Component} from "react";
import './Deck.css';
import Question from "./Question";
import {Review} from "./Review";
import axios from 'axios';
import {Button} from "@material-ui/core";
import {Stars} from "./Stars";
import {Card} from "./Card";

export class Deck extends Component {


    constructor(props) {
        super(props);
        this.state = {
            index: 0,
            data: [],
            questions: [],
            answers: {},
        }
    }

    componentWillMount() {
        let data = this.getData();
        data.then(res => {
            const data = res.data;
            console.log("got response:");
            console.log(data);
            this.setState({
                    data: data,
                    questions: data.map(q =>
                        <Question key={q.id} {...q} onSubmit={this.onAnswerSubmitted}/>)
                }
            );
        });
    }

    render() {
        return <div className={'Deck'}>
            <Card title={() => this.getQuestion().title}>
                {this.hasReview() ? this.renderReview() : this.renderQuestion()}
            </Card>
            <div>
                <Button onClick={this.loadNextQuestion}>next</Button>
            </div>
        </div>
    }

    renderQuestion() {
        console.log("rendering question");
        console.log(this.getQuestion());


        return this.state.questions[this.state.index];
        // return this.state.data.map((question) => {
        //     return <Question {...question}/>
        // });
    }

    hasReview() {
        return !!this.getReview();
    }

    getReview() {
        return this.state.answers[this.state.index];
    }

    getQuestion() {
        return this.state.data[this.state.index]
    }

    renderReview() {
        return this.hasReview() ? <Review {...this.getReview()} question={this.getQuestion()}/> : null;
    }

    getData = () => {
        console.log('/questions');
        return axios.get('/questions');
    };

    onAnswerSubmitted = (event) => {
        console.log("onAnswerSubmitted ");
        console.log(event);
        this.submitAndReview(event.answer);
    };

    submitAndReview(answer) {
        const request = {
            answer: answer,
        };
        console.log(request);
        console.log(answer);
        console.log(this.getQuestion().id);
        axios.post('/questions/' + this.getQuestion().id + '/answer', request).then(res => {
            // console.log(res);
            // console.log(res.data);
            let answers = {...this.state.answers};
            answers[this.state.index] = res.data;
            console.log("logging answers:");
            console.log(answers);

            this.setState({
                answers: answers,
            });
        })
    }

    loadNextQuestion = () => {
        let next = this.state.index + 1 < this.state.data.length ? this.state.index + 1 : 0;
        this.setState({
            index: next,
        });
    };

}

export default Deck;