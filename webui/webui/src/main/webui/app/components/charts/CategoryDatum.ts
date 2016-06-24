export class CategoryDatum {

    constructor(private name: string,
        private value: number, private link: any = null) { }

    public getName(): string {
        return this.name;
    }

    public getValue(): number {
        return this.value;
    }

    public getLink(): any {
        return this.link;
    }
}