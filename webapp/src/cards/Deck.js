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
            reviews: {},
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
        return this.state.reviews[this.state.index];
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
            questionId: this.getQuestion().id,
            userId: '',
            answer: answer,
        };
        console.log(request);
        axios.post('/answers', request).then(res => {
            // console.log(res);
            // console.log(res.data);
            let reviews = {...this.state.reviews};
            reviews[this.state.index] = res.data;
            console.log("logging reviews:");
            console.log(reviews);

            this.setState({
                reviews: reviews,
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