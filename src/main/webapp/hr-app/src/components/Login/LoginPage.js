import React, {Component} from 'react';
import Card from 'react-bootstrap/Card'
import Button from 'react-bootstrap/Button'
import Form from 'react-bootstrap/Form'
import {sendLoginRequest} from "../../requests/LoginRequests";
import {authentication} from "../../Authentication/Authentication";
import CustomizedSnackbar from "../Other/CustomizedSnackbar";

class LoginPage extends Component {

    state = {
        username : '',
        password : '',
        errorMessage : false,
        isOpenedErrorMessage : false
    }

    login = async (e) => {
        e.preventDefault();
        const {username,password} = this.state;
        var loginRequestDTO = {
            username : username,
            password: password
        }
        const response = await sendLoginRequest(loginRequestDTO)

        if(this.isLoginSuccess(response.data.success)) {
            authentication(response.data.jwtToken);
            this.props.history.push(`/`);
        } else {
            this.setState({
                errorMessage : response.data.message,
                isOpenedErrorMessage : true
            })
        }
    }

    isLoginSuccess = (success) => {
        return success;
    }

    changeInput = (event) => {
        this.setState({
            [event.target.name]: event.target.value
        })
    }

    closeErrorMessage = () => {
        this.setState({
            isOpenedErrorMessage : false
        })
    }

    render() {
        const {isOpenedErrorMessage, errorMessage} = this.state
        return (
            <div className={"container mt-5 w-50 d-flex justify-content-center"}>
                <Card >
                    <Card.Header className="text-center">Login as HR Manager</Card.Header>
                    <Card.Body>
                        <Form>
                            <Form.Group controlId="formBasicEmail">
                                <Form.Label>Username</Form.Label>
                                <Form.Control type="email"
                                              placeholder="Enter username"
                                              name = "username"
                                              onChange={this.changeInput}/>
                                <Form.Text className="text-muted">
                                    We'll never share your username with anyone else.
                                </Form.Text>
                            </Form.Group>

                            <Form.Group controlId="formBasicPassword">
                                <Form.Label>Password</Form.Label>
                                <Form.Control type="password"
                                              placeholder="Password"
                                              onChange={this.changeInput}
                                              name = "password"/>
                            </Form.Group>
                            <Form.Group controlId="formBasicCheckbox">
                                <Form.Check type="checkbox" label="Check me out" />
                            </Form.Group>
                            <Button variant="primary"
                                    type="submit"
                                     onClick={(e) =>  this.login(e)}>
                                Submit
                            </Button>
                        </Form>
                    </Card.Body>
                    <Card.Footer className="text-muted">Copyright</Card.Footer>
                </Card>
                {isOpenedErrorMessage ?
                    <CustomizedSnackbar
                        vertical={"bottom"}
                        horizontal={"right"}
                        open = {isOpenedErrorMessage}
                        handleClose = {this.closeErrorMessage}
                        message={errorMessage}
                        messageType={"ERROR"}/> : null }
            </div>

        );
    }
}

export default LoginPage;