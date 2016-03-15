import {Injectable} from 'angular2/core';
import {Response} from 'angular2/http';

import {PurifinityServerConnector} from './PurifinityServerConnector';
import {Role} from '../auth/Role';
import {User} from '../auth/User';

@Injectable()
export class AccountManager {

    constructor(private purifinityServerConnector: PurifinityServerConnector) {
    }

    getUsers(success: (users: User[]) => void, error: (response: Response) => void) {
        return this.purifinityServerConnector.get("/accountmanager/rest/users",
            function(response: Response) {
                success(response.json());
            }, error);
    }

    getUser(email: string, success: (user: User) => void, error: (response: Response) => void) {
        return this.purifinityServerConnector.get("/accountmanager/rest/users/" + email,
            function(response: Response) {
                success(response.json());
            }, error);
    }

    createAccount(email: string, name: string, password: string, roleId: string,
        success: (response: Response) => void, error: (response: Response) => void) {
        var data = {
            email: email,
            name: name,
            password: password,
            roleId: roleId
        };
        return this.purifinityServerConnector.put("/accountmanager/rest/users", data,
            success, error);
    }

    editAccount(email: string, name: string, roleId: string,
        success: (response: Response) => void, error: (response: Response) => void) {
        var data = {
            name: name,
            roleId: roleId
        };
        return this.purifinityServerConnector.post("/accountmanager/rest/users/" + email, data,
            success, error);
    }

    deleteAccount(email: string,
        success: (response: Response) => void, error: (response: Response) => void) {
        var data = {};
        return this.purifinityServerConnector.del("/accountmanager/rest/users/" + email,
            success, error);
    }

    changePassword(email: string, oldPassword: string, newPassword: string,
        success: (response: Response) => void, error: (response: Response) => void) {
        var data = {
            oldPassword: oldPassword,
            newPassword: newPassword
        };
        return this.purifinityServerConnector.post("/accountmanager/rest/users/" + email + "/passwd", data,
            success, error);
    }

    getRoles(success: (roles: Role[]) => void, error: (response: Response) => void) {
        return this.purifinityServerConnector.get("/accountmanager/rest/roles",
            function(response: Response) {
                success(response.json());
            }, error);
    }

}
