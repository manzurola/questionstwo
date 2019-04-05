import React, {Component} from 'react';

class Review extends Component {
    render() {
        return <div>
            {this.props.correct ? 'you are correct' : 'you\'re wrong'}
        </div>
    }
}

export default Review;