import React, {Component} from 'react';
import {getAllJobs} from "../../requests/JobRequests";
import JobCard from "./JobCard";
import Spinner from 'react-bootstrap/Spinner'
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

    handleDelete = (jobId) => {
        const jobs = this.state.jobs.filter(job => job.id !== jobId)
        this.setState({
            jobs : jobs
        })
    }

    render() {
        const {jobs,loading} = this.state;
        return (
            <div className = {"container mt-5"}>
                {
                    loading ?   <Spinner animation="border"
                                         role="status"/>
                            :
                                jobs.map((job) => {
                                    return (
                                        <JobCard job = {job}
                                                 key ={job.id}
                                                 handleDelete = {this.handleDelete}/>
                                    )
                                })
                }
            </div>
        );
    }
}

export default Jobs;