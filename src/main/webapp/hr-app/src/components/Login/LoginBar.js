import React, {Component} from 'react';
import {Navbar,Button,Form,FormControl} from 'react-bootstrap'
import {InputGroup} from "react-bootstrap";
import {sendLoginRequest} from "../../requests/LoginRequests";
import {isLogged,getUsernameOfCurrentUser} from "../../Authentication/Authentication";
import {isUserHrManager} from "../../Authorities/Authorities";
import { withRouter } from 'react-router-dom';
import CustomizedSnackbar from "../Other/CustomizedSnackbar";
class LoginBar extends Component {

    state = {
        success: false,
        message: '',
        showLoginStatus: false
    }

    goToLoginPage = (e) => {
        e.preventDefault();
        this.props.history.push(`/login/`);
    }

    componentDidMount() {
        if (isLogged) { //TODO : Good practise, it is error
            this.setState({
                success: true,
                message: "Login is successfull"
            })
        }
    }

    closeMessage = () => {
        this.setState({
            success: false
        })
    }

    render() {
        const {success, message} = this.state;
        return (
            <div className={"text-center text-white bg-info py-2"}>
                <Navbar className="justify-content-between">
                    <div>
                        {(isLogged && isUserHrManager()) ? `Welcome ${getUsernameOfCurrentUser()}` : "Login as HR Manager"}
                    </div>
                    <Button variant={"outline-dark"}
                            onClick={(e) => this.goToLoginPage(e)}
                    >Login</Button>
                </Navbar>
                <CustomizedSnackbar
                    vertical={"bottom"}
                    horizontal={"right"}
                    open={success}
                    handleClose={this.closeMessage}
                    message={message}
                    messageType={"SUCCESS"}/>
            </div>
        );
    }
}

export default withRouter(LoginBar);