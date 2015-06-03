/**
 * This class is used to keep the information about a single column for tree table. 
 */
class TreeTableColumn {

    /**
     * @constructor
     * @param content is the content of the cell to be displayed.
     * @param imageUrl is an URL to an image to be added to the cell in front of the content.
     * @param link is an URL which is to be put into a link.
     */
    constructor(public content: any, public imageUrl: string, public link: string) {
    }

}
