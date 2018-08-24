import {Pipe, PipeTransform} from 'angular2/core';

/**
 * We assume for that filter a duration with millisecond accuracy.
 */
@Pipe({
    name: 'duration'
})
export class DurationPipe implements PipeTransform {

    transform(duration: number, args: any[]): string {
        if (duration < 0) {
            return "not finished/aborted";
        }
        var result: string = String(duration % 1000) + "ms";
        duration = Math.floor(duration / 1000);
        if (duration <= 0) {
            return result;
        }
        result = String(duration % 60) + "s " + result;
        duration = Math.floor(duration / 60);
        if (duration <= 0) {
            return result;
        }
        result = String(duration % 60) + "min " + result;
        duration = Math.floor(duration / 60);
        if (duration <= 0) {
            return result;
        }
        return String(duration) + "hr " + result;
    }
}
