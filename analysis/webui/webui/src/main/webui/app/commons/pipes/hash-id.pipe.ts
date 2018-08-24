import {Pipe, PipeTransform} from 'angular2/core';

import {HashId} from '../domain/HashId';

@Pipe({
    name: 'hashId'
})
export class HashIdPipe implements PipeTransform {

    transform(hashId: HashId, args: any[]): string {
        return hashId.algorithmName + ":" + hashId.hash;
    }
}
