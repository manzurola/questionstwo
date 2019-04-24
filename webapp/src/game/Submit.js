import React, {Component} from 'react';

class Submit extends Component {
    render() {
        return (
            <div>
                <button onClick={this.props.onClick}>Submit</button>
            </div>
        );
    }
}

export default Submit;