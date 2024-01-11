import {HttpEvent, HttpHandler, HttpRequest} from '@angular/common/http';
import {catchError, finalize, Observable, throwError} from "rxjs";
import {LoadingService} from "../services/loading.service";
import {Injectable} from "@angular/core";

@Injectable()
export class HttpManagerInterceptor {

  constructor(private loadingService:LoadingService) {
  }

  intercept(request:HttpRequest<unknown>, next:HttpHandler):Observable<HttpEvent<unknown>>{
    this.loadingService.loading.next(true);
    return next.handle(request).pipe(
      catchError(err => {
        console.log(err);
        return throwError(err);
      }),
      finalize(()=>{
        this.loadingService.loading.next(false);
      })
    )
  }


}
