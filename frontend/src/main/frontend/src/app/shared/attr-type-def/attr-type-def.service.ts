import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";

@Injectable()
export class AttrTypeDefService {

  public URL = 'http://localhost:8080/api/attr-type-def';

  constructor(private http: HttpClient) {
  }

  getAllByName(name: string): Observable<any> {
    return this.http.get(this.URL + "/by-name/" + name);
  }

  add(attr: any): Observable<any> {
    let result: Observable<Object>;
    result = this.http.put(this.URL, attr);
    return result;
  }

  getById(id: number): Observable<any> {
    return this.http.get(this.URL + "/" + id);
  }

  getAttrTypeViewById(id): string {
    switch (id) {
      case 1:
        return "Text";
      case 2:
        return "Number";
      case 3:
        return "Date";
      case 4:
        return "List";
      case 5:
        return "Reference";
      default:
        return "";
    }
  }

}
