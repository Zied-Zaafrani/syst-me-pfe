/* tslint:disable */
/* eslint-disable */
import { AppelOffreResponse } from '../models/appel-offre-response';
export interface PageResponseAppelOffreResponse {
  content?: Array<AppelOffreResponse>;
  first?: boolean;
  last?: boolean;
  number?: number;
  size?: number;
  totalElements?: number;
  totalPages?: number;
}
