import React, {Component} from "react";
import './ReviewMistake.css';

export class ReviewMistake extends Component {

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
            <div className={'question-input-reviewed-mistake'}>
                {this.renderReviewedWords()}
            </div>
            {/*<div className={'question-input-reviewed-correct-icon'}>*/}
            {/*<i className="material-icons">done</i>*/}
            {/*</div>*/}
        </div>
    }

    renderReviewedWords() {
        let words = [];
        for (let i = 0; i < this.props.diffs.length; i++) {
            let diff = this.props.diffs[i];
            switch (diff.operation) {
                case 'DELETE':
                    words.push(<span className={'input-delete'}>{diff.text}</span>);
                    break;
                case 'INSERT':
                    words.push(<span className={'input-insert'}>{diff.text}</span>);
                    break;
                case 'EQUAL':
                    words.push(<span className={'input-equal'}>{diff.text}</span>);
                    break;
            }
        }
        return words;
    }


}

export default ReviewMistake;