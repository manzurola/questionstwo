import React, {Component} from 'react';

export default class Text extends Component {

    render() {
        return <span style={{
            fontFamily: this.props.fontFamily || 'Myriad Pro, sans-serif',
            fontSize: this.props.fontSize || '14pt',
            fontWeight: this.props.fontWeight || 300,
            color: this.props.color || '#434343',
            textDecoration: this.props.strikethrough ? 'strikethrough' : '',
        }}>
            {this.props.children}
        </span>
    }
}