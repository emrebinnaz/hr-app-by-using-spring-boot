import React, {Component} from 'react';
import {Card} from 'react-bootstrap'
import {formatDate} from "../../Helpers/DateFormat";

class JobDetailCard extends Component {

    render() {
        const {job} = this.props;;
        return (
            <div>
                <Card className="text-center">
                    <Card.Header>{job.title}</Card.Header>
                    <Card.Body>
                        <Card.Text>
                            {job.description}
                        </Card.Text>
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