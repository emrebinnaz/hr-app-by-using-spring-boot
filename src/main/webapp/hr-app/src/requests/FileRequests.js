import axios from 'axios'
import {jwtToken} from "../Authentication/Authentication";

export const getResume = async (jobApplicationId) => {

    return await axios.get(`/getResumeBy/${jobApplicationId}`,{
        responseType: 'blob',
        headers : {
            Authorization : 'Bearer ' + jwtToken,
        }}).catch(err => {
            return err.response;
        });
    }