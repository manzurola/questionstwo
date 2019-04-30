import React, {Component} from 'react';
import './SlideFromBottom.css';

let lastId = 0;

export default class EditableText extends Component {

    constructor(props) {
        super(props);
        lastId++;
        this._contenteditable = null;
        this.elemId = 'EditableText-' + lastId;
    }


    render() {
        return <div ref={c => (this._contenteditable = c)}
                    onInput={this.onInput}
                    onBlur={this.onBlur}
                    onFocus={this.onFocus}
                    onKeyDown={this.onKeyDown}
                    onKeyPress={this.onKeyPress}
                    onChange={this.onChange}
                    contentEditable={true}>
        </div>
    }

    onFocus = (event) => {
        console.log("onFocus");
        console.log(event);
    };

    onBlur = (event) => {
        console.log("onBlur");
        console.log(event);
    };

    onKeyDown = (event) => {
        // capture Tab key
        if (event.keyCode === 9) {
            event.preventDefault();
        } else if (event.keyCode === 229) {
            // filter noise keys
            event.preventDefault();
        }
        console.log("onKeyDown " + event.key + ", " + event.keyCode);
        this.printSelectionStart();
    };

    onKeyPress = (event) => {
        // capture Enter key
        if (event.key === 'Enter') {
            event.preventDefault();
            this.props.onSubmit();
        }
        // capture all else
        console.log("onKeyPress " + event.key + ", " + event.keyCode);
    };

    onInput = (event) => {
        const onChange = this.props.onChange || (()=>{});
        console.log("onInput");
        console.log(event);
        let text = event.target.innerText;
        console.log(text);
        this.setState({text}, onChange({text}));
    };

    onChange = (event) => {
        console.log("onChange");
        console.log(event);
    };

    printSelectionStart = () => {
        console.log("printing selection start");
        console.log(this._contenteditable);
    }
}