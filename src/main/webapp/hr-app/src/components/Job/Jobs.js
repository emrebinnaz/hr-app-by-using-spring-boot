import React, {Component} from 'react';
import {getAllJobs} from "../../requests/JobRequests";
import JobCard from "./JobCard";
import Spinner from 'react-bootstrap/Spinner'
import {isUserHrManager} from "../../Authorities/Authorities";

class Jobs extends Component {

    constructor(props) {
        super(props);
        this.state = {
            jobs : [],
            loading : true
        }
    }

    componentDidMount = async () => {
        const response = await getAllJobs();

        this.setState({
            jobs : response.data.jobDTOList,
            loading : false
        })
    }

    render() {
        const {jobs,loading} = this.state;
        return (
            <div className = {"container mt-5"}>
                {
                    loading ?   <Spinner animation="border" role="status"/>
                            :
                                jobs.map((job) => {
                                    return (
                                        <JobCard job = {job} key ={job.id}></JobCard>
                                    )
                                })
                }
            </div>
        );
    }
}

export default Jobs;