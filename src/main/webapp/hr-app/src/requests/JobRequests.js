import axios from 'axios'

export const getAllJobs = async () => {

    const response = await axios.get("/getAllJobs");
    return response;
}

export const getJob = async (jobId) => {
    const response = await axios.get(`/getJob/${jobId}`)
        .catch(err => {
        return err.response;
    });

    return response;
}