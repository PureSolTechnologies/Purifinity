export default class ServerConfiguration {

	host;
    port;
    
    constructor(
        host = 'localhost',
        port = 8080
    ) { 
    	this.host = host;
    	this.port = port;
    }

}
