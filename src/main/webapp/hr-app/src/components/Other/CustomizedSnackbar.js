import React, {Component} from 'react';
import Snackbar from '@material-ui/core/Snackbar';
import MuiAlert from '@material-ui/lab/Alert';

function Alert(props) {
    return <MuiAlert elevation={6} variant="filled" {...props} />;
}

class CustomizedSnackbar extends Component {

    render() {
        const {message, messageType, vertical, horizontal,handleClose,open} = this.props;
        return (
            <div>
                <Snackbar
                    anchorOrigin = {{ vertical, horizontal }}
                    open = {open}
                    message = {message}
                    autoHideDuration={2000}
                    onClose =  {handleClose}
                    key = {vertical + horizontal}>
                    <Alert onClose = {handleClose}
                           severity = {messageType.toLowerCase()}>
                        {message}
                    </Alert>
                </Snackbar>
            </div>
        );
    }
}

export default CustomizedSnackbar;