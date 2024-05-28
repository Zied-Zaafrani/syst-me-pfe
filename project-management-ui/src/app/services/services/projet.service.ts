/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { deleteProjet } from '../fn/projet/delete-projet';
import { DeleteProjet$Params } from '../fn/projet/delete-projet';
import { findAllProjets } from '../fn/projet/find-all-projets';
import { FindAllProjets$Params } from '../fn/projet/find-all-projets';
import { findAllProjetsByUserStructure } from '../fn/projet/find-all-projets-by-user-structure';
import { FindAllProjetsByUserStructure$Params } from '../fn/projet/find-all-projets-by-user-structure';
import { findProjetById } from '../fn/projet/find-projet-by-id';
import { FindProjetById$Params } from '../fn/projet/find-projet-by-id';
import { PageResponseProjetResponse } from '../models/page-response-projet-response';
import { ProjetResponse } from '../models/projet-response';
import { saveProjet } from '../fn/projet/save-projet';
import { SaveProjet$Params } from '../fn/projet/save-projet';

@Injectable({ providedIn: 'root' })
export class ProjetService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `findAllProjetsByUserStructure()` */
  static readonly FindAllProjetsByUserStructurePath = '/projets';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAllProjetsByUserStructure()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllProjetsByUserStructure$Response(params?: FindAllProjetsByUserStructure$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseProjetResponse>> {
    return findAllProjetsByUserStructure(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAllProjetsByUserStructure$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllProjetsByUserStructure(params?: FindAllProjetsByUserStructure$Params, context?: HttpContext): Observable<PageResponseProjetResponse> {
    return this.findAllProjetsByUserStructure$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseProjetResponse>): PageResponseProjetResponse => r.body)
    );
  }

  /** Path part for operation `saveProjet()` */
  static readonly SaveProjetPath = '/projets';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `saveProjet()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveProjet$Response(params: SaveProjet$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return saveProjet(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `saveProjet$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveProjet(params: SaveProjet$Params, context?: HttpContext): Observable<number> {
    return this.saveProjet$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `findProjetById()` */
  static readonly FindProjetByIdPath = '/projets/{projet-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findProjetById()` instead.
   *
   * This method doesn't expect any request body.
   */
  findProjetById$Response(params: FindProjetById$Params, context?: HttpContext): Observable<StrictHttpResponse<ProjetResponse>> {
    return findProjetById(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findProjetById$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findProjetById(params: FindProjetById$Params, context?: HttpContext): Observable<ProjetResponse> {
    return this.findProjetById$Response(params, context).pipe(
      map((r: StrictHttpResponse<ProjetResponse>): ProjetResponse => r.body)
    );
  }

  /** Path part for operation `findAllProjets()` */
  static readonly FindAllProjetsPath = '/projets/all';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAllProjets()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllProjets$Response(params?: FindAllProjets$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseProjetResponse>> {
    return findAllProjets(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAllProjets$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllProjets(params?: FindAllProjets$Params, context?: HttpContext): Observable<PageResponseProjetResponse> {
    return this.findAllProjets$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseProjetResponse>): PageResponseProjetResponse => r.body)
    );
  }

  /** Path part for operation `deleteProjet()` */
  static readonly DeleteProjetPath = '/projets/{projetId}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `deleteProjet()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteProjet$Response(params: DeleteProjet$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return deleteProjet(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `deleteProjet$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteProjet(params: DeleteProjet$Params, context?: HttpContext): Observable<void> {
    return this.deleteProjet$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

}
