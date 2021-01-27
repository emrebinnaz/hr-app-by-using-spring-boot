import React, {Component} from 'react';
import LoginBar from "./Login/LoginBar";
import Jobs from './Job/Jobs'

class Homepage extends Component {
    render() {
        return (
            <div>
                <LoginBar></LoginBar>
                <Jobs></Jobs>
            </div>
        );
    }
}

export default Homepage;