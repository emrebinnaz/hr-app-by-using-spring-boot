import React from 'react';
import Homepage from './components/Homepage'
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import LoginPage from "./components/Login/LoginPage";
import JobDetails from "./components/Job/JobDetails";
function App() {
  return (
      <Router>
        <Switch>
          <Route exact path = {"/"} component = {Homepage}/>
          <Route exact path = {"/login"} component = {LoginPage}/>
          <Route exact path = {"/viewJobDetails/:jobId"}  component = {JobDetails}/>
        </Switch>
      </Router>
  );
}

export default App;
