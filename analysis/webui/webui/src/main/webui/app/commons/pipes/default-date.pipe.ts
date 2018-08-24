import {Pipe, PipeTransform} from 'angular2/core';

@Pipe({
    name: 'defaultDate'
})
export class DefaultDatePipe implements PipeTransform {

    transform(value: number, args: any[]): string {
        if ((value) && (!isNaN(value))) {
            var date = new Date(value);
            var year = date.getFullYear();
            var month = date.getMonth() + 1;
            var day = date.getDate();
            var hour = date.getHours();
            var minute = date.getMinutes();
            var second = date.getSeconds();
            return year + "-" + this.twoDigits(month) + "-" + this.twoDigits(day) + " " + this.twoDigits(hour) + ":" + this.twoDigits(minute) + ":" + this.twoDigits(second);
        }
        return;
    }

    twoDigits(value: number): string {
        if (value < 10) {
            return "0" + value;
        } else {
            return String(value);
        }
    }

}