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
            focused: false,
            text: '',
            words: [],
            html: '',
            empty: true,
            suggestion: ''
        };
    }

    componentDidMount() {
        this.focus();
    }

    render() {
        return <div className={'textarea'} onClick={this.focus}>
            <ContentEditable
                className={'textarea-contenteditable'}
                ref={(node) => { this.contentEditable = node }}
                disabled={this.state.disabled}
                onFocus={this.handleFocus}
                onBlur={this.handleBlur}
                onInput={this.handleInput}
                onKeyDown={this.onKeyDown}
                tagName={'div'}
                onChange={this.handleChange} // handle innerHTML change
                html={this.state.html}>
            </ContentEditable>
            {this.renderAutoSuggest()}
            {this.renderLabel()}
        </div>
    }

    renderLabel = () => {
        return <div className={['textarea-label', this.state.empty ? 'textarea-label-withoutcontent' : 'textarea-label-withcontent'].join(' ')}>{this.props.label}</div>
    };

    renderAutoSuggest = () => {
        return this.state.focused && !this.state.empty ? <span contentEditable={false} className={'textarea-autosuggest'}>suggestion</span> : null;
    };

    handleChange = (event) => {
        console.log("on change in content editable");
        console.log(event);
        let value = event.target.value;
        if (value.includes('<br>')) {
            // this.handleSubmit(event);
        } else if (value.includes('\t')) {
            console.log("should auto suggest");
        } else {
            console.log("value " + value + ", " + !value);
            this.setState({html: value, empty: !value}, () => this.props.onChange(event));
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

    focus = () => {
        let findDOMNode = ReactDom.findDOMNode(this.contentEditable);
        console.log(findDOMNode);
        findDOMNode.focus();
    };

    handleFocus = (event) => {
        console.log("focusing...")
        this.setState({
            focused: true
        });
    };

    handleBlur = (event) => {
        console.log("on blur");
        // event.preventDefault();
        console.log(event);
        // this.focus();
        this.setState({
            focused: false
        });
    };

    handleInput = (event) => {
        console.log("on input");
        console.log(event);
    }

}