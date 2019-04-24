import React, {Component} from "react";
import TextField from '@material-ui/core/TextField';
import "./TextInput.css";
import autocorrect from 'autocorrect';

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

    constructor(props) {
        super(props);
        this.state = {
            disabled: true,
            value: '',
            disabled: false,
            focused: false,
        };

    }

    render() {
        // const { classes } = this.props;
        return <TextField
            id="standard-textarea"
            label={this.props.label}
            multiline
            autoFocus={this.props.autoFocus}
            className={"textinput"}
            onChange={(event) => this.onChange(event)}
            value={this.state.value}
            disabled={this.state.disabled}
            onFocus={this.onFocus}
            onBlur={this.onBlur}
            InputLabelProps={{
                classes: {
                    root: "textinput-label",
                    focused: "focus",
                    disabled: "textinput-label.disabled",
                    // shrink: "textinput-label-focus",
                },
                FormLabelClasses: {
                    // root: "textinput-label-focus",
                    // shrink: "textinput-label-focus",
                }
            }}
            InputProps={{
                classes: {
                    root: "textinput-input"
                }
            }}

            // margin="normal"
        />
    }

    onFocus = (event) => {
        console.log(event);
        this.setState({
            focused: true,
        })
    };

    onBlur = (event) => {
        console.log(event);
        this.setState({
            focused: false,
        })
    };

    onChange(event) {
        console.log(event);
        let value = event.target.value;
        // let lastChar = value.slice(-1);
        // if (lastChar === '\n') {
        //     this.setState({disabled: true}, () => this.props.onSubmit());
        //     // console.log(autocorrect(value));
        //
        // } else {
        //     this.setState({value: value}, ((event) => this.props.onChange(event))(event));
        // }


        const regex = /\n/;
        const noNewLine = value.replace(regex, "");
        if (value.localeCompare(noNewLine) !== 0) {
            this.setState({disabled: true}, () => this.props.onSubmit());
        } else {
            this.setState({value: value}, ((event) => this.props.onChange(event))(event));
        }
    }
}