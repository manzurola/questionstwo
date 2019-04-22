import React, {Component} from "react";
import './Deck.css';
import Question from "./Question";
import {Review} from "./Review";
import axios from 'axios';
import {Button} from "@material-ui/core";
import {Stars} from "./Stars";

export class Deck extends Component {


    constructor(props) {
        super(props);
        this.state = {
            index: 0,
            questions: [],
            reviews: {},
        }
    }

    componentWillMount() {
        let data = this.getData();
        data.then(res => {
            const questions = res.data;
            console.log("got response:");
            console.log(questions);
            this.setState({questions: questions});
        });
    }

    render() {
        return <div className={'Deck'}>
            <Stars/>
            <div className={'card-wrapper'}>
                {this.hasReview() ? this.renderReview() : this.renderQuestion()}
            </div>
            <div>
                <Button onClick={this.loadNextQuestion}>next</Button>
            </div>
        </div>
    }

    renderQuestion() {
        console.log("rendering question");
        console.log(this.getQuestion());


        return <Question {...this.getQuestion()}
                         onSubmit={this.onAnswerSubmitted}
        />
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
        return this.state.questions[this.state.index]
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
        let next = this.state.index + 1 < this.state.questions.length ? this.state.index + 1 : 0;
        this.setState({
            index: next,
        });
    };

}

export default Deck;