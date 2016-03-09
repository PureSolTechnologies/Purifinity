import {Pipe, PipeTransform} from 'angular2/core';

@Pipe({
    name: 'successful-mark'
})
export class SuccessfulMarkPipe implements PipeTransform {

    transform(successful:boolean, args: any[]): string {
            var mark = "<div style='position:relative;'>" +
                "<img src='/images/icons/FatCow_Icons16x16/source_code.png' />";
            if (successful) {
                mark += "<img style='position:absolute;top:6px;left:6px;' src='/images/icons/FatCow_Icons16x16/bullet_valid.png' />";
            } else {
                mark += "<img style='position:absolute;top:6px;left:6px;' src='/images/icons/FatCow_Icons16x16/bullet_error.png' />";
            }
            mark += "</div>";
            return mark;
        }
}
