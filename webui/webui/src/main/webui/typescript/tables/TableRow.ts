/**
* This class repersents a single row in a table.
*/
class TableRow {

    private cells: TableCell[] = [];

    constructor(private columnHeaders: TableColumnHeader[]) {
    }

}