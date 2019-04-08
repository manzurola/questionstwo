import React, {Component} from "react";
import './TextInput.css';

export class TextInput extends Component {

    constructor(props) {
        super(props);
        this.state = {
            value: '',
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        this.setState({value: event.target.value});
    }

    handleSubmit(event) {
        alert('Something was submitted: ' + this.state.value);
        event.preventDefault();
    }

    render() {
        return (
            <form className={'TextInput'} onSubmit={this.handleSubmit}>
                <input className={'TextInput-input'}
                       autoFocus
                       type="text"
                       value={this.props.value}
                       placeholder={this.props.placeholder}
                       onChange={this.handleChange} />
                <input className={'TextInput-submit'}
                       type="submit"
                       value="Submit" />
            </form>
        );
    }
}

export default TextInput;