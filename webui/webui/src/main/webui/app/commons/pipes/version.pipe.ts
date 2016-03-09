import {Pipe, PipeTransform} from 'angular2/core';

@Pipe({
    name: 'version'
})
export class VersionPipe implements PipeTransform {

    transform(version: any, args: any[]): string {
        if ((typeof version.major === "number") && (typeof version.minor === "number") && (typeof version.patch === "number")) {
            var versionString = version.major + "." + version.minor + "." + version.patch;
            if (typeof version.preReleaseInformation === "string") {
                versionString += "-" + version.preReleaseInformation;
            }
            if (typeof version.buildMetadata === "string") {
                versionString += "+" + version.buildMetadata;
            }
            return versionString;
        }
        return version;
    };
}
