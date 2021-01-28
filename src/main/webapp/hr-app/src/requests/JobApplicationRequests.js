import axios from 'axios'
import {jwtToken} from "../Authentication/Authentication";

export const getJobApplicationsBy = async (jobId) => {

    return await axios.get(`/getAllJobApplicationsBy/${jobId}`, {
        headers: {
            authorization: 'Bearer ' + jwtToken
        }
    }).catch(err => {
        return err.response;
    });
}
