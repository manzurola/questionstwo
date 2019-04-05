import './Answer.css';
import React, {Component} from 'react';

class Answer extends Component {

    constructor(props) {
        super(props);
        this.state = {
            inputValue: ''
        };
    }

    render() {
        return <div >
            <input className={"answer"} onChange={evt => this.updateInputValue(evt)}/>
        </div>
    }

    updateInputValue(evt) {
        this.setState({
            inputValue: evt.target.value
        }, () => this.props.onChange(this.state.inputValue));
    }
}

export default Answer;