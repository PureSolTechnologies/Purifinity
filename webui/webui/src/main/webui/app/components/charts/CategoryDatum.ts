export class CategoryDatum {

    constructor(private name: string,
        private value: number, private onClick: () => any  = null) { }

    public getName(): string {
         return this.name;
     }

    public getValue(): number {
         return this.value;
     }

    public performOnClick(): void {
         if(this.onClick != null) {
            this.onClick();
        }
     }
}