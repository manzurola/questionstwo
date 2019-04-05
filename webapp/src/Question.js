import './Question.css';
import React, {Component} from 'react';

class Question extends Component {

    render() {
        return <div className={"question"}>
            <Title text={this.props.title}/>
            <Subtitle text={this.props.subtitle}/>
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

class Subtitle extends Component {
    render() {
        return (
            <div className={"question subtitle"}>
                <p>{this.props.text}</p>
            </div>
        );
    }
}

export default Question;