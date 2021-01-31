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

export const applyToJob = async (jobApplication,jobId) => {

    return await axios.post(`/applyToJob/${jobId}`,jobApplication).

    catch(err => {
        return err.response;
    })
}
