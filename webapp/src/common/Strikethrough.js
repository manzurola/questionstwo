import React, {Component} from 'react';
import './Strikethrough.css';

export default class Strikethrough extends Component {

    render() {
        return <span className={'strikethrough'}>
            {this.props.children}
        </span>
    }
}