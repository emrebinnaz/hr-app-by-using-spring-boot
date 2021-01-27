import React, {Component} from 'react';
import { Redirect } from 'react-router-dom';
import { withRouter } from 'react-router-dom';
import {isUserHrManager} from "../../Authorities/Authorities";
import {OverlayTrigger, Card, Button, Tooltip} from "react-bootstrap";

const renderTooltip = (props) => (
    <Tooltip id="button-tooltip" {...props}>
        {isUserHrManager() ?
            "See all job applications and job details" :
            "Apply to job and see job details"
        }
    </Tooltip>
);


class JobCard extends Component {

    constructor(props) {
        super(props);

    }


    goToJobDetailPage = (e,job) => {
        e.preventDefault();
        this.props.history.push(`/viewJobDetails/${job.id}`);

    }

    goToEditJobPage = (e,jobId) => {

    }

    deleteJob = (e,jobId) => {
        //TODO: Write delete request in request folder
    }

    render() {
        const {job} = this.props;

        return (
            <div className={"col-md-6 float-left mt-5"}>
                <Card>
                    <Card.Header as="h5">{job.title}</Card.Header>
                    <Card.Body>
                        <Card.Title> Son başvuru tarihi : {job.lastApplicationDate}</Card.Title>
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
            </div>
        );
    }
}

export default withRouter(JobCard);