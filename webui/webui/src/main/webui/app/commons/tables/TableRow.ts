import {TableColumnHeader} from './TableColumnHeader';
import {TableCell} from './TableCell';

/**
* This class repersents a single row in a table.
*/
export class TableRow {

    private cells: TableCell[] = [];

    constructor(private columnHeaders: TableColumnHeader[]) {
    }

}