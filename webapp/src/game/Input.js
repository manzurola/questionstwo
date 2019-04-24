import React, {Component} from "react";
import "./Input.css";
import TextInput from "./TextInput";

export class Input extends Component {

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
        // document.addEventListener("keydown", this.handleKeyDown);
    }

    componentWillUnmount() {
        // document.removeEventListener("keypress", this.handleKeyPress);
        // document.removeEventListener("keydown", this.handleKeyDown);
    }

    handleKeyDown = (event) => {
        // console.log(event);
        if (event.key === 'Enter') {
            this.handleSubmit();
        } else if (event.key === 'Backspace') {
            let newText = this.state.text.slice(0, -1);
            this.setState({text: newText});
        }
    };

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
            <TextInput/>
                {/*<input style={{*/}
                    {/*boxSizing: 'border-box',*/}
                    {/*display: 'inline-block',*/}
                    {/*position: 'relative',*/}
                    {/*float: 'left',*/}
                    {/*padding: '10px 20px',*/}
                    {/*// border: '4px solid rgba(140,58,58,1)',*/}
                    {/*borderRadius: '30px',*/}
                    {/*margin: 'auto',*/}
                    {/*font: 'inherit',*/}
                    {/*height: 'auto',*/}
                    {/*width: '600px',*/}
                    {/*// textAlign: 'center',*/}
                    {/*verticalAlign: 'middle',*/}
                    {/*border: 'none',*/}
                    {/*boxShadow: '0px 2px 5px 0 rgba(0,0,0,0.3)',*/}
                    {/*// lineHeight: '90px',*/}

                {/*}} onChange={this.handleInputChange}*/}
                          {/*autoFocus*/}
                       {/*value={this.state.text}*/}
                {/*/>*/}
                {/*<button style={{*/}
                    {/*boxSizing: 'border-box',*/}
                    {/*display: 'inline-block',*/}
                    {/*position: 'relative',*/}
                    {/*float: 'left',*/}
                    {/*// height: '100%',*/}
                    {/*margin: '10px',*/}
                    {/*borderRadius: 0,*/}
                    {/*font: 'inherit',*/}
                    {/*width: 'auto'*/}
                {/*}} onClick={this.handleSubmit}*/}
                        {/*value={this.state.text}>*/}
                    {/*Submit*/}
                {/*</button>*/}
        </div>
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