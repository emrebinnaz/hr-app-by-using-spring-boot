import axios from 'axios'

export const sendLoginRequest = async (loginRequestDTO) => {

    const response = await axios.post("/login",loginRequestDTO).catch(err => {
        return err.response;
    });

    return response;
}