import React from 'react';
import {Route} from 'react-router-dom';
import {isLogged} from "../../Authentication/Authentication";
import {isUserHrManager} from "../../Authorities/Authorities";

const HrManagerRoute = ({component: Component, ...rest}) => {

    return (
        <Route {...rest} render={props => (
            isLogged && isUserHrManager  ?
                <Component {...props} /> :
                null
        )} />
    );
};
export default HrManagerRoute;