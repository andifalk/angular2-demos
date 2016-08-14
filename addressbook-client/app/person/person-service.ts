
import {Injectable} from "@angular/core";
import {Person} from "./person";

@Injectable()
export class PersonService {
  private _persons: Array<Person> = [
    new Person(0, 'MALE', 'Hans', 'Mustermann'),
    new Person(1, 'FEMALE', 'Sabine', 'Huber')];

  public getPersons():Promise<Array<Person>> {
    return Promise.resolve(this._persons);
  }

  public addPerson(person:Person) {
    this._persons.push(person);
  }

  public getPerson(id: number):Promise<Person> {
    console.log('getPerson() id=%o', id);
    return this.getPersons().then(persons => persons.find(person => person.id == id))
  }
}
