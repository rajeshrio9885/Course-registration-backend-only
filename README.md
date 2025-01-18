GitHub Repository Content Description: Course Registration Backend
Repository Overview
This project is a backend service for managing a course registration system. It enables administrators to add, update, delete, and fetch course details, while ensuring students can register for courses with limited seat availability. Built using Java, the project follows clean code principles and is designed for scalability and maintainability.

Key Features
Course Management (CRUD Operations):

Add Course: Create a new course with details like course name, description, and seat limit.
Update Course: Modify course details or adjust seat limits.
Delete Course: Remove courses from the system.
Fetch Course: Retrieve a list of all courses or details of a specific course.
Student Registration:

Students can register for available courses.
Seat limits are managed dynamically; a course cannot be overbooked.
Seat Management:

Real-time seat tracking for each course.
Automatic updates to seat availability upon registration or course removal.
Error Handling:

Prevents over-registration.
Returns appropriate error messages for invalid operations (e.g., registering for a full course).
RESTful APIs:

Exposes endpoints for course and student management.
Built with standard HTTP methods for easy integration with frontends or third-party services.
