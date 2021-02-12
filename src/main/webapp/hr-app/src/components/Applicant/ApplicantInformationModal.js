import Modal from 'react-bootstrap/Modal'
import {useState} from "react";
import {InputGroup, ListGroup} from "react-bootstrap";
import {getResume} from "../../requests/FileRequests";

export default function ApplicantInformationModal(props) {
    const [show, setShow] = useState(true);
    const {jobApplication} = props;

    const closeModal = () => {
        setShow(false)
        props.handleClose();
    }

    const showResume = async (e, jobApplicationId) => {
        e.preventDefault();

        await getResume(jobApplicationId).then(response => {
            const url = window.URL.createObjectURL(new Blob([response.data], {type: 'application/pdf'}));
            window.open(url);
        });
    }

    return (
        <>
            <Modal
                show={show}
                onHide={closeModal}
                dialogClassName="modal-90w"
                aria-labelledby="example-custom-modal-styling-title">
                <Modal.Header closeButton>
                    <Modal.Title id="example-custom-modal-styling-title">
                        Job Application Information
                    </Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <ListGroup variant="flush">
                        <ListGroup.Item>
                            Applicant Name :
                            <span className={"font-weight-bold"}>
                                {jobApplication.applicantName}
                            </span>
                        </ListGroup.Item>
                        <ListGroup.Item>
                            Applicant Surname :
                            <span className={"font-weight-bold"}>
                                {jobApplication.applicantSurname}
                            </span>
                        </ListGroup.Item>
                        <ListGroup.Item>
                            Applicant Email :
                            <span className={"font-weight-bold"}>
                                {jobApplication.applicantEmail}
                            </span>
                        </ListGroup.Item>
                        <ListGroup.Item>
                            Applicant Phone :
                            <span className={"font-weight-bold"}>
                                {jobApplication.applicantPhone}
                            </span>
                        </ListGroup.Item>
                        <ListGroup.Item>
                            Thoughts of applicant on the Job :
                            <InputGroup className={"font-weight-bold form-control"}
                                      readOnly={true}>
                                {jobApplication.thoughtsOfApplicantOnTheJob}
                            </InputGroup>
                        </ListGroup.Item>
                        <ListGroup.Item>

                            <button className={"btn btn-dark"}
                                    onClick={(e) => showResume(e,jobApplication.id)}>
                                Show applicant resume
                            </button>
                        </ListGroup.Item>
                    </ListGroup>
                </Modal.Body>
            </Modal>
        </>
    );
}
