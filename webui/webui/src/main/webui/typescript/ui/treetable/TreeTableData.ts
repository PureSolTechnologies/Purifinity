/**
 * This class is used as model for the tree-table directive. 
 */
class TreeTableData {

    /**
     * This array contains the column header definitions which are used
     * throughout the whole table to have consistency.
     */
    columnHeaders: TableColumnHeader[] = [];

    /**
     * This variable contains the tree data for the table.
     */
    root: TreeTableTree;

}
