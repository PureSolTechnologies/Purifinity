import {Pipe, PipeTransform} from 'angular2/core';

@Pipe({
    name: 'metric-value'
})
export class MetricValuePipe implements PipeTransform {

    transform(value: string, args: any[]): string {
        if (value === String(parseInt(value, 10))) {
            return value;
        } else {
            return Number(value).toFixed(2);
        }
    };
}
