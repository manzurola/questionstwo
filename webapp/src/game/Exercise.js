import React, {Component} from "react";
import './Exercise.css';

export class Exercise extends Component {

    render() {
        return <div className={"exercise"}>
            <p>{this.props.title}</p>
        </div>
    }
}

export default Exercise;