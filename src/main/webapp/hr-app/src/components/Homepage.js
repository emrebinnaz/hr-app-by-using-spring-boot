import React, {Component} from 'react';
import LoginBar from "./Login/LoginBar";
import {Button} from "react-bootstrap";
import {isUserHrManager} from "../Authorities/Authorities";
import Jobs from './Job/Jobs'
import {withRouter} from "react-router-dom";

class Homepage extends Component {

    goToAddJobPage = () => {
        this.props.history.push(`/addJob`);
    }

    render() {
        return (
            <div>
                <LoginBar></LoginBar>
                {isUserHrManager() ?
                    <div className={"text-center mt-1"}>
                        <Button onClick={this.goToAddJobPage}
                                className={"btn-outline-dark text-white"}>
                            + Add Job
                        </Button>
                    </div>
                    :
                    null
                }
                <Jobs></Jobs>
            </div>
        );
    }
}
export default withRouter(Homepage);