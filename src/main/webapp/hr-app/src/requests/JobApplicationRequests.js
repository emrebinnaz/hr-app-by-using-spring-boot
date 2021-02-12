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
    const jobApplicationForm = new FormData();
    jobApplicationForm.append("resume",jobApplication.applicantResume);
    jobApplication.applicantResume = "";
    jobApplication = JSON.stringify(jobApplication)
    jobApplicationForm.append("jobApplication",jobApplication);

    return await axios.post(`/applyToJob/${jobId}`,jobApplicationForm, {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    }).
    catch(err => {
        return err.response;
    })
}
