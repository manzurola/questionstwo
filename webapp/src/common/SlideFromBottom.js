import React, {Component} from 'react';
import './SlideFromBottom.css';

export default class SlideFromBottom extends Component {

    render() {
        return <div className={'slide-from-bottom'}>
            {this.props.children}
        </div>
    }
}