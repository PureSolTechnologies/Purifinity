import {Component, View, bootstrap} from 'angular2/angular2';
@Component({
    selector: 'contact-manager',
    template: '<h1>My First Angular 2 App</h1>'
})
@View({
    template: '<h1>Welcome to {{title}}</h1>'
})
export class ContactManager { 
       title:string;

       constructor() {
       		     title = "Contact Manager";
       }
}
bootstrap(ContactComponent).then(app => {
        console.log('Bootstrap Successful');
    }, err => {
        console.error(err);
    });
