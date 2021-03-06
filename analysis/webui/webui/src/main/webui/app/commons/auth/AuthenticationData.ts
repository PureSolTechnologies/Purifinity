/**
 * This class represents a single set of authentication information which is 
 * used to authenticate and authorize against the server.
 */
export class AuthenticationData {
    constructor(public authId: string, public authToken: string) {
    }
}   
