import {TableColumnHeader} from './TableColumnHeader';
import {TreeTableNode} from './TreeTableNode';

/**
 * This class is used as model for the tree-table directive. 
 */
export class TreeTableData {

    /**
     * @columnHeaders is an array containing the column header definitions which are used
     * throughout the whole table to have consistency.
     * @root contains the tree data for the table.
     */
    constructor(public columnHeaders: Array<TableColumnHeader>, public root: TreeTableNode) { }

}
