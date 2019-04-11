import React, {Component} from "react";
import './Review.css';

export class Review extends Component {

    constructor(props) {
        super(props);
        this.state = {
            answer: '',
        };
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
            <div className={'question-input-label'}>Put the sentence into the plural</div>
            <div className={'question-input-reviewed-correct'}>
                {"Dogs are cute."}
            </div>
            <div className={'question-input-reviewed-correct-icon'}>
                <i className="material-icons">done</i>
            </div>
        </div>
    }


}

export default Review;