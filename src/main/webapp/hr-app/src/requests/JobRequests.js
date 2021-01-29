import axios from 'axios'
import {jwtToken} from "../Authentication/Authentication";

export const getAllJobs = async () => {

    return await axios.get("/getAllJobs");
}

export const getJob = async (jobId) => {

    return await axios.get(`/getJob/${jobId}`)
        .catch(err => {
            return err.response;
        });
}

export const sendAddJobRequest = async (job) => {

    return await axios.post(`/saveJob`,
        job, {
            headers : {
                'Authorization' : "Bearer " + jwtToken
            }
        }).catch(err => {
        return err.response;
    })
}