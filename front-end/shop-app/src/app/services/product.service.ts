import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpService } from './http.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  serverAPI_URL = `http://localhost:8085`;

  constructor(
    private http: HttpClient,
    private httpService: HttpService
  ) { }

  getAllProducts(): Observable<any> {
    const url = `${this.serverAPI_URL}/products/all`;
    const httpOptions = this.httpService.getHttpHeader();
    return this.http.get(url, httpOptions);
  }
}
