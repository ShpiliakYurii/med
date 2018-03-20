import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";

@Injectable()
export class AttrObjectTypeService {

  public URL = 'http://localhost:8080/api/attr-object-type';

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<any> {
    return this.http.get(this.URL);
  }

  add(aot: any): Observable<any> {
    let result: Observable<Object>;
    result = this.http.put(this.URL, aot);
    return result;
  }

  update(aot: any): Observable<any> {
    let result: Observable<Object>;
    result = this.http.post(this.URL, aot);
    return result;
  }

  remove(attrId: number, objectTypeId: number) {
    return this.http.delete(this.URL + "/" + attrId + "/" + objectTypeId);
  }

  getByObjectType(otId: number, hierarchical: boolean): Observable<any> {
    return this.http.get(this.URL + "/ot/" + otId + "/" + hierarchical);
  }

}
