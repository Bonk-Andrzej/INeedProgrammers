export interface UserDto {
  id?: number;
  version?: number;
  firstName?: string;
  lastName?: string;
  login?: string;
  email?: string;
  password?: string;
  resetPasswordKey?: string;
  resetPasswordDate?: string;
  isEnabled?: boolean;

  roleId?: number;
}
