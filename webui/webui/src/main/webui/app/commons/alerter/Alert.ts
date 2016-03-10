/**
 * This class represents a single alert which is to be shown in UI. The alert
 * itself only contains two public properties severity and message. 
 */
export class Alert {
    constructor(public severity: string, public message: string) {
    }
}
