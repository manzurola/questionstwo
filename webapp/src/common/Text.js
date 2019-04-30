import React, {Component} from 'react';

export default class Text extends Component {

    render() {
        return <span style={{
            fontFamily: this.props.fontFamily || 'Myriad Pro, sans-serif',
            fontSize: this.props.fontSize || '12pt',
            fontWeight: this.props.fontWeight || 300,
            color: this.props.color || '#434343',
        }}>
            {this.props.children}
        </span>
    }
}