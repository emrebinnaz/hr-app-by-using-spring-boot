import Modal from 'react-bootstrap/Modal'
import {useState} from "react";
import {Button} from "@material-ui/core";

export default function DeletedJobModal(props) {

    const {handleClose,job,handleDelete} = props;
    const [show, setShow] = useState(true);

    const closeModal = () => {
        setShow(false)
        handleClose();

    }

    const deleteJob = (e,jobId) => {
        e.preventDefault();
        setShow(false)
        handleDelete(e,jobId);

    }

    return (
        <>

            <Modal show={show} onHide={closeModal}>
                <Modal.Header closeButton>
                    <Modal.Title>Modal heading</Modal.Title>
                </Modal.Header>
                <Modal.Body>Are you sure to delete {job.title} job ?</Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        No
                    </Button>
                    <Button variant="primary" onClick={(e) => deleteJob(e,job.id)}>
                        Yes
                    </Button>
                </Modal.Footer>
            </Modal>
        </>
    );
}
