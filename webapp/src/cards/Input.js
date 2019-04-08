import React, {Component} from "react";
import "./Input.css";
import TextInputOld from "./TextInput";

class Input extends Component {

    constructor(props) {
        super(props);
        this.state = {
            isAccepting: true,
            text: '',
        };

        this.handleInputChange = this.handleInputChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleKeyDown = this.handleKeyDown.bind(this);
        this.handleKeyPress = this.handleKeyPress.bind(this);
    }

    componentDidMount() {
        document.addEventListener("keypress", this.handleKeyPress);
        document.addEventListener("keydown", this.handleKeyDown);
    }

    componentWillUnmount() {
        document.removeEventListener("keypress", this.handleKeyPress);
        document.removeEventListener("keydown", this.handleKeyDown);
    }

    handleKeyDown(event) {
        // console.log(event);
        if (event.key === 'Enter') {
            this.handleSubmit();
        } else if (event.key === 'Backspace') {
            let newText = this.state.text.slice(0, -1);
            this.setState({text: newText});
        }
    }

    handleKeyPress(event) {
        console.log(event);
        if (event.key.match(/^(\w|\s|\S){1}$/)) {
            console.log("match " + event.key);
            let newText = this.state.text.concat(event.key);
            this.setState({text: newText});
        }
    }

    render() {
        return <div className={"Input"}>
            <input className={"Input-input"}/>
            <p className={"Input-label"}>{this.props.label}</p>
            <div className={"Input-output"}>{this.renderText()}</div>
        </div>
    }

    renderText() {
        return this.state.text.split('').map(char => {
            let span = <span className={"Input-output-text"}>{char}</span>;
            return span;
        })
    }

    handleInputChange(event) {
        if (event.key === '\n') return;
        let newText = this.state.text.concat(event.key);
        console.log(newText);
        event.stopPropagation();
        this.setState({text: newText});
    }

    handleSubmit(event) {
        if (this.props.onSubmit) {
            this.props.onSubmit(this.state.text);
        }
    }


}

export default Input;

class SubmitButton extends Component {
    render() {
        return (
            <button style={{
                boxSizing: 'border-box',
                display: 'inline-block',
                position: 'relative',
                float: 'left',
                height: '100%',
                borderRadius: 0,
                border: '4px solid blue',
                font: 'inherit',
            }}>
                Submit
            </button>
        );
    }
}