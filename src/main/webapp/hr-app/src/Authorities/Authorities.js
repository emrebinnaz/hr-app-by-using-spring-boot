
import {isLogged,decodedJwtToken} from "../Authentication/Authentication";

export const isUserHrManager = () => {

    const authorities = decodedJwtToken.authorities;
    if(authorities !== undefined)
        return authorities.includes("ROLE_HR_MANAGER");

}
