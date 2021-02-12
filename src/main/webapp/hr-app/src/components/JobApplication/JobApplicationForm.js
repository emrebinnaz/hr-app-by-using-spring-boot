import React, {Component} from 'react';
import {Form, Button, Col, Card, InputGroup} from 'react-bootstrap'
import CustomizedSnackbar from "../Other/CustomizedSnackbar";
import {applyToJob} from "../../requests/JobApplicationRequests";
class JobApplicationForm extends Component {

    state = {

        applicantAddress : '',
        applicantName : '',
        applicantEmail : 0,
        applicantPhone : '',
        applicantResume : null,
        applicantSurname : '',
        thoughtsOfApplicantOnTheJob : '',
        isSubmittedForm : false,
        message : '',
        success : false
    }
    changeInput = (e) =>{
        this.setState({
            [e.target.name]:e.target.value
        });
    }

    handleKey = (e) =>  {
        e.target.style.height = 'inherit';
        e.target.style.height = `${e.target.scrollHeight}px`;
    }

    applyToJob = async (e) => {
        const {jobId} = this.props;

        e.preventDefault();
        const {applicantAddress,
            applicantName,
            applicantEmail,
            applicantPhone,
            applicantResume,
            applicantSurname ,
            thoughtsOfApplicantOnTheJob} = this.state;

        const jobApplication = {
            applicantName,
            applicantAddress,
            applicantEmail,
            applicantPhone,
            applicantResume,
            applicantSurname ,
            thoughtsOfApplicantOnTheJob
        };
        const response = await applyToJob(jobApplication,jobId);

        this.showMessage(response.data);
    }

    showMessage = (data) =>{
        this.setState({
            isSubmittedForm : true,
            message :data.message,
            success : data.success
        })
    }

    closeMessage = () => {
        this.setState({
            isSubmittedForm : false
        })
    }

    setResume = (e) => {
        this.setState({
            applicantResume : e.target.files[0]
        })
    }

    render() {
        const {applicantAddress,
            applicantName,
            applicantEmail,
            applicantPhone,
            applicantSurname ,
            thoughtsOfApplicantOnTheJob,
            isSubmittedForm,
            message,
            success} = this.state;
        return (
            <div>
                <Card>
                    <Card.Header className={"text-center"}>Job Application Form</Card.Header>
                    <Card.Body>
                        <Form onSubmit = {(e) => this.applyToJob(e)}
                              enctype="multipart/form-data">
                            <Form.Row>

                                <Form.Group as={Col} controlId="formGridName">
                                    <Form.Label>Name</Form.Label>
                                    <Form.Control type="text"
                                                  placeholder="Name"
                                                  name = "applicantName"
                                                  value ={applicantName}
                                                  onChange={(e) => this.changeInput(e)}
                                                  required/>
                                </Form.Group>
                                <Form.Group as={Col} controlId="formGridSurname">
                                    <Form.Label>Surname</Form.Label>
                                    <Form.Control type="text"
                                                  placeholder="Surname"
                                                  name = {"applicantSurname"}
                                                  value = {applicantSurname}
                                                  onChange={(e) => this.changeInput(e)}
                                                  required/>
                                </Form.Group>
                            </Form.Row>
                            <Form.Row>
                                <Form.Group  as={Col} controlId="formGridAddress">
                                    <Form.Label>Address</Form.Label>
                                    <Form.Control placeholder="Address"
                                                  required
                                                  name = "applicantAddress"
                                                  value ={applicantAddress}
                                                  onChange={(e) => this.changeInput(e)}
                                                  type = "text"/>

                                </Form.Group>
                            </Form.Row>
                            <Form.Row>
                                <Form.Group as={Col} controlId="formGridEmail">
                                    <Form.Label>Email</Form.Label>
                                    <Form.Control type="email"
                                                  placeholder="Enter email"
                                                  name = {"applicantEmail"}
                                                  value = {applicantEmail}
                                                  onChange={(e) => this.changeInput(e)}
                                                  required/>
                                </Form.Group>
                                <Form.Group as={Col} controlId="formGridPhone">
                                    <Form.Label>Phone</Form.Label>
                                    <Form.Control type="text"
                                                  placeholder="Phone"
                                                  name = {"applicantPhone"}
                                                  value = {applicantPhone}
                                                  onChange={(e) => this.changeInput(e)}
                                                  required/>
                                </Form.Group>
                            </Form.Row>
                            <Form.Row>
                                <Form.Group as={Col} controlId="formGridThoughts">
                                    <Form.Label>What are you consider about the job ? </Form.Label>
                                    <InputGroup>
                                        <Form.Control required autoComplete="off"
                                                      as = "textarea"
                                                      type="text" name="thoughtsOfApplicantOnTheJob"
                                                      value = {thoughtsOfApplicantOnTheJob}
                                                      onKeyDown = {this.handleKey}
                                                      onKeyUp  = {this.handleKey}
                                                      onChange={(e) => this.changeInput(e)}
                                                      placeholder="Please explain your thoughts on job briefly."/>
                                    </InputGroup>
                                </Form.Group>
                            </Form.Row>
                            <Form.Group>
                                <input type ="file"
                                       name = "applicantResume"
                                       required={true}
                                       onChange={(e) => this.setResume(e)}/>
                            </Form.Group>
                            <Button variant="primary" type="submit">
                                Submit
                            </Button>
                        </Form>
                    </Card.Body>
                </Card>
                {isSubmittedForm ?
                    <CustomizedSnackbar
                        vertical={"bottom"}
                        horizontal={"right"}
                        open = {isSubmittedForm}
                        handleClose = {this.closeMessage}
                        message={message}
                        messageType={success ? "SUCCESS" : "ERROR" }/>
                    :
                    null
                }
            </div>
        );
    }
}

export default JobApplicationForm;