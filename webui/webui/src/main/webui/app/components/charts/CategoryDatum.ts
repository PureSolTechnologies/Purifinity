export class CategoryDatum {

    constructor(private name: string = "",
        private value: number = 0) { }

    public getName(): string {
        return this.name;
    }

    public getValue(): number {
        return this.value;
    }
}