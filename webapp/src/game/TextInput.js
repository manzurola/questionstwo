import React, {Component} from "react";
import TextField from '@material-ui/core/TextField';
import "./TextInput.css";
import { connect } from "tls";

export class TextInput extends Component {

    constructor(props) {
        super(props);
        this.state = {
            value: '',
            disabled: false,
            focused: true,
            autoFocus: true,
        };
    }

    componentWillReceiveProps(nextProps) {
        if (this.props === nextProps) return;
        console.log('TextInput.componentWillReceiveProps-nextProps:');
        console.log(nextProps);
        this.setState({
            disabled: !!nextProps.disabled,
            focused: !!nextProps.focused,
        });
    }

    render() {
        // const { classes } = this.props;
        return <TextField
            id="standard-textarea"
            label={this.props.label}
            multiline
            autoFocus={this.state.autoFocus}
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
        let value = event.target.value;
        console.log("TextInput.onChange");
        console.log(value);

        const regex = /\n/;
        const noNewLine = value.replace(regex, ""); // user can type enter anywhere within the string
        if (value.localeCompare(noNewLine) !== 0) {
            this.setState({value: ''}, (() => this.props.onSubmit(event))(event));
        } else {
            let callback = () => {
                if (this.props.onChange) {
                    this.props.onChange(event);
                }
            }
            this.setState({value: value}, callback);
        }
    }


}