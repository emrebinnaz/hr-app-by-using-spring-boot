import axios from 'axios'

export const getAllJobs = async () => {

    return await axios.get("/getAllJobs");
}

export const getJob = async (jobId) => {

    return await axios.get(`/getJob/${jobId}`)
        .catch(err => {
            return err.response;
        });
}