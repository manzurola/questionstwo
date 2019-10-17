import React, {Component} from 'react';

export default class Text extends Component {

    render() {
        return <span style={{
            fontSize: this.props.fontSize || 'inherit',
            fontWeight: this.props.fontWeight || 'inherit',
            color: this.props.color || '#434343',
            display: 'inline',
            whiteSpace: 'pre', // to keep whitespace at ends of string
        }}>{this.props.text}</span>
    }
}