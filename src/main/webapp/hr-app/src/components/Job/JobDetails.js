import React, {Component} from 'react';
import JobDetailCard from "./JobDetailCard";
import JobApplicationForm from "../JobApplication/JobApplicationForm";
import{getJob} from "../../requests/JobRequests";
import {isUserHrManager} from "../../Authorities/Authorities";
import JobApplications from "../JobApplication/JobApplications";

class JobDetails extends Component {

    state = {
        message : "",
        success : false,
        job : ""
    }

    componentDidMount = async () => {
        const {jobId} = this.props.match.params;
        const response = await getJob(jobId);

        this.setState({
            message : response.data.message,
            success : response.data.success,
            job : response.data.jobDTO
        })
    }

    render() {
        const {jobId} = this.props.match.params;
        const {job} = this.state
        return (
            <div className={"mt-5 d-flex justify-content-around container"}>
                <JobDetailCard job = {job}></JobDetailCard>
                {isUserHrManager() ?
                    <JobApplications jobId = {jobId}/>
                    :
                    <JobApplicationForm jobId = {jobId}/>
                }
            </div>
        );
    }
}

export default JobDetails;