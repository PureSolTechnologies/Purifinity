import {Pipe, PipeTransform} from 'angular2/core';

@Pipe({
    name: 'metric-value'
})
export class MetricValuePipe implements PipeTransform {

    transform(value: number, args: any[]): number {
        if (value === parseInt(value, 10)) {
            return value;
        } else {
            return value.toFixed(2);
        }
    };
}
