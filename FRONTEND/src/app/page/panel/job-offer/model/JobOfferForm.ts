export interface JobOfferForm {
  id?: number;
  uuid?: string;
  title?: string;
  content?: string;
  email?: string;
  salary?: string;
  phoneNumber?: string;
  employerId?: number;
  categoriesIds?: number[];
  technologiesIds?: number[];
  senioritySetIds?: number[];
  locationsIds?: number[];
  benefitsIds?: number[];
}
