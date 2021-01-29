import React, {Component} from 'react';
import {Card, Form, Button, Col, InputGroup} from 'react-bootstrap';
import {getCurrentDate} from "../../../Helpers/DateFormat";
import {sendAddJobRequest} from "../../../requests/JobRequests";

class AddJobForm extends Component {
    constructor(props) {
        super(props);
        this.state = this.initialState;
    }

    initialState = {
        description : '',
        lastApplicationDate : '',
        numberOfPeopleToHire : 0,
        title : '',
        isAdded : false,
        isSubmittedForm : false,
        message : ''
    }

    resetAllInputs = () =>{
        this.setState(() => this.initialState);
    }

    changeInput = (e) =>{
        this.setState({
            [e.target.name]:e.target.value
        });
    }

    handleKeyDown = (e) =>  {
        e.target.style.height = 'inherit';
        e.target.style.height = `${e.target.scrollHeight}px`;
    }

    addJob = async (e) => {
        e.preventDefault();
        const { title,
            description,
            lastApplicationDate,
            numberOfPeopleToHire,
            isAdded,
            message,
            isSubmittedForm} = this.state

        var job = {
            title,
            description,
            lastApplicationDate,
            numberOfPeopleToHire,
        }
        const response = await sendAddJobRequest(job);
        console.log(response);

    }

    render() {
        const { title,
                description,
                lastApplicationDate,
                numberOfPeopleToHire,
                isAdded,
                message,
                isSubmittedForm} = this.state
        return (
            <div>
                <Card className={"container w-50 mt-5"}>
                    <Card.Header>Add Job</Card.Header>
                    <Form onReset = {this.resetAllInputs}
                          onSubmit = {(e) => this.addJob(e)}>
                        <Card.Body>
                            <Form.Row>
                                <Form.Group as={Col} controlId="formGridTitle">
                                    <Form.Label>Title</Form.Label>
                                    <InputGroup>
                                        <Form.Control required autoComplete="off"
                                                      type="text" name="title"
                                                      value={title}
                                                      onChange={(e) => this.changeInput(e)}
                                                      className={"bg-dark text-white"}
                                                      placeholder="Title"/>
                                    </InputGroup>
                                </Form.Group>
                                <Form.Group as={Col} controlId="formGridStartDate">
                                    <Form.Label>Last Application Date</Form.Label>
                                    <InputGroup>
                                        <Form.Control required autoComplete="off"
                                                      type="date" name="lastApplicationDate"
                                                      min = {getCurrentDate()}
                                                      value={lastApplicationDate}
                                                      onChange={(e) => this.changeInput(e)}
                                                      className={"bg-dark text-white"}
                                                      placeholder="Last Application Date"/>
                                    </InputGroup>
                                </Form.Group>
                            </Form.Row>
                            <Form.Row>
                                <Form.Group as={Col} controlId="formGridNumberOfPeopleToHire">
                                    <Form.Label>Number of People To Hire</Form.Label>
                                    <InputGroup>
                                        <Form.Control required autoComplete="off"
                                                      type="number" name="numberOfPeopleToHire"
                                                      min = {1}
                                                      value={numberOfPeopleToHire}
                                                      onChange={(e) => this.changeInput(e)}
                                                      className={"bg-dark text-white"}
                                                      placeholder="Number of People To Hire"/>
                                    </InputGroup>
                                </Form.Group>
                            </Form.Row>
                            <Form.Row>
                                <Form.Group as={Col} controlId="formGridDescrtiption">
                                    <Form.Label>Job Description</Form.Label>
                                    <InputGroup>
                                        <Form.Control required autoComplete="off"
                                                      as = "textarea"
                                                      type="text" name="description"
                                                      onKeyDown = {this.handleKeyDown}
                                                      value={description}
                                                      onChange={(e) => this.changeInput(e)}
                                                      className={"bg-dark text-white"}
                                                      placeholder="Job Description"/>
                                    </InputGroup>
                                </Form.Group>
                            </Form.Row>
                        </Card.Body>
                        <Card.Footer style={{"textAlign":"right"}}
                                     className={"d-flex justify-content-between"}>
                            <Button variant="success" type="submit" disabled = {isSubmittedForm}>
                                Submit
                            </Button>
                            <Button  variant="info" type="reset">
                                Reset
                            </Button>
                        </Card.Footer>
                    </Form>
                </Card>
            </div>
        );
    }
}

export default AddJobForm;