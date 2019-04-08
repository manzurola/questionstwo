import React, {Component} from "react";
import { withStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import "./TextInput.css";

const styles = theme => ({
    container: {
        display: 'flex',
        flexWrap: 'wrap',
    },
    textField: {
        marginLeft: theme.spacing.unit,
        marginRight: theme.spacing.unit,
        width: 200,
    },
    dense: {
        marginTop: 19,
    },
    menu: {
        width: 200,
    },
});

export class TextInput extends Component {

    render() {
        // const { classes } = this.props;
        return <TextField
            id="standard-textarea"
            label="Put the sentence into the plural"
            placeholder="Type something"
            multiline
            className={"textinput"}
            // margin="normal"
        />
    }
}