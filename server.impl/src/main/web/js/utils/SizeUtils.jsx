export function convertToSizeString( sizeInBytes ) {
    if ( sizeInBytes < 1024 ) {
        return sizeInBytes + "B";
    }
    let sizeInKB = sizeInBytes / 1024;
    if ( sizeInKB < 1024 ) {
        return Math.round( sizeInKB * 100 ) / 100 + "kB";
    }
    let sizeInMB = sizeInKB / 1024;
    if ( sizeInMB < 1024 ) {
        return Math.round( sizeInMB * 100 ) / 100 + "B";
    }
    let sizeInGB = sizeInMB / 1024;
    return Math.round( sizeInGB * 100 ) / 100 + "GB";
}
