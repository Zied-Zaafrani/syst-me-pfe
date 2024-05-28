/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { ContratResponse } from '../../models/contrat-response';

export interface GetContratById$Params {
  id: number;
}

export function getContratById(http: HttpClient, rootUrl: string, params: GetContratById$Params, context?: HttpContext): Observable<StrictHttpResponse<ContratResponse>> {
  const rb = new RequestBuilder(rootUrl, getContratById.PATH, 'get');
  if (params) {
    rb.path('id', params.id, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<ContratResponse>;
    })
  );
}

getContratById.PATH = '/contrats/{id}';
