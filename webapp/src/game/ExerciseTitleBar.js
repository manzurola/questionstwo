import React, {Component} from "react";
import "./ExerciseTitleBar.css";

export class ExerciseTitleBar extends Component {
    render() {
        return (
            <div className={"bar"}>
                <p>{this.props.text}</p>
            </div>
        );
    }
}

export default ExerciseTitleBar;