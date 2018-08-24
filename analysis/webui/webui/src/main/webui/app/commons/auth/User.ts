import {Role} from './Role';

/**
 * This class represents a single user.
 */
export class User {

    constructor(public name: string, public email: string, public role: Role) { }

}