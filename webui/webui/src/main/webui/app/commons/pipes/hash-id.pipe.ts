import {Pipe, PipeTransform} from 'angular2/core';

@Pipe({
    name: 'hashId'
})
export class HashIdPipe implements PipeTransform {

    transform(hashId: HashId, args: any[]): string {
        return hashId.algorithmName + ":" + hashId.hash;
    }
}
