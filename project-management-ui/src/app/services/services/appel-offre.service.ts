/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { AppelOffreResponse } from '../models/appel-offre-response';
import { deleteAppelOffre } from '../fn/appel-offre/delete-appel-offre';
import { DeleteAppelOffre$Params } from '../fn/appel-offre/delete-appel-offre';
import { findAllAppelOffres } from '../fn/appel-offre/find-all-appel-offres';
import { FindAllAppelOffres$Params } from '../fn/appel-offre/find-all-appel-offres';
import { findAllAppelOffresByProjetId } from '../fn/appel-offre/find-all-appel-offres-by-projet-id';
import { FindAllAppelOffresByProjetId$Params } from '../fn/appel-offre/find-all-appel-offres-by-projet-id';
import { findAllAppelOffresByUserStructure } from '../fn/appel-offre/find-all-appel-offres-by-user-structure';
import { FindAllAppelOffresByUserStructure$Params } from '../fn/appel-offre/find-all-appel-offres-by-user-structure';
import { findAppelOffreById } from '../fn/appel-offre/find-appel-offre-by-id';
import { FindAppelOffreById$Params } from '../fn/appel-offre/find-appel-offre-by-id';
import { PageResponseAppelOffreResponse } from '../models/page-response-appel-offre-response';
import { saveAppelOffre } from '../fn/appel-offre/save-appel-offre';
import { SaveAppelOffre$Params } from '../fn/appel-offre/save-appel-offre';
import { updateStatus } from '../fn/appel-offre/update-status';
import { UpdateStatus$Params } from '../fn/appel-offre/update-status';

@Injectable({ providedIn: 'root' })
export class AppelOffreService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `findAllAppelOffresByUserStructure()` */
  static readonly FindAllAppelOffresByUserStructurePath = '/appels-offres';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAllAppelOffresByUserStructure()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllAppelOffresByUserStructure$Response(params?: FindAllAppelOffresByUserStructure$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseAppelOffreResponse>> {
    return findAllAppelOffresByUserStructure(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAllAppelOffresByUserStructure$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllAppelOffresByUserStructure(params?: FindAllAppelOffresByUserStructure$Params, context?: HttpContext): Observable<PageResponseAppelOffreResponse> {
    return this.findAllAppelOffresByUserStructure$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseAppelOffreResponse>): PageResponseAppelOffreResponse => r.body)
    );
  }

  /** Path part for operation `saveAppelOffre()` */
  static readonly SaveAppelOffrePath = '/appels-offres';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `saveAppelOffre()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveAppelOffre$Response(params: SaveAppelOffre$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return saveAppelOffre(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `saveAppelOffre$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveAppelOffre(params: SaveAppelOffre$Params, context?: HttpContext): Observable<number> {
    return this.saveAppelOffre$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `updateStatus()` */
  static readonly UpdateStatusPath = '/appels-offres/{appelOffreId}/{status}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateStatus()` instead.
   *
   * This method doesn't expect any request body.
   */
  updateStatus$Response(params: UpdateStatus$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return updateStatus(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updateStatus$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  updateStatus(params: UpdateStatus$Params, context?: HttpContext): Observable<void> {
    return this.updateStatus$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `findAppelOffreById()` */
  static readonly FindAppelOffreByIdPath = '/appels-offres/{appelOffreId}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAppelOffreById()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAppelOffreById$Response(params: FindAppelOffreById$Params, context?: HttpContext): Observable<StrictHttpResponse<AppelOffreResponse>> {
    return findAppelOffreById(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAppelOffreById$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAppelOffreById(params: FindAppelOffreById$Params, context?: HttpContext): Observable<AppelOffreResponse> {
    return this.findAppelOffreById$Response(params, context).pipe(
      map((r: StrictHttpResponse<AppelOffreResponse>): AppelOffreResponse => r.body)
    );
  }

  /** Path part for operation `deleteAppelOffre()` */
  static readonly DeleteAppelOffrePath = '/appels-offres/{appelOffreId}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `deleteAppelOffre()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteAppelOffre$Response(params: DeleteAppelOffre$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return deleteAppelOffre(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `deleteAppelOffre$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteAppelOffre(params: DeleteAppelOffre$Params, context?: HttpContext): Observable<void> {
    return this.deleteAppelOffre$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `findAllAppelOffresByProjetId()` */
  static readonly FindAllAppelOffresByProjetIdPath = '/appels-offres/byProjet/{projetId}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAllAppelOffresByProjetId()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllAppelOffresByProjetId$Response(params: FindAllAppelOffresByProjetId$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseAppelOffreResponse>> {
    return findAllAppelOffresByProjetId(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAllAppelOffresByProjetId$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllAppelOffresByProjetId(params: FindAllAppelOffresByProjetId$Params, context?: HttpContext): Observable<PageResponseAppelOffreResponse> {
    return this.findAllAppelOffresByProjetId$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseAppelOffreResponse>): PageResponseAppelOffreResponse => r.body)
    );
  }

  /** Path part for operation `findAllAppelOffres()` */
  static readonly FindAllAppelOffresPath = '/appels-offres/all';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAllAppelOffres()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllAppelOffres$Response(params?: FindAllAppelOffres$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseAppelOffreResponse>> {
    return findAllAppelOffres(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAllAppelOffres$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllAppelOffres(params?: FindAllAppelOffres$Params, context?: HttpContext): Observable<PageResponseAppelOffreResponse> {
    return this.findAllAppelOffres$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseAppelOffreResponse>): PageResponseAppelOffreResponse => r.body)
    );
  }

}
