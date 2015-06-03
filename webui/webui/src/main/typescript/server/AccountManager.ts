class AccountManager {

    constructor(private purifinityServerConnector: PurifinityServerConnector) {
    }

    getUsers(success, error) {
        return this.purifinityServerConnector.get("/accountmanager/rest/users",
            success, error);
    }

    createAccount(email, name, password, roleId, success, error) {
        var data = {
            email: email,
            name: name,
            password: password,
            roleId: roleId
        };
        return this.purifinityServerConnector.put("/accountmanager/rest/users", data,
            success, error);
    }

    editAccount(email, name, roleId, success, error) {
        var data = {
            name: name,
            roleId: roleId
        };
        return this.purifinityServerConnector.post("/accountmanager/rest/users/" + email, data,
            success, error);
    }

    deleteAccount(email, success, error) {
        var data = {};
        return this.purifinityServerConnector.del("/accountmanager/rest/users/" + email,
            success, error);
    }

    getRoles(success, error) {
        return this.purifinityServerConnector.get("/accountmanager/rest/roles",
            success, error);
    }
    getUser(email, success, error) {
        return this.purifinityServerConnector.get("/accountmanager/rest/users/" + email,
            success, error);
    }

    changePassword(email, oldPassword, newPassword, success, error) {
        var data = {
            oldPassword: oldPassword,
            newPassword: newPassword
        };
        return this.purifinityServerConnector.post("/accountmanager/rest/users/" + email + "/passwd", data,
            success, error);
    }

}
