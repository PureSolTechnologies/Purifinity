import {Component, Input} from 'angular2/core';
import {ROUTER_DIRECTIVES} from 'angular2/router';

import {TableModel} from '../../commons/tables/TableModel';

@Component({
    selector: 'data-table',
    directives: [
        ROUTER_DIRECTIVES
    ],
    templateUrl: '../../../html/components/table/data-table.html'
})
export class DataTableComponent {

    @Input()
    private tableData: TableModel;

    constructor(){}
}
