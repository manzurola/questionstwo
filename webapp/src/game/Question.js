import React, {Component} from "react";
import './Question.css';

export class Question extends Component {

    render() {
        return <div className={"question"}>
            <p>{this.props.body}</p>
        </div>
    }
}

export default Question;