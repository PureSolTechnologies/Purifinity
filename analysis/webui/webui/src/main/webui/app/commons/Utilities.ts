export class Utilities {

    static strcmp(s1, s2) {
        return (s1 < s2) ? - 1 : ((s1 > s2) ? 1 : 0);
    }

    static getEnumNames(enumeration): string[] {
        let names: string[] = [];
        Object.keys(enumeration)
            .map(key => enumeration[key])
            .filter(key => typeof key === "string")
            .forEach(function(name: string) { names.push(name); });
        return names;
    }
}
