import React, {Component} from 'react';
import {getJobApplicationsBy} from "../../requests/JobApplicationRequests";
import{Spinner,Card} from 'react-bootstrap'
import {Link} from "react-router-dom";
import ApplicantInformationModal from "../Applicant/ApplicantInformationModal";

class JobApplications extends Component {

    state = {
        jobApplications : [],
        loading : true,
        showApplicantInformation : false,
        clickedJobApplication : ''
    }

    componentDidMount = async  () =>{

        const {jobId} = this.props;
        const response = await getJobApplicationsBy(jobId);
        this.setState({
            jobApplications : response.data.jobApplicationDTOList,
            loading : false
        })

    }

    showApplicantInformation = (e,jobApplication) =>{

        e.preventDefault()
        this.setState({
            showApplicantInformation : true,
            clickedJobApplication : jobApplication
        })
    }

    closeApplicantInformation = (e,jobApplication) => {

        this.setState({
            showApplicantInformation : false
        })
    }

    render() {
        const {loading, jobApplications,showApplicantInformation,clickedJobApplication} = this.state;
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
                    {showApplicantInformation ?
                        <ApplicantInformationModal jobApplication = {clickedJobApplication}
                                                   handleClose = {this.closeApplicantInformation}/>
                        :
                        null
                    }
                </Card.Body>
            </Card>
        );
    }
}

export default JobApplications;