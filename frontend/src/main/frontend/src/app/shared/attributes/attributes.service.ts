import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";

@Injectable()
export class AttributesService {

  public URL = 'http://localhost:8080/api/attribute';

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<any> {
    return this.http.get(this.URL);
  }

  add(attr: any): Observable<any> {
    let result: Observable<Object>;
    result = this.http.put(this.URL, attr);
    return result;
  }

  addAndBind(attr: any, otId: number, options: number): Observable<any> {
    let result: Observable<Object>;
    result = this.http.put(this.URL + "/" + otId + "/" + options, attr);
    return result;
  }

  update(attr: any): Observable<any> {
    let result: Observable<Object>;
    result = this.http.post(this.URL, attr);
    return result;
  }

  remove(id: number): Observable<any> {
    return this.http.delete(this.URL + "/" + id);
  }

  getById(id: number): Observable<any> {
    return this.http.get(this.URL + "/" + id);
  }
}
