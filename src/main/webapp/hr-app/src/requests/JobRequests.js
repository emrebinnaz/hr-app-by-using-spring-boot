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

export const sendDeleteJobRequest =  async (jobId) => {

    return await axios.delete(`/deleteJob/${jobId}`,{
        headers:{
            'Authorization' : "Bearer " + jwtToken
        }

    }).catch(err => {
        return err.response;
    })
}

export const sendUpdateJobRequest = async (job,jobId) => {

   return await axios.put(`/updateJob/${jobId}`, job,{
        headers : {
            'Authorization' : "Bearer " + jwtToken
        }
    }).catch(err => {
       return err.response;
    });
}