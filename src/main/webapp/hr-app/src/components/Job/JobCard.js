import React, {Component} from 'react';
import { withRouter } from 'react-router-dom';
import {isUserHrManager} from "../../Authorities/Authorities";
import {OverlayTrigger, Card, Button, Tooltip} from "react-bootstrap";
import {formatDate} from "../../Helpers/DateFormat";
import {sendDeleteJobRequest} from "../../requests/JobRequests";
import CustomizedSnackbar from "../Other/CustomizedSnackbar";

const renderTooltip = (props) => (
    <Tooltip id="button-tooltip" {...props}>
        {isUserHrManager() ?
            "See all job applications and job details" :
            "Apply to job and see job details"
        }
    </Tooltip>
);


class JobCard extends Component {

    state = {
        isDeleted : false,
        message : '',
        isShowMessage : false,
    }

    goToJobDetailPage = (e,job) => {
        e.preventDefault();
        this.props.history.push(`/viewJobDetails/${job.id}`);

    }

    goToEditJobPage = (e,jobId) => {
        e.preventDefault();
        this.props.history.push(`/editJob/${jobId}`);
    }

    deleteJob = async (e,jobId) => {
        e.preventDefault();
        const response = await sendDeleteJobRequest(jobId);

        this.setState({
            isDeleted : response.data.success,
            message : response.data.message
        })
        this.showMessage();
        this.setJobs();

    }

    showMessage = () =>{
        this.setState({
            isShowMessage : true,
        })
    }

    closeMessage = () => {
        this.setState({
            isShowMessage : false
        })
    }

    render() {
        const {job} = this.props;
        const {isDeleted,message,isShowMessage} = this.state;
        return (
            <div className={"col-md-6 float-left mt-5"}>
                <Card>
                    <Card.Header as="h5">{job.title}</Card.Header>
                    <Card.Body>
                        <Card.Title> Son başvuru tarihi : {formatDate(job.lastApplicationDate)}</Card.Title>
                        <div>
                            <OverlayTrigger placement="right"
                                            delay={{ show: 250, hide: 400 }}
                                            overlay={renderTooltip}>
                                <Button variant="success"
                                        onClick={(e) => {this.goToJobDetailPage(e,job)}}>İlan Detayları
                                </Button>
                            </OverlayTrigger>
                            {isUserHrManager() ?
                                <div className={"float-right"}>

                                    <Button variant={"primary"}
                                            onClick={(e) => this.goToEditJobPage(e,job.id)}>Edit Job
                                    </Button>

                                    <Button variant={"danger"}
                                            onClick={(e) => this.deleteJob(e,job.id)}>Delete Job
                                    </Button>
                                </div> :

                                null
                            }
                        </div>
                    </Card.Body>
                </Card>
                {isShowMessage ?
                    <CustomizedSnackbar
                        vertical={"bottom"}
                        horizontal={"right"}
                        open = {isShowMessage}
                        handleClose = {this.closeMessage}
                        message={message}
                        messageType={isDeleted ? "SUCCESS" : "ERROR" }/>
                    :
                    null
                }
            </div>
        );
    }
}

export default withRouter(JobCard);