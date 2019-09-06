import React, {Component} from "react";
import "./TextInput.css";
import ContentEditable from "react-contenteditable";
import * as ReactDom from "react-dom";
import sanitizeHtml from 'sanitize-html';
import Text from "../common/Text";

export class TextInput extends Component {

    constructor(props) {
        super(props);
        this.contentEditable = React.createRef();
        this.state = {
            disabled: this.props.disabled || false,
            focused: true,
            html: this.props.value || '',
            empty: true,
            suggestion: '',
            previousKey: '',
        };
    }

    componentWillReceiveProps(nextProps) {
        console.log("TextInput - componentWillReceiveProps - next props: ");
        console.log(nextProps);
        // if (nextProps === prevProps) {
        //     return;
        // }

        let newState = {};
        if (nextProps.disabled !== this.state.disabled) {
            newState.disabled = nextProps.disabled;
        }
        if (nextProps.value !== this.state.html) {
            newState.html = nextProps.value;
        }
        if (nextProps.focused && nextProps.focused !== this.state.focused) {
            this.focus();       
        }

        this.setState({...newState});
    }

    render() {
        return <div className={'textarea'} onClick={this.focus}>
            {this.renderLabel()}
            {this.renderContent()}
        </div>
    }

    renderLabel = () => {
        return <div
            className={['textarea-label', this.state.empty ? 'withoutcontent' : 'withcontent'].join(' ')}><Text text={this.props.label}/></div>
    };

    renderContent =() => {
        return <div className={'content-wrapper'}>
            <ContentEditable
                className={'textarea-contenteditable'}
                ref={(node) => {
                    this.contentEditable = node
                }}
                disabled={this.state.disabled}
                onFocus={this.handleFocus}
                onBlur={this.handleBlur}
                onInput={this.handleInput}
                onKeyDown={this.onKeyDown}
                tagName={'div'}
                onChange={this.handleChange} // handle innerHTML change
                html={this.state.html}>
            </ContentEditable>
            {/* {this.renderAutoSuggest()} */}
        </div>
    };

    renderAutoSuggest = () => {
        return this.state.focused && !this.state.empty ?
            <span contentEditable={false} className={'textarea-autosuggest'}>suggestion</span> : null;
    };

    handleChange = (event) => {
        console.log("on change in content editable");
        console.log(event);
        // let value = sanitizeHtml(event.target.value);
        // event.target.value = value;
        let value = event.target.value;
        if (value.includes('<br>')) {
            // this.handleSubmit(event);
        } else if (value.includes('\t')) {
            console.log("should auto suggest");
        } else {
            console.log("value " + value + ", " + !value);
            let callback = (e) => {
                if (this.props.onChange) {
                    this.props.onChange(e);
                }
            }
            this.setState({html: value, empty: !value}, (callback)(event));
        }
    };

    onKeyDown = (event) => {
        event.persist();
        console.log("onKeyDown");
        console.log(event);
        let nextKey = event.keyCode;
        if (nextKey === 13) { // capture Enter
            this.handleSubmit(event);
        } else if (nextKey === 9) { //capture Tab
            event.preventDefault();
            console.log("pressed tab for auto complete");
        } else if (nextKey === 32 && nextKey === this.state.previousKey) {
            event.preventDefault();
            console.log("preventing second spacebar");
        }

        this.setState({previousKey: nextKey});
    };

    handleSubmit = (event) => {
        console.log("handleSubmit");
        let value = this.state.html;
        console.log("value: " + value);
        console.log(event);
        
        let e = {
            target: {
                value: this.state.html
            }
        }
        this.setState({disabled: true}, (() => this.props.onSubmit(e))(e));
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