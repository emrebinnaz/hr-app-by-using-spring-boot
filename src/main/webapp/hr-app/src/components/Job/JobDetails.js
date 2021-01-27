import React, {Component} from 'react';
import JobDetailCard from "./JobDetailCard";
import JobApplicationForm from "../JobApplication/JobApplicationForm";
import{getJob} from "../../requests/JobRequests";

class JobDetails extends Component {

    constructor(props) {
        super(props);
        this.setState({
            message : "",
            success : false,
            job : ""
        })
    }

    componentDidMount = async () => {
        const {jobId} = this.props.match.params;
        const response = await getJob(jobId);
        this.setState({
            message : response.data.message,
            success : response.data.success,
            job : response.data.jobDto
        })
    }

    render() {
        return (
            <div>
                emre
                <JobDetailCard></JobDetailCard>
                <JobApplicationForm></JobApplicationForm>
            </div>
        );
    }
}

export default JobDetails;