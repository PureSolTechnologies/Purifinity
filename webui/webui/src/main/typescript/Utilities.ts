module Utilities {

    export function strcmp(s1, s2) {
        return (s1 < s2) ? - 1 : ((s1 > s2) ? 1 : 0);
    }

}
