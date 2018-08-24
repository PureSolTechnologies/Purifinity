import {TableColumnHeader} from './TableColumnHeader';
import {TableCell} from './TableCell';

/**
* This class represents a single row in a table.
*/
export class TableRow {

    private cells: TableCell[] = [];

    constructor(private columnHeaders: TableColumnHeader[]) {
    }

    public addCell(cell: TableCell): void {
        this.cells.push(cell);
    }

    public getCells(): TableCell[] {
        return this.cells;
    }

}
