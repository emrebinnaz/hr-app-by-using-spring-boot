import React, {Component} from 'react';
import {Card,Button} from 'react-bootstrap'
import {isUserHrManager} from "../../Authorities/Authorities"
import {formatDate} from "../../Helpers/DateFormat";

class JobDetailCard extends Component {

    render() {
        const {job} = this.props;;
        return (
            <div>
                <Card className="text-center">
                    <Card.Header>{job.title}</Card.Header>
                    <Card.Body>
                        <Card.Title>{job.title}</Card.Title>
                        <Card.Text>
                            {job.description}
                        </Card.Text>
                        {!isUserHrManager() ? <Button variant="primary">Go somewhere</Button> : null }
                    </Card.Body>
                    <Card.Footer className="text-muted">
                      Last Application Date : {formatDate(job.lastApplicationDate)}
                    </Card.Footer>
                </Card>
            </div>
        );
    }
}

export default JobDetailCard;