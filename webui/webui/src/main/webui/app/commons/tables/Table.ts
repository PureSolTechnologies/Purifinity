import {TableColumnHeader} from './TableColumnHeader';
import {TableRow} from './TableRow';
import {TableCell} from './TableCell';
import {TableModel} from './TableModel';
import {VariableType} from '../VariableType';

/**
* This class is used to represent data in a simple table with rows and 
* columns. Each column has a column header with a name and other 
* specifications.
*/
export class Table implements TableModel {

    private columns: TableColumnHeader[] = [];
    private rows: TableRow[] = [];

    constructor(private name: string) {
    }

    public getTableName(): string {
        return this.name;
    }

    public getRowCount(): number {
        return this.rows.length;
    }

    public getColumnCount(): number {
        return this.columns.length;
    }

    public addColumn(columnHeader: TableColumnHeader): void {
        this.columns.push(columnHeader);
    }

    public getColumnHeaders(): TableColumnHeader[] {
        return this.columns;
    }

    public getColumnName(columnIndex: number): string {
        return this.columns[columnIndex].getName();
    }

    public getColumnType(columnIndex: number): VariableType {
        return VariableType.ANY;
    };

    public isCellEditable(rowIndex: number, columnIndex: number): boolean {
        return false;
    }

    public getCellAt(rowIndex: number, columnIndex: number): TableCell {
        return this.rows[rowIndex].getCells()[columnIndex].content;
    }

    public setValueAt(value: any, rowIndex: number, columnIndex: number): void {
        // TODO to be implemented
    }


    public addRow(row: TableRow): void {
        this.rows.push(row);
    }

    public getRows(): TableRow[] {
        return this.rows;
    }
}
