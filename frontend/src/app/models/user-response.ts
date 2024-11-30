import { ERole } from "./role";

export interface UserResponse {
    id: String;
    username: String;
    email: String;
    firstName: String;
    lastName: String;
    roles: ERole[];
    profilePicture: String;
    bio: String;
    createdAt: Date;
    updatedAt: Date;
}
