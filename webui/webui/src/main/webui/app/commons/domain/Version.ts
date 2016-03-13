export class Version {

    constructor(public major: number,
        public minor: number,
        public patch: number,
        public buildMetadata: string,
        public preReleaseInformation: string) {
    }

}