import React, {Component} from "react";
import "./TextInput.css";
import ContentEditable from "react-contenteditable";
import * as ReactDom from "react-dom";

export class TextInput extends Component {

    constructor(props) {
        super(props);
        this.contentEditable = React.createRef();
        this.state = {
            disabled: false,
            text: '',
            words: [],
            html: '',
            suggestion: ''
        };
    }

    componentDidMount() {
        // document.addEventListener("keypress", this.handleKeyPress);
        // document.addEventListener("keydown", this.handleKeyDown);
        console.log(this.contentEditable);
        ReactDom.findDOMNode(this.contentEditable).focus();
        // this.contentEditable.focus();
    }

    componentWillUnmount() {

        // document.removeEventListener("keypress", this.handleKeyPress);
        // document.removeEventListener("keydown", this.handleKeyDown);
    }

    render() {
        return <div>
            <ContentEditable
                className={'textarea-contenteditable'}
                ref={(node) => { this.contentEditable = node }}
                disabled={this.state.disabled}
                onBlur={this.handleBlur}
                onInput={this.handleInput}
                onKeyDown={this.onKeyDown}
                tagName={'div'}
                onChange={this.handleChange} // handle innerHTML change
                html={this.state.html}>
            </ContentEditable>
            <span contentEditable={false}>suggestion</span>
        </div>
    }

    handleChange = (event) => {
        console.log("on change in content editable");
        console.log(event);
        let value = event.target.value;
        if (value.includes('<br>')) {
            // this.handleSubmit(event);
        } else if (value.includes('\t')) {
            console.log("should auto suggest");
        } else {
            this.setState({html: value}, () => this.props.onChange(event));
        }
    };

    onKeyDown = (event) => {
        event.persist();
        console.log("onKeyDown");
        console.log(event);
        if (event.keyCode === 13) { // capture Enter
            this.handleSubmit(event);
        } else if (event.keyCode === 9) { //capture Tab
            event.preventDefault();
            console.log("pressed tab for auto complete");
        }
    };

    handleSubmit = (event) => {
        console.log("handleSubmit");
        let value = this.state.html;
        console.log("value: " + value);
        console.log(event);
        this.setState({disabled: true}, () => this.props.onSubmit(value));
    };

    handleBlur = (event) => {
        console.log("on blur");
        console.log(event);
    };

    handleInput = (event) => {
        console.log("on input");
        console.log(event);
    }

}