import axios from 'axios'

export const sendLoginRequest = async (loginRequestDTO) => {

    return await axios.post("/login", loginRequestDTO).catch(err => {
        return err.response;
    });
}