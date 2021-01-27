import jwtDecode from 'jwt-decode';

export let decodedJwtToken = ""
export let isLogged = false;

export const authentication = (jwtToken) =>{

    decodedJwtToken = jwtDecode((jwtToken));
    localStorage.setItem("jwtToken",decodedJwtToken);
    isLogged = true;
}

export const getUsernameOfCurrentUser = () => {

    return decodedJwtToken.sub;
}

