/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { ProjetResponse } from '../../models/projet-response';

export interface FindProjetById$Params {
  'projet-id': number;
}

export function findProjetById(http: HttpClient, rootUrl: string, params: FindProjetById$Params, context?: HttpContext): Observable<StrictHttpResponse<ProjetResponse>> {
  const rb = new RequestBuilder(rootUrl, findProjetById.PATH, 'get');
  if (params) {
    rb.path('projet-id', params['projet-id'], {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<ProjetResponse>;
    })
  );
}

findProjetById.PATH = '/projets/{projet-id}';
