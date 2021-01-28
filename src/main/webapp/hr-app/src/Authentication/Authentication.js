import jwtDecode from 'jwt-decode';

export let decodedJwtToken = ""
export let isLogged = false;
export let jwtToken = ""

export const authentication = (token) =>{

    jwtToken = token
    decodedJwtToken = jwtDecode((token));
    isLogged = true;
}

export const getUsernameOfCurrentUser = () => {

    return decodedJwtToken.sub;
}

