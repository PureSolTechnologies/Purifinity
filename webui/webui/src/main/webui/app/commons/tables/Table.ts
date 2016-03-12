import {TableColumnHeader} from './TableColumnHeader';
import {TableRow} from './TableRow';



/**
* This class is used to represent data in a simple table with rows and 
* columns. Each column has a column header with a name and other 
* specifications.
*/
export class Table {

    private columns: TableColumnHeader[] = [];
    private rows: TableRow[] = [];

    constructor(private name: string) {
    }

    public getName(): string {
        return this.name;
    }

    public addColumn(columnHeader: TableColumnHeader): void {
        this.columns.push(columnHeader);
    }

    public getColumnHeaders(): TableColumnHeader[] {
        return this.columns;
    }

    public addRow(row: TableRow): void {
        this.rows.push(row);
    }

    public getRows(): TableRow[] {
        return this.rows;
    }
}
