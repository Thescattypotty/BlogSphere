import { ERole } from "./role";

export interface RegisterRequest {
    username: String;
    email: String;
    password: String;
    firstName?: String;
    lastName?: String;
    roles?: ERole[];
    profilePicture?: String;
    bio?: String;
}
