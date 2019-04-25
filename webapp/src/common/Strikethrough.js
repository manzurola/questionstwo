import React, {Component} from 'react';
import './Strikethrough.css';

export default class Strikethrough extends Component {

    render() {
        return <div className={'strikethrough'}>
            {this.props.children}
        </div>
    }
}