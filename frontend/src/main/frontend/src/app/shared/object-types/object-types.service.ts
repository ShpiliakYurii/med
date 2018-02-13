import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class ObjectTypesService {

  public URL = 'http://localhost:8080/api/object-type';

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<any> {
    return this.http.get(this.URL);
  }

  add(objectType: any): Observable<any> {
    let result: Observable<Object>;
    result = this.http.put(this.URL, objectType);
    return result;
  }

  update(objectType: any): Observable<any> {
    let result: Observable<Object>;
    result = this.http.post(this.URL, objectType);
    return result;
  }

  remove(id: number) {
    return this.http.delete(this.URL +"/"+ id);
  }
}
