import React, {Component} from "react";
import './Card.css';

export class Card extends Component {

    render() {
        return <div className={'card'}>
            <div className={'card-child-wrapper'}>
                {this.props.children}
            </div>
        </div>
    }
}