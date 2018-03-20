import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";

@Injectable()
export class AttrGroupsService {

  public URL = 'http://localhost:8080/api/attr-group';

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<any> {
    return this.http.get(this.URL);
  }

  add(attrGroup: any): Observable<any> {
    let result: Observable<Object>;
    result = this.http.put(this.URL, attrGroup);
    return result;
  }

  update(attrGroup: any): Observable<any> {
    let result: Observable<Object>;
    result = this.http.post(this.URL, attrGroup);
    return result;
  }

  remove(id: number) {
    return this.http.delete(this.URL + "/" + id);
  }

  getAllByName(name: string): Observable<any> {
    return this.http.get(this.URL + "/by-name/" + name);
  }

  getById(id: number): Observable<any>{
    return this.http.get(this.URL + "/" + id);
  }

}
