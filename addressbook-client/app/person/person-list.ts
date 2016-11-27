
import {Person} from "./person";

export class PersonList {
  public persons:Person[];
  public _links:PersonListResourceLinks;
}

class PersonListResourceLinks {
  public self:string;
}
