/**
 * Keeps a single HashId as it is needed for files and directories.
 */
export class HashId {
    constructor(public algorithm: string, public algorithmName: string, public hash: string) {
    }
}
