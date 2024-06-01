# Blog Platform Specification

## Overview
The Blog Platform is a web application backend that allows users to create, publish, and manage their own blog posts. It provides a user-friendly interface for both writers and readers, along with features for commenting, searching, and categorizing blog posts.

## User Roles
- **Admin:** Can manage users, blog posts, comments, and categories.
- **Registered User:** Can create, edit, and delete their own blog posts. Can comment on blog posts.
- **Guest User:** Can browse and read blog posts. Cannot create or edit content.

## Features

### For Writers (Registered Users):
- Authentication: Users can sign up, log in, and log out.
- Blog Post Management: Users can create, edit, delete, and publish/unpublish their blog posts.
- Rich Text Editor: Integration of a WYSIWYG editor for writing blog posts.
- Category Management: Users can assign categories/tags to their blog posts.
- Drafts: Users can save blog posts as drafts before publishing.
- Comments Moderation: Users can moderate comments on their own blog posts.

### For Readers (Guests and Registered Users):
- Browse Blog Posts: Visitors can view a list of all published blog posts.
- Read Full Post: Visitors can click on a blog post to read its full content.
- Search Functionality: Users can search for blog posts by title, content, or category.
- Commenting System: Users can leave comments on blog posts.
- Pagination: Display blog posts with pagination to manage large numbers of posts efficiently.
- User Profiles: Users can view profiles of writers and see their published blog posts.

### For Admins:
- User Management: Admins can manage user accounts (create, edit, delete, ban).
- Blog Post Management: Admins can manage all blog posts (edit, delete, publish/unpublish).
- Comment Moderation: Admins can moderate comments on all blog posts.
- Category Management: Admins can manage blog post categories (create, edit, delete).

## Technology Stack
- **Backend:** Spring Boot for RESTful API development.
- **Database:** MySQL or PostgreSQL for storing user data, blog posts, comments, and categories.
- **Authentication:** JWT (JSON Web Tokens) for user authentication and authorization.
- **API Documentation:** Swagger for documenting the RESTful APIs.
