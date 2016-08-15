
export class Person {

  public id:number = 0;
  public firstName:string = "";
  public lastName:string = "";
  public gender:string = 'MALE';
  public _links:PersonResourceLinks;

  constructor(id:number, gender:string, firstName:string, lastName:string, _links:PersonResourceLinks) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.gender = gender;
    this._links = _links;
  }

}

class PersonResourceLinks {
  public self:string;
  public back:string;
}

