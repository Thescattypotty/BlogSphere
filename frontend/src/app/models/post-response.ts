export interface PostResponse {
    id: String;
    title: String;
    content: String;
    coverImage: String;
    createdAt: Date;
    updatedAt: Date;
    publishedAt: Date;
    isPublished: Boolean;
    tagsId: String[];
    commentsId: String[];
    createdById: String;

}
