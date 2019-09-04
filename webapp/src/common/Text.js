import React, {Component} from 'react';

export default class Text extends Component {

    render() {
        return <span style={{
            fontFamily: this.props.fontFamily || 'Myriad Pro, sans-serif',
            fontSize: this.props.fontSize || 'inherit',
            fontWeight: this.props.fontWeight || 300,
            color: this.props.color || '#434343',
            display: 'inline',
            whiteSpace: 'pre', // to keep whitespace at ends of string
        }}>{this.props.text}</span>
    }
}