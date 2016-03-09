import {Pipe, PipeTransform} from 'angular2/core';

/**
 * We assume for that filter a duration with millisecond accuracy.
 */
@Pipe({
    name: 'fsSize'
})
export class FsSizePipe implements PipeTransform {

    transform(size: number, args: any[]): string {
        var magnitude = 0;
        var result = size;
        while ((result > 1024) && (magnitude < 4)) {
            result /= 1024;
            magnitude++;
        }
        switch (magnitude) {
            case 1:
                return String(result.toFixed(2)) + "kB";
            case 2:
                return String(result.toFixed(2)) + "MB";
            case 3:
                return String(result.toFixed(2)) + "GB";
            case 4:
                return String(result.toFixed(2)) + "TB";
            default:
                return String(size) + " B";
        }
    }
}
