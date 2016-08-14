
export class Person {

  public id:number = 0;
  public firstName:string = "";
  public lastName:string = "";
  public gender:string = 'MALE';

  constructor(id:number, gender:string, firstName:string, lastName:string) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.gender = gender;
  }
}
