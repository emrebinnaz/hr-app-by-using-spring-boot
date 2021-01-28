import React, {Component} from 'react';
import {getJobApplicationsBy} from "../../requests/JobApplicationRequests";
import{Spinner,Card} from 'react-bootstrap'
import {Link} from "react-router-dom";
import ApplicantInformationModal from "../Applicant/ApplicantInformationModal";

class JobApplications extends Component {

    state = {
        jobApplications : [],
        loading : true
    }

    componentDidMount = async  () =>{

        const {jobId} = this.props;
        const response = await getJobApplicationsBy(jobId);
        this.setState({
            jobApplications : response.data.jobApplicationDTOList,
            loading : false
        })

    }

    showApplicantInformation = (e, jobApplication) =>{
        return <div><ApplicantInformationModal open = {true}/></div>;
    }

    render() {
        const {loading, jobApplications} = this.state;
        return (
            <Card>
                <Card.Header>Applications</Card.Header>
                <Card.Body>
                {
                    loading ?   <Spinner animation="border"
                                         role="status"
                                         className={"align-items-center"}/>
                        :
                            jobApplications.map((jobApplication) => {
                                return (
                                    <Link jobApplication = {jobApplication}
                                          onClick = {(e) => this.showApplicantInformation(e,jobApplication)}
                                           key ={jobApplication.id}
                                           className = {"d-block"}>
                                        {`${jobApplication.applicantName} ${jobApplication.applicantSurname} iş başvurusu görüntüle`}
                                    </Link>
                                )
                            })
                }
                </Card.Body>
            </Card>
        );
    }
}

export default JobApplications;