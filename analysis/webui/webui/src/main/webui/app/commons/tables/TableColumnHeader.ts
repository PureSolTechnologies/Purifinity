/**
 * This class contains information about a single column header.
 */
export class TableColumnHeader {

    constructor(private name: string, private tooltip: string) {
    }

    public getName(): string {
        return this.name;
    }

    public getTooltip(): string {
        return this.tooltip;
    }
}
